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
		return em.createQuery("select m from TMatch m").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TMatch> findAllGamesByCategory(int categorie) {
		return em.createQuery("select m from TMatch m where m.categorie = :cat")
				.setParameter("cat", categorie).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TMatch> findAllGamesByStatus(int statut) {
		return em.createQuery("select m from TMatch m where m.statut = :status")
				.setParameter("status", statut).getResultList();
	}
	
	public boolean isCourtNotDisponible(int court) {
		TypedQuery<TMatch> query = em.createQuery("select m from TMatch m where m.statut = 0 and m.court = :court", TMatch.class);
		List<TMatch> matchs = query.setParameter("court", court).getResultList();

		return matchs.size() > 0;
	}

	public TMatch findGame(int court) {
		TypedQuery<TMatch> query = em.createQuery("select m from TMatch m where m.court = :court", TMatch.class);
		return query.setParameter("court", court).getSingleResult();
	}
	
	public TMatch findGameById(int id) {
		return em.find(TMatch.class, id);
	}
	
	public void addGame(TMatch game) {
		em.getTransaction().begin();
		em.persist(game);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void updateGame(TMatch updatedGame) {
		TMatch m = findGameById(updatedGame.getIdMatch());
		em.getTransaction().begin();
		m.replaceBy(updatedGame);
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
