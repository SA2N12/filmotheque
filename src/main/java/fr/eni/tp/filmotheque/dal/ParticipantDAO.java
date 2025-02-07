package fr.eni.tp.filmotheque.dal;

import java.util.List;

import fr.eni.tp.filmotheque.bo.Participant;

public interface ParticipantDAO {
	
	public Participant read(long id);
	
	public List<Participant> findAll();
	
	public boolean validerListeParticipantsExiste(List<Participant> listeParticipants);

	public boolean validerParticipantExiste(Participant participant);
	
}
