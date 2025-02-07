package fr.eni.tp.filmotheque.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.GenreDAO;
import fr.eni.tp.filmotheque.dal.FilmDAO;
import fr.eni.tp.filmotheque.dal.ParticipantDAO;
import fr.eni.tp.filmotheque.exception.BusinessException;

@Profile("dev2")
@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	private GenreDAO genreDAO;
	
	@Autowired
	private FilmDAO filmDAO;
	
	@Autowired
	private ParticipantDAO participantDAO;

	@Override
	public List<Film> consulterFilms() {
		return filmDAO.findAll();
	}

	@Override
	public Film consulterFilmParId(long id) {
		return filmDAO.read(id);
	}

	@Override
	public List<Genre> consulterGenres() {
		return genreDAO.findAll();
	}

	@Override
	public Genre consulterGenreParId(long id) {
		return genreDAO.read(id);
	}

	@Override
	public List<Participant> consulterParticipants() {
		return participantDAO.findAll();
	}

	@Override
	public Participant consulterParticipantParId(long id) {
		return participantDAO.read(id);
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void creerFilm(Film film) throws BusinessException{
		//test erreur
		// film.getGenre().setId(20L);
		// film.getActeurs().add(0, new Participant(1, "", ""));
		
		//determiner si la création du film est valide
		BusinessException be = new BusinessException();
		
		//ajouyer les booleans des exceptions à la business exception
		boolean valide = validerGenre(film.getGenre().getId(), be);
		valide &= validerListeParticipants(film.getActeurs(), be);
		valide &= validerParticipant(film.getRealisateur(), be);
		
		//poster le film en fonction de sa validité ou non
		try {
			if(valide) {
				filmDAO.create(film);
			}else {
				throw be;
			}
		}catch(Exception e) {
			e.printStackTrace();
			be.addCleErreur("erreur.film.creation");
			throw be;
		}
	}
	
	private boolean validerGenre(Long idGenre, BusinessException be) {
		boolean genreValide = genreDAO.validerGenreExiste(idGenre);

		if(!genreValide) {
			be.addCleErreur("validation.film.genre");
		}
		
		return genreValide;
	}
	
	private boolean validerListeParticipants(List<Participant> listeParticipants, BusinessException be) {
		boolean listeParticipantValide = participantDAO.validerListeParticipantsExiste(listeParticipants);
		
		if(!listeParticipantValide) {
			be.addCleErreur("validation.film.acteurs");
		}
		
		return listeParticipantValide;
	}

	private boolean validerParticipant(Participant participant, BusinessException be){
		boolean participantValide = participantDAO.validerParticipantExiste(participant);

		if(!participantValide){
			be.addCleErreur("validation.film.realisateur");
		}

		return participantValide;
	}

}
