package beans;

import javax.persistence.*;


/**
 * The persistent class for the soustournoi database table.
 * 
 */
@Entity
public class Soustournoi  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_soust")
	private Integer idSoust;

	private String categorie;

	//bi-directional many-to-one association to TMatch
	@ManyToOne
	@JoinColumn(name="id_match")
	private TMatch TMatch;

	public Soustournoi() {
	}

	public Integer getIdSoust() {
		return this.idSoust;
	}

	public void setIdSoust(Integer idSoust) {
		this.idSoust = idSoust;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public TMatch getTMatch() {
		return this.TMatch;
	}

	public void setTMatch(TMatch TMatch) {
		this.TMatch = TMatch;
	}

}