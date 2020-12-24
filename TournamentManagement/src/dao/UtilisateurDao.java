package dao;

import java.util.List;

import javax.persistence.EntityManager;

import beans.Utilisateur;

public class UtilisateurDao {

	private EntityManager em;
	
	public UtilisateurDao(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllUsers() {
		return em.createQuery("select u from Utilisateur u").getResultList();
	}
	
	public Utilisateur findUser(int id) {
		return em.find(Utilisateur.class, id);
	}
	
	public void addUser(Utilisateur user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public void updateUser(Utilisateur customUser) {
		em.getTransaction().begin();
		em.merge(customUser);
		em.getTransaction().commit();
	}
	
	public void deleteUser(int id) {
		Utilisateur user = findUser(id);
		if (user != null) {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}
	}
	
}
