package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.Utilisateur;

public class UtilisateurDao {

	private EntityManager em;
	
	public UtilisateurDao(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllUsers() {
		Query query = em.createQuery("select u from Utilisateur u");
		return query.getResultList();
	}
	
	public Utilisateur findUser(int id) {
		return em.find(Utilisateur.class, id);
	}
	
	public void deleteUser(int id) {
		Utilisateur user = findUser(id);
		if (user != null) {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}
	}
	
	public void addUser(Utilisateur user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
}
