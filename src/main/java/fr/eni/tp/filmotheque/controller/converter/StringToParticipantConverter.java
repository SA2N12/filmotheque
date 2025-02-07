package fr.eni.tp.filmotheque.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Participant;

@Component
public class StringToParticipantConverter implements Converter<String, Participant> {

	private FilmService filmService;

	public StringToParticipantConverter(FilmService filmService) {
		this.filmService = filmService;
	}

	@Override
	public Participant convert(String idParticipant) {
		return this.filmService.consulterParticipantParId(Long.parseLong(idParticipant));
	}

}