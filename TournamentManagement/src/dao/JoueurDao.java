package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import beans.Joueur;


public class JoueurDao {

	private EntityManager em;

	public JoueurDao(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Joueur> findAllPlayers() {
		return em.createQuery("select j from Joueur j order by j.classementmondial").getResultList();
	}

	public Joueur findPlayer(int id) {
		return em.find(Joueur.class, id);
	}

	public Joueur findPlayerByName(String name) {
		TypedQuery<Joueur> query = em.createQuery("select j from Joueur j where j.nom = :name", Joueur.class);
		List<Joueur> joueurs = query.setParameter("name", name).getResultList();

		if (joueurs.size() > 0) 
			return joueurs.get(0);

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Joueur> findSelectedPlayers() {
		return em.createQuery("select j from Joueur j where j.isselected = 0")
				.getResultList();
	}

	public boolean existRanking(int rank, String sexe) {
		TypedQuery<Joueur> query = em.createQuery("select j from Joueur j where j.classementmondial = :rank "
				+ "AND j.sexe = :sexe", Joueur.class);
		List<Joueur> joueurs = query.setParameter("rank", rank)
				.setParameter("sexe", sexe).getResultList();

		return joueurs.size() > 0;
	}

	public boolean existName(String name) {
		TypedQuery<Joueur> query = em.createQuery("select j from Joueur j where j.nom = :name", Joueur.class);
		List<Joueur> joueurs = query.setParameter("name", name).getResultList();

		return joueurs.size() > 0;
	}

	public void addPlayer(Joueur Player) {
		em.getTransaction().begin();
		em.persist(Player);
		em.flush();
		em.getTransaction().commit();
	}

	public void updatePlayer(Joueur customPlayer) {
		Joueur player = findPlayer(customPlayer.getIdJoueur());
		if (player != null) {
			em.getTransaction().begin();
			player.replaceBy(customPlayer);
			em.getTransaction().commit();
		}
	}

	public void deletePlayer(int id) {
		Joueur Player = findPlayer(id);
		if (Player != null) {
			em.getTransaction().begin();
			em.remove(Player);
			em.getTransaction().commit();
		}
	}

}
