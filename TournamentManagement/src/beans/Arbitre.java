package beans;

import javax.persistence.*;


/**
 * The persistent class for the arbitre database table.
 * 
 */
@Entity
@NamedQuery(name="Arbitre.findAll", query="SELECT a FROM Arbitre a")
public class Arbitre  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_arbitre")
	private Integer idArbitre;

	private String description;

	private Integer isselected;

	private String nationalite;

	private String nom;

	public Arbitre() {
	}
	
	public Arbitre(String description, String nationalite, String nom) {
		this.description = description;
		this.nationalite = nationalite;
		this.nom = nom;
		this.isselected = 0;
	}

	public void replaceBy(Arbitre a) {
		this.description = a.description;
		this.nationalite = a.nationalite;
		this.nom = a.nom;
	}

	public Integer getIdArbitre() {
		return this.idArbitre;
	}

	public void setIdArbitre(Integer idArbitre) {
		this.idArbitre = idArbitre;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}