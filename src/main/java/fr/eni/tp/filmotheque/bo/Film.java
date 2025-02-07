package fr.eni.tp.filmotheque.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Film {
	
	//attributs
	
	private long id;
	
	
	@NotEmpty
	@Size(max=250)
	private String titre;
	
	@Min(1900)
	private int annee;
	
	@Min(value = 1)
	private int duree;
	
	@Size(min=20, max=250)
	private String synopsis;
	
	@NotNull
	private Participant realisateur;
	
	private List<Participant> acteurs = new ArrayList<>();
	
	@NotNull
	private Genre genre;
	
	private List<Avis> avis = new ArrayList<>();
	
	//constructor
	//par défaut
	public Film() {
		
	}
	
	//tous les paramètres
	public Film(long id, String titre, int annee, int duree, String synopsis) {
		this.id = id;
		this.titre = titre;
		this.annee = annee;
		this.duree = duree;
		this.synopsis = synopsis;
	}
	
	//getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Participant getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(Participant realisateur) {
		this.realisateur = realisateur;
	}

	public List<Participant> getActeurs() {
		return acteurs;
	}

	public void setActeurs(List<Participant> acteurs) {
		this.acteurs = acteurs;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Avis> getAvis() {
		return avis;
	}

	public void setAvis(List<Avis> avis) {
		this.avis = avis;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", annee=" + annee + ", duree=" + duree + ", synopsis="
				+ synopsis + ", realisateur=" + realisateur + ", acteurs=" + acteurs + ", genre=" + genre + ", avis="
				+ avis + "]";
	}
	
}
