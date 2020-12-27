package beans;

import javax.persistence.*;


/**
 * The persistent class for the joueur database table.
 * 
 */
@Entity
@NamedQuery(name="Joueur.findAll", query="SELECT j FROM Joueur j")
public class Joueur  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_joueur")
	private Integer idJoueur;

	private Integer classementmondial;

	private String description;

	private String image;

	private Integer isselected;

	private String nationalite;

	private String nom;

	private String sexe;

	public Joueur() {
	}
	
	public Joueur(Integer classementmondial, String description, String image, String nationalite, String nom,
			String sexe) {
		this.classementmondial = classementmondial;
		this.description = description;
		this.image = image;
		this.nationalite = nationalite;
		this.nom = nom;
		this.sexe = sexe;
		this.isselected = 0;
	}
	
	public void replaceBy(Joueur j) {
		this.classementmondial = j.classementmondial;
		this.description = j.description;
		this.image = j.image;
		this.nationalite = j.nationalite;
		this.nom = j.nom;
		this.sexe = j.sexe;
	}

	public Integer getIdJoueur() {
		return this.idJoueur;
	}

	public void setIdJoueur(Integer idJoueur) {
		this.idJoueur = idJoueur;
	}

	public Integer getClassementmondial() {
		return this.classementmondial;
	}

	public void setClassementmondial(Integer classementmondial) {
		this.classementmondial = classementmondial;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIsselected() {
		return this.isselected;
	}

	public void setIsselected(Integer isselected) {
		this.isselected = isselected;
	}

	public String getNationalite() {
		return this.nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

}