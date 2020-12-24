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
		return em.createQuery("select j from Joueur j").getResultList();
	}
	
	public Joueur findPlayer(int id) {
		return em.find(Joueur.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Joueur> findSelectedPlayers(int isSelected) {
		return em.createQuery("select j from Joueur j where j.isselected = :selected")
				.setParameter("selected", isSelected).getResultList();
	}
	
	public boolean existRanking(int rank) {
		TypedQuery<Joueur> query = em.createQuery("select j from Joueur j where j.classementmondial = :rank", Joueur.class);
		Joueur player = query.setParameter("rank", rank).getSingleResult();
		if (player != null)
			return true;
		return false;
	}
	
	public boolean existName(String name) {
		TypedQuery<Joueur> query = em.createQuery("select j from Joueur j where j.name = :name", Joueur.class);
		Joueur player = query.setParameter("name", name).getSingleResult();
		if (player != null)
			return true;
		return false;
	}
	
	public void addPlayer(Joueur Player) {
		em.getTransaction().begin();
		em.persist(Player);
		em.getTransaction().commit();
	}
	
	public void updatePlayer(Joueur customPlayer) {
		em.getTransaction().begin();
		em.merge(customPlayer);
		em.getTransaction().commit();
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
