package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import beans.Appartenir;
import beans.Equipe;
import beans.Joueur;


public class EquipeDao {
	
	private EntityManager em;
	
	private JoueurDao joueurDao;

	public EquipeDao(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipe> findAllTeams() {
		return em.createQuery("select e from Equipe e").getResultList();
	}
	
	public Equipe findTeam(int id) {
		return em.find(Equipe.class, id);
	}
	
	public void addTeam(Equipe Team) {
		em.getTransaction().begin();
		em.persist(Team);
		em.getTransaction().commit();
	}
	
	public void updateTeam(Equipe customTeam) {
		em.getTransaction().begin();
		em.merge(customTeam);
		em.getTransaction().commit();
	}

	public void deleteTeam(int id) {
		Equipe Team = findTeam(id);
		if (Team != null) {
			em.getTransaction().begin();
			em.remove(Team);
			em.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Joueur> getPlayersAndPartners(Equipe equipe) {
		List<Joueur> players = new ArrayList<Joueur>();
		players.add(joueurDao.findPlayer(equipe.getIdJoueur()));
		players.add(joueurDao.findPlayer(equipe.getIdEquipier()));
		
		return players;
	}
		
	
}
