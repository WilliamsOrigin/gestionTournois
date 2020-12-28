package beans;

import javax.persistence.*;


/**
 * The persistent class for the appartenir database table.
 * 
 */
@Entity
@NamedQuery(name="Appartenir.findAll", query="SELECT a FROM Appartenir a")
public class Appartenir  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	public Appartenir(Integer categorie, Integer idEquipe, Integer idJoueur, beans.TMatch tMatch) {
		this.categorie = categorie;
		this.idEquipe = idEquipe;
		this.idJoueur = idJoueur;
		TMatch = tMatch;
	}

	public void replaceBy(Appartenir ap) {
		this.categorie = ap.categorie;
		this.idEquipe = ap.idEquipe;
		this.idJoueur = ap.idJoueur;
		TMatch = ap.getTMatch();
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