package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import beans.Arbitre;
import beans.TMatch;


public class ArbitreDao {
	
	private EntityManager em;

	public ArbitreDao(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Arbitre> findAllReferees() {
		return em.createQuery("select a from Arbitre a").getResultList();
	}
	
	public Arbitre findReferee(int id) {
		return em.find(Arbitre.class, id);
	}
	
	public Arbitre findRefereeByName(String name) {
		TypedQuery<Arbitre> query = em.createQuery("select a from Arbitre a where a.nom = :name", Arbitre.class);
		List<Arbitre> Arbitres = query.setParameter("name", name).getResultList();
		
		if (Arbitres.size() > 0) 
			return Arbitres.get(0);
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Arbitre> findSelectedReferees() {
		return em.createQuery("select a from Arbitre a where a.isselected = 0")
				 .getResultList();
	}
	
	public boolean existName(String name) {
		TypedQuery<Arbitre> query = em.createQuery("select a from Arbitre a where a.nom = :name", Arbitre.class);
		List<Arbitre> Arbitres = query.setParameter("name", name).getResultList();
		
		return Arbitres.size() > 0;
	}
	
	public boolean isDeletable(int id) {
		TypedQuery<TMatch> query = em.createQuery("select m from TMatch m, Arbitre a WHERE a.idArbitre = m.arbitre.idArbitre AND a.idArbitre = :id", TMatch.class);
		List<TMatch> matchs = query.setParameter("id", id).getResultList();
		
		if (matchs.size() > 0)
			return false;
		return true;
	}
	
	public void addReferee(Arbitre referee) {
		em.getTransaction().begin();
		em.persist(referee);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void updateReferee(Arbitre customReferee) {
		Arbitre referee = findReferee(customReferee.getIdArbitre());
		if (referee != null) {
			em.getTransaction().begin();
			referee.replaceBy(customReferee);
			em.getTransaction().commit();
		}
	}

	public void deleteReferee(int id) {
		Arbitre Referee = findReferee(id);
		if (Referee != null) {
			em.getTransaction().begin();
			em.remove(Referee);
			em.getTransaction().commit();
		}
	}
	
}
