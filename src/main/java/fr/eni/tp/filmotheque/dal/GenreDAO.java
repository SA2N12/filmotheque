package fr.eni.tp.filmotheque.dal;

import java.util.List;

import fr.eni.tp.filmotheque.bo.Genre;

public interface GenreDAO {

	public Genre read(long id);
	
	public List<Genre> findAll();
	
	public boolean validerGenreExiste(Long idGenre);
	
}
