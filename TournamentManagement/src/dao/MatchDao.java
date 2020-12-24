package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import beans.TMatch;

public class MatchDao {

	private EntityManager em;

	public MatchDao(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<TMatch> findAllGames() {
		return em.createQuery("select m from t_match m").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TMatch> findAllGamesByCategory(int categorie) {
		return em.createQuery("select m from t_match m where m.categorie = :cat")
				.setParameter("cat", categorie).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TMatch> findAllGamesByStatus(int statut) {
		return em.createQuery("select m from t_match m where m.statut = :status")
				.setParameter("status", statut).getResultList();
	}

	public TMatch findGame(int court) {
		TypedQuery<TMatch> query = em.createQuery("select m from t_match m where m.court = :court", TMatch.class);
		return query.setParameter("court", court).getSingleResult();
	}
	
	public TMatch findGameById(int id) {
		return em.find(TMatch.class, id);
	}
	
	public void addGame(TMatch game) {
		em.getTransaction().begin();
		if (game.getCategorie() <= 2) 
			game.setSets(3);
		else
			game.setSets(5);
		em.persist(game);
		em.getTransaction().commit();
	}
	
	public void updateGame(TMatch updatedGame) {
		em.getTransaction().begin();
		em.merge(updatedGame);
		em.getTransaction().commit();
	}
	
	public void deleteGame(int idGame) {
		TMatch game = findGameById(idGame);
		if (game != null) {
			em.getTransaction().begin();
			em.remove(game);
			em.getTransaction().commit();
		}
	}
}
