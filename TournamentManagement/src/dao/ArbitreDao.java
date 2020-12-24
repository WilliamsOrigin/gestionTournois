package dao;

import java.util.List;

import javax.persistence.EntityManager;

import beans.Arbitre;


public class ArbitreDao {
	
	private EntityManager em;

	public ArbitreDao(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Arbitre> findAllReferees() {
		return em.createQuery("select a from arbitre a").getResultList();
	}
	
	public Arbitre findReferee(int id) {
		return em.find(Arbitre.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Arbitre> findSelectedReferees(int isSelected) {
		return em.createQuery("select a from arbitre a where a.isselected = :selected")
				.setParameter("selected", isSelected).getResultList();
	}
	
	public void addReferee(Arbitre Referee) {
		em.getTransaction().begin();
		em.persist(Referee);
		em.getTransaction().commit();
	}
	
	public void updateReferee(Arbitre customReferee) {
		em.getTransaction().begin();
		em.merge(customReferee);
		em.getTransaction().commit();
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
