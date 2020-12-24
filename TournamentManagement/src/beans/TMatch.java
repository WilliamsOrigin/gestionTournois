package beans;

import java.io.Serializable;
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
@NamedQuery(name="TMatch.findAll", query="SELECT t FROM TMatch t")
public class TMatch  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_match")
	private Integer idMatch;

	private Integer categorie;

	private Integer court;

	private Time heuredb;

	private Time heurefin;

	@Temporal(TemporalType.DATE)
	private Date jour;

	private String score;

	private Integer sets;

	private Integer statut;

	//bi-directional many-to-one association to Appartenir
	@OneToMany(mappedBy="TMatch")
	private List<Appartenir> appartenirs;

	public TMatch() {
	}

	public Integer getIdMatch() {
		return this.idMatch;
	}

	public void setIdMatch(Integer idMatch) {
		this.idMatch = idMatch;
	}

	public Integer getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}

	public Integer getCourt() {
		return this.court;
	}

	public void setCourt(Integer court) {
		this.court = court;
	}

	public Time getHeuredb() {
		return this.heuredb;
	}

	public void setHeuredb(Time heuredb) {
		this.heuredb = heuredb;
	}

	public Time getHeurefin() {
		return this.heurefin;
	}

	public void setHeurefin(Time heurefin) {
		this.heurefin = heurefin;
	}

	public Date getJour() {
		return this.jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Integer getSets() {
		return this.sets;
	}

	public void setSets(Integer sets) {
		this.sets = sets;
	}

	public Integer getStatut() {
		return this.statut;
	}

	public void setStatut(Integer statut) {
		this.statut = statut;
	}

	public List<Appartenir> getAppartenirs() {
		return this.appartenirs;
	}

	public void setAppartenirs(List<Appartenir> appartenirs) {
		this.appartenirs = appartenirs;
	}

	public Appartenir addAppartenir(Appartenir appartenir) {
		getAppartenirs().add(appartenir);
		appartenir.setTMatch(this);

		return appartenir;
	}

	public Appartenir removeAppartenir(Appartenir appartenir) {
		getAppartenirs().remove(appartenir);
		appartenir.setTMatch(null);

		return appartenir;
	}

}