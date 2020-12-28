package beans;

import javax.persistence.*;


/**
 * The persistent class for the equipe database table.
 * 
 */
@Entity
@NamedQuery(name="Equipe.findAll", query="SELECT e FROM Equipe e")
public class Equipe  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_equipe")
	private Integer idEquipe;

	@Column(name="id_equipier")
	private Integer idEquipier;

	@Column(name="id_joueur")
	private Integer idJoueur;

	public Equipe() {
	}

	public Equipe(Integer idEquipier, Integer idJoueur) {
		this.idEquipier = idEquipier;
		this.idJoueur = idJoueur;
	}
	
	public void replaceBy(Equipe e) {
		this.idEquipier = e.idEquipier;
		this.idJoueur = e.idJoueur;
	}

	public Integer getIdEquipe() {
		return this.idEquipe;
	}

	public void setIdEquipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
	}

	public Integer getIdEquipier() {
		return this.idEquipier;
	}

	public void setIdEquipier(Integer idEquipier) {
		this.idEquipier = idEquipier;
	}

	public Integer getIdJoueur() {
		return this.idJoueur;
	}

	public void setIdJoueur(Integer idJoueur) {
		this.idJoueur = idJoueur;
	}

}