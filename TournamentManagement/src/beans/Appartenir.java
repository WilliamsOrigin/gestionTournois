package beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the appartenir database table.
 * 
 */
@Entity
@NamedQuery(name="Appartenir.findAll", query="SELECT a FROM Appartenir a")
public class Appartenir  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_app")
	private Integer idApp;

	private Integer categorie;

	@Column(name="id_equipe")
	private Integer idEquipe;

	@Column(name="id_joueur")
	private Integer idJoueur;

	//bi-directional many-to-one association to TMatch
	@ManyToOne
	@JoinColumn(name="id_match")
	private TMatch TMatch;

	public Appartenir() {
	}

	public Integer getIdApp() {
		return this.idApp;
	}

	public void setIdApp(Integer idApp) {
		this.idApp = idApp;
	}

	public Integer getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}

	public Integer getIdEquipe() {
		return this.idEquipe;
	}

	public void setIdEquipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
	}

	public Integer getIdJoueur() {
		return this.idJoueur;
	}

	public void setIdJoueur(Integer idJoueur) {
		this.idJoueur = idJoueur;
	}

	public TMatch getTMatch() {
		return this.TMatch;
	}

	public void setTMatch(TMatch TMatch) {
		this.TMatch = TMatch;
	}

}