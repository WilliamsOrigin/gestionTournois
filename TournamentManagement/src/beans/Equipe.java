package beans;

import javax.persistence.*;


/**
 * The persistent class for the equipe database table.
 * 
 */
@Entity
public class Equipe  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_equipe")
	private Integer idEquipe;

	//bi-directional many-to-one association to Joueur
	@ManyToOne
	@JoinColumn(name="id_joueur")
	private Joueur joueur;

	public Equipe() {
	}

	public Integer getIdEquipe() {
		return this.idEquipe;
	}

	public void setIdEquipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
	}

	public Joueur getJoueur() {
		return this.joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

}