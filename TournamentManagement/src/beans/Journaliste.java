package beans;

import javax.persistence.*;


/**
 * The persistent class for the journaliste database table.
 * 
 */
@Entity
public class Journaliste  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_journaliste")
	private Integer idJournaliste;

	private String nom;

	public Journaliste() {
	}

	public Integer getIdJournaliste() {
		return this.idJournaliste;
	}

	public void setIdJournaliste(Integer idJournaliste) {
		this.idJournaliste = idJournaliste;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}