package beans;

import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to TMatch
	@OneToMany(mappedBy="arbitre")
	private List<TMatch> TMatches;

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
		this.isselected = a.isselected;
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

	public List<TMatch> getTMatches() {
		return this.TMatches;
	}

	public void setTMatches(List<TMatch> TMatches) {
		this.TMatches = TMatches;
	}

	public TMatch addTMatch(TMatch TMatch) {
		getTMatches().add(TMatch);
		TMatch.setArbitre(this);

		return TMatch;
	}

	public TMatch removeTMatch(TMatch TMatch) {
		getTMatches().remove(TMatch);
		TMatch.setArbitre(null);

		return TMatch;
	}

}