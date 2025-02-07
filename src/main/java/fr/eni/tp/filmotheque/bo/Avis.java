package fr.eni.tp.filmotheque.bo;

public class Avis {
	
	//attributs
	private long id;
	private int note;
	private String commentaire;
	private Membre membre;
	
	//constructor
	//par défaut
	public Avis() {
		
	}
	
	//sans id
	public Avis(int note, String commentaire, Membre membre) {
		this.note = note;
		this.commentaire = commentaire;
		this.membre = membre;
	}

	//tous les paramètres
	public Avis(long id, int note, String commentaire, Membre membre) {
		this.id = id;
		this.note = note;
		this.commentaire = commentaire;
		this.membre = membre;
	}
	
	//getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	@Override
	public String toString() {
		return "Avis [id=" + id + ", note=" + note + ", commentaire=" + commentaire + ", membre=" + membre + "]";
	}
	
}
