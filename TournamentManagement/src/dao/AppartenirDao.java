package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import beans.Appartenir;
import beans.Equipe;
import beans.Joueur;
import beans.TMatch;

public class AppartenirDao {

	private EntityManager em;
	
	private JoueurDao joueurDao;
	
	private EquipeDao equipeDao;
	
	public AppartenirDao(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
		joueurDao = new JoueurDao(em);
		equipeDao = new EquipeDao(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Appartenir> findAllApps() {
		return em.createQuery("select a from Appartenir a").getResultList();
	}
	
	public Appartenir findApp(int id) {
		return em.find(Appartenir.class, id);
	}
	
	public void add(Appartenir App) {
		em.getTransaction().begin();
		em.persist(App);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void update(Appartenir customApp) {
		Appartenir ap = findApp(customApp.getIdApp());
		em.getTransaction().begin();
		ap.replaceBy(customApp);
		em.getTransaction().commit();
	}
	
	public void delete(int id) {
		Appartenir App = findApp(id);
		if (App != null) {
			em.getTransaction().begin();
			em.remove(App);
			em.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Joueur> getPlayersAndOponents(int match) {
		List<Appartenir> apps = em.createQuery("select a from Appartenir a where a.TMatch.idMatch = :idm")
				.setParameter("idm", match).getResultList();
		
		List<Joueur> players = new ArrayList<Joueur>();
		
		if (apps.size() > 0) {
			for (Appartenir a: apps) {
				players.add(joueurDao.findPlayer(a.getIdJoueur()));
			}
		}
		
		return players;
	}
	
	@SuppressWarnings("unchecked")
	public List<Joueur> getTeamsAndOponents(int match) {
		List<Appartenir> apps = em.createQuery("select a from Appartenir a where a.TMatch.idMatch = :idm")
				.setParameter("idm", match).getResultList();
		
		List<Equipe> teams = new ArrayList<Equipe>();
		List<Joueur> players = new ArrayList<Joueur>();
		
		if (apps.size() > 0) {
			for (Appartenir a: apps) {
				teams.add(equipeDao.findTeam(a.getIdEquipe()));
			}
			if (teams.size() > 0) {
				for (Equipe e: teams) {
					players.add(joueurDao.findPlayer(e.getIdJoueur()));
					players.add(joueurDao.findPlayer(e.getIdEquipier()));
				}
			}
		}
		return players;
	}
	
	@SuppressWarnings("unchecked")
	public Map<TMatch, List<Joueur>> getInfoSimpleGame() {
		List<TMatch> games = em.createQuery("select m from TMatch m, Appartenir a"
				+ " where a.TMatch.idMatch = m.idMatch AND a.categorie <= 2")
				.getResultList();
		Map<TMatch, List<Joueur>> hm = new HashMap<TMatch, List<Joueur>>();
		
		if (games.size() > 0) {
			for (TMatch g: games) {
				List<Joueur> players = this.getPlayersAndOponents(g.getIdMatch());
				if (players != null)
					hm.put(g, players);
			}
		}
		
		return hm;
	}
	
	@SuppressWarnings("unchecked")
	public Map<TMatch, List<Joueur>> getInfoDoubleGame() {
		List<TMatch> games = em.createQuery("select m from TMatch m, Appartenir a"
				+ " where a.TMatch.idMatch = m.idMatch AND a.categorie > 2")
				.getResultList();
		Map<TMatch, List<Joueur>> hm = new HashMap<TMatch, List<Joueur>>();
		if (games.size() > 0) {
			for (TMatch g: games) {
				List<Joueur> players = this.getTeamsAndOponents(g.getIdMatch());
				if (players != null)
					hm.put(g, players);
			}
		}
		return hm;
	}
	
}
