package fr.eni.tp.filmotheque.bo;

public class Genre {
	//attributs
	private long id;
	private String titre;
	
	//constructor
	//par défaut
	public Genre() {
		
	}
	
	//sans l'id
	public Genre(String titre) {
		this.titre = titre;
	}
	
	//tous les paramètres
	public Genre(long id, String titre) {
		this.titre = titre;
		this.id = id;
	}
	
	//getters et setters
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
	
	//toString
	@Override
	public String toString() {
		return "Genre [id=" + id + ", titre=" + titre + "]";
	}

}
