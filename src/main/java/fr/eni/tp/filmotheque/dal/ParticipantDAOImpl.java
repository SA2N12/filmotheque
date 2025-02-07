package fr.eni.tp.filmotheque.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.tp.filmotheque.bo.Participant;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO {
	
	//requêtes sql en private static final
	private static final String SELECT_BY_ID = "select id, nom, prenom from participant where id=:idParticipant";
	private static final String SELECT_ALL = "select id, nom, prenom from participant";
	private static final String COUNT_FOR_ACTEUR = "SELECT COUNT(*) FROM PARTICIPANT WHERE id IN (:listeParticipants)";
	private static final String COUNT_FOR_REAL = "SELECT COUNT(*) FROM PARTICIPANT WHERE id=:participant";
	
	//instanciation de namedParameterJdbcTemplate
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	//constructeur
	public ParticipantDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Participant read(long id) {
		//instancier un mapsqlparametersource namedParameters
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		//ajouter valeur au namedparameters
		namedParameters.addValue("idParticipant", id);
		
		//namedParameterJdbcTemplate
		return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, new BeanPropertyRowMapper<>(Participant.class));
	}

	@Override
	public List<Participant> findAll() {
		//namedParameterJdbcTemplate
		return namedParameterJdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Participant.class));
	}

	@Override
	public boolean validerListeParticipantsExiste(List<Participant> listeParticipants) {
		List<Long> listeIds = listeParticipants.stream().map(Participant::getId).toList();//renvoie la liste sous forme d'id des participants
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("listeParticipants", listeIds);
		
		int nbParticipants = namedParameterJdbcTemplate.queryForObject(COUNT_FOR_ACTEUR, map, Integer.class);
		return listeIds.size() == nbParticipants; 
	}

	@Override
	public boolean validerParticipantExiste(Participant participant) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("participant", participant.getId());

		int participantExiste = namedParameterJdbcTemplate.queryForObject(COUNT_FOR_REAL, map, Integer.class);
		System.out.println(participantExiste);
		return participantExiste > 0;
	}
	
}
