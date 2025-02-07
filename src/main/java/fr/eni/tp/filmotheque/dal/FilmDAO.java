package fr.eni.tp.filmotheque.dal;

import java.util.List;

import fr.eni.tp.filmotheque.bo.Film;

public interface FilmDAO {
	
	public void create(Film film);
	
	public Film read(long id);
	
	public List<Film> findAll();
	
	public String findTitre(long id);

}
