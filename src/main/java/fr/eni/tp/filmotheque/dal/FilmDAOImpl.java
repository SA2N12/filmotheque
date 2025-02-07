package fr.eni.tp.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;

@Repository
public class FilmDAOImpl implements FilmDAO {
	
	private static final String SELECT_BY_ID = "SELECT f.id, f.titre, f.annee, f.duree, f.synopsis, f.id_realisateur, p.nom as nom_realisateur, p.prenom as prenom_realisateur, g.id as id_genre, g.titre as genre_titre FROM FILM f JOIN GENRE g ON f.id_genre = g.id JOIN PARTICIPANT p ON f.id_realisateur = p.id WHERE f.id = :idFilm";
	private static final String SELECT_ALL = "SELECT f.id, f.titre, f.annee, f.duree, f.synopsis, f.id_realisateur, p.nom as nom_realisateur, p.prenom as prenom_realisateur,  g.id as id_genre, g.titre as genre_titre FROM FILM f JOIN GENRE g ON f.id_genre = g.id JOIN PARTICIPANT p ON f.id_realisateur = p.id";
	private static final String INSERT = "INSERT INTO FILM ( titre, annee, duree, synopsis, id_realisateur, id_genre) VALUES (:titre, :annee, :duree, :synopsis, :id_realisateur, :id_genre)";
	private static final String FIND_BY_ID = "select titre from film where id=:idFilm";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public FilmDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void create(Film film) {
		// Crée un objet KeyHolder
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("titre", film.getTitre());
		namedParameters.addValue("annee", film.getAnnee());
		namedParameters.addValue("duree", film.getDuree());
		namedParameters.addValue("synopsis", film.getSynopsis());
		namedParameters.addValue("id_realisateur", film.getRealisateur().getId());
		namedParameters.addValue("id_genre", film.getGenre().getId());
		namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);
		
		//récupérer l'id généré
		Number generatedKey = keyHolder.getKey();
		if(generatedKey != null) {
			film.setId(generatedKey.longValue()); // assigné l'id généré à l'objet film
		}
	}

	@Override
	public Film read(long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idFilm", id);
		return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, new FilmRowMapper());
	}

	@Override
	public List<Film> findAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL, new FilmRowMapper());
	}

	@Override
	public String findTitre(long id) {
		//créer un mapsqlparameter namedParameters
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		//ajouter valeur au namedparameters
		namedParameters.addValue("idFilm", id);
		
		//namedParameterJdbcTemplate
		return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, String.class);
	}
	
	class FilmRowMapper implements RowMapper<Film> {
		@Override
		public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
			Film f = new Film();
			f.setId(rs.getLong("id"));
			f.setTitre(rs.getString("titre"));
			f.setAnnee(rs.getInt("annee"));
			f.setDuree(rs.getInt("duree"));
			f.setSynopsis(rs.getString("synopsis"));
			
			Participant participant = new Participant();	
			participant.setId(rs.getLong("id_realisateur"));
			participant.setNom(rs.getString("nom_realisateur"));
			participant.setPrenom(rs.getString("prenom_realisateur"));
			f.setRealisateur(participant);
	
			Genre genre = new Genre();
			genre.setId(rs.getLong("id_genre"));
			genre.setTitre(rs.getString("genre_titre"));
			f.setGenre(genre);
 
			return f;
		}
	}

}
