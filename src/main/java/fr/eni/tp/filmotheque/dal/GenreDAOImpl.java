package fr.eni.tp.filmotheque.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.tp.filmotheque.bo.Genre;

@Repository
public class GenreDAOImpl implements GenreDAO {
	
	private static final String SELECT_BY_ID = "select id, titre from genre where id=:idGenre";
	private static final String SELECT_ALL = "select id, titre from genre";
	private static final String COUNT = "SELECT COUNT(*) FROM GENRE WHERE id = :idGenre";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public GenreDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Genre read(long idGenre) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idGenre", idGenre);
		return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, new BeanPropertyRowMapper<>(Genre.class));
	}

	@Override
	public List<Genre> findAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Genre.class));
	}

	@Override
	public boolean validerGenreExiste(Long idGenre) {
	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    namedParameters.addValue("idGenre", idGenre);
	    
	    int count = namedParameterJdbcTemplate.queryForObject(COUNT, namedParameters, Integer.class);
	    return count > 0;
	}

}
