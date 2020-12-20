package beans;

import javax.persistence.*;


/**
 * The persistent class for the arbitre database table.
 * 
 */
@Entity
public class Arbitre  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_arbitre")
	private Integer idArbitre;

	private String nationalite;

	private String nom;

	public Arbitre() {
	}

	public Integer getIdArbitre() {
		return this.idArbitre;
	}

	public void setIdArbitre(Integer idArbitre) {
		this.idArbitre = idArbitre;
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