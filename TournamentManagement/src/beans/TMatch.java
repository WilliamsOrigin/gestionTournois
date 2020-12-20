package beans;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the t_match database table.
 * 
 */
@Entity
@Table(name="t_match")
public class TMatch  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_match")
	private Integer idMatch;

	private String categorie;

	private String courtdeterrain;

	private Time duree;

	@Temporal(TemporalType.DATE)
	private Date heure;

	private Integer nombredeset;

	private Integer score;

	private String statut;

	//bi-directional many-to-one association to Soustournoi
	@OneToMany(mappedBy="TMatch")
	private List<Soustournoi> soustournois;

	//bi-directional many-to-one association to Joueur
	@ManyToOne
	@JoinColumn(name="id_joueur")
	private Joueur joueur;

	public TMatch() {
	}

	public Integer getIdMatch() {
		return this.idMatch;
	}

	public void setIdMatch(Integer idMatch) {
		this.idMatch = idMatch;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getCourtdeterrain() {
		return this.courtdeterrain;
	}

	public void setCourtdeterrain(String courtdeterrain) {
		this.courtdeterrain = courtdeterrain;
	}

	public Time getDuree() {
		return this.duree;
	}

	public void setDuree(Time duree) {
		this.duree = duree;
	}

	public Date getHeure() {
		return this.heure;
	}

	public void setHeure(Date heure) {
		this.heure = heure;
	}

	public Integer getNombredeset() {
		return this.nombredeset;
	}

	public void setNombredeset(Integer nombredeset) {
		this.nombredeset = nombredeset;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public List<Soustournoi> getSoustournois() {
		return this.soustournois;
	}

	public void setSoustournois(List<Soustournoi> soustournois) {
		this.soustournois = soustournois;
	}

	public Soustournoi addSoustournoi(Soustournoi soustournoi) {
		getSoustournois().add(soustournoi);
		soustournoi.setTMatch(this);

		return soustournoi;
	}

	public Soustournoi removeSoustournoi(Soustournoi soustournoi) {
		getSoustournois().remove(soustournoi);
		soustournoi.setTMatch(null);

		return soustournoi;
	}

	public Joueur getJoueur() {
		return this.joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

}