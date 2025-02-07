package fr.eni.tp.filmotheque.bo;

public class Membre extends Personne {
	
	//attributs
	private String pseudo;
	private String motDePasse;
	private boolean admin = false;
	
	//constructor
	//par défaut
	public Membre() {
		super();
	}
	
	//tous les paramètres
	public Membre(String pseudo, String motDePasse, boolean admin) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.admin = admin;
	}
	
	public Membre(long id, String nom, String prenom, String pseudo, String motDePasse) {
		super(id, nom, prenom);
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
	}
	
	//getters and setters
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Membre [pseudo=" + pseudo + ", admin=" + admin + "]";
	}
	
}
