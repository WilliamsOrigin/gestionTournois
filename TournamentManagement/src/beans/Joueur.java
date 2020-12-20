package beans;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the joueur database table.
 * 
 */
@Entity
public class Joueur  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_joueur")
	private Integer idJoueur;

	private Integer classemmentmondial;

	private String image;

	private String nationalite;

	private String nom;

	private String sexe;

	//bi-directional many-to-one association to Equipe
	@OneToMany(mappedBy="joueur")
	private List<Equipe> equipes;

	//bi-directional many-to-one association to TMatch
	@OneToMany(mappedBy="joueur")
	private List<TMatch> TMatches;

	public Joueur() {
	}

	public Integer getIdJoueur() {
		return this.idJoueur;
	}

	public void setIdJoueur(Integer idJoueur) {
		this.idJoueur = idJoueur;
	}

	public Integer getClassemmentmondial() {
		return this.classemmentmondial;
	}

	public void setClassemmentmondial(Integer classemmentmondial) {
		this.classemmentmondial = classemmentmondial;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public List<Equipe> getEquipes() {
		return this.equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public Equipe addEquipe(Equipe equipe) {
		getEquipes().add(equipe);
		equipe.setJoueur(this);

		return equipe;
	}

	public Equipe removeEquipe(Equipe equipe) {
		getEquipes().remove(equipe);
		equipe.setJoueur(null);

		return equipe;
	}

	public List<TMatch> getTMatches() {
		return this.TMatches;
	}

	public void setTMatches(List<TMatch> TMatches) {
		this.TMatches = TMatches;
	}

	public TMatch addTMatch(TMatch TMatch) {
		getTMatches().add(TMatch);
		TMatch.setJoueur(this);

		return TMatch;
	}

	public TMatch removeTMatch(TMatch TMatch) {
		getTMatches().remove(TMatch);
		TMatch.setJoueur(null);

		return TMatch;
	}

}