package beans;

import javax.persistence.*;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
public class Utilisateur  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idutilisateur;

	private String motdepasse;

	private String pseudo;

	private Integer role;

	public Utilisateur() {
	}

	public Integer getIdutilisateur() {
		return this.idutilisateur;
	}

	public void setIdutilisateur(Integer idutilisateur) {
		this.idutilisateur = idutilisateur;
	}

	public String getMotdepasse() {
		return this.motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

}