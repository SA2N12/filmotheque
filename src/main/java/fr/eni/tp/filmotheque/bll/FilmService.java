package fr.eni.tp.filmotheque.bll;

import java.util.List;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.exception.BusinessException;

public interface FilmService  {
	
	//méthodes
	public List<Film> consulterFilms();
	
	public Film consulterFilmParId(long id);
	
	public List<Genre> consulterGenres();
	
	public Genre consulterGenreParId(long id);
	
	public List<Participant> consulterParticipants();
	
	public Participant consulterParticipantParId(long id);
	
	public void creerFilm(Film film) throws BusinessException;

}
