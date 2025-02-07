package fr.eni.tp.filmotheque.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Genre;

@Component
public class StringToGenreConverter implements Converter<String, Genre> {

	private FilmService filmService;
	
	
	
	public StringToGenreConverter(FilmService filmService) {
		this.filmService = filmService;
	}



	@Override
	public Genre convert(String idGenre) {
		return this.filmService.consulterGenreParId(Long.parseLong(idGenre));
	}

}
