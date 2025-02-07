package fr.eni.tp.filmotheque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import jakarta.validation.Valid;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.exception.BusinessException;

@Controller
@SessionAttributes({"genreListe", "participantListe"})
public class FilmController {
	
	//attributs
	private FilmService filmService;

	//constructor
	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}
	
	//routage
	//afficher liste de films
	@GetMapping("/films")
	public String afficherFilms(Model model) {
		List<Film> listeFilms = filmService.consulterFilms();
		model.addAttribute("listeFilms", listeFilms);
		
		return("view-films");
	}
	
	//afficher detail du film
	@GetMapping("films/detail")
	public String afficherFilm(@RequestParam("id") long id, Model model) {
		Film film = filmService.consulterFilmParId(id);
		
		model.addAttribute("film", film);
		
		return("view-film-detail");	
	}
	
	//afficher creer
	@GetMapping("/creer")
	public String afficherCreer(Model model) {
		//récupérer les listes depuis la session
		model.addAttribute("film", new Film());
		
		return("view-creer");
	}

	//récupérer creer
	@PostMapping("/creer")
	public String creerFilm(@Valid @ModelAttribute ("film") Film film, BindingResult result) {
		if (!result.hasErrors()){
			try{
				filmService.creerFilm(film);
				return("redirect:/films");
			}catch (BusinessException e){
				e.printStackTrace();
				e.getClesErreurs().forEach(cle ->{
					ObjectError error = new ObjectError("globalError", cle);
					result.addError(error);
				});
				return("view-creer");
			}
		}else {
			return("view-creer");
		}
	}
	
	//créer une trace en session
	//liste de genre
	@ModelAttribute("genreListe")
	public List<Genre> genreListe(){
		List<Genre> liste = filmService.consulterGenres();
		
		return liste;
	}
	
	//liste de participants
	@ModelAttribute("participantListe")
	public List<Participant> realisateurListe(){
		List<Participant> participantListe = filmService.consulterParticipants();
		
		return participantListe;
	}
	
}
