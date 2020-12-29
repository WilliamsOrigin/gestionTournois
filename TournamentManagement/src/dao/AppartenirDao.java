package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	public List<Appartenir> findAllApps() {
		return em.createQuery("select a from Appartenir a", Appartenir.class).getResultList();
	}
	
	public Appartenir findApp(int id) {
		return em.find(Appartenir.class, id);
	}
	
	public Appartenir findBySimpleGame(int idG, int idJ) {
		TypedQuery<Appartenir> query = em.createQuery("select a from Appartenir a where a.TMatch.idMatch = :idm and a.idJoueur = :idj", Appartenir.class);
		List<Appartenir> aps = query.setParameter("idm", idG).setParameter("idj", idJ).getResultList();
		
		if (aps.size() > 0)
			return aps.get(0);
		
		return null;
	}
	
	public Appartenir findByDoubleGame(int idG, int idE) {
		TypedQuery<Appartenir> query = em.createQuery("select a from Appartenir a where a.TMatch.idMatch = :idm and a.idEquipe = :ide", Appartenir.class);
		List<Appartenir> aps = query.setParameter("idm", idG).setParameter("ide", idE).getResultList();
		
		if (aps.size() > 0)
			return aps.get(0);
		
		return null;
	}
	
	public void add(Appartenir App) {
		em.getTransaction().begin();
		em.persist(App);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void update(Appartenir customApp) {
		Appartenir ap;
		if (customApp.getIdEquipe() == null) {
			ap = findBySimpleGame(customApp.getTMatch().getIdMatch(), customApp.getIdJoueur());			
		}
		else
			ap = findBySimpleGame(customApp.getTMatch().getIdMatch(), customApp.getIdEquipe());
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
	
	public List<Joueur> getPlayersAndOponents(int match) {
		List<Appartenir> apps = em.createQuery("select a from Appartenir a where a.TMatch.idMatch = :idm", Appartenir.class)
				.setParameter("idm", match).getResultList();
		
		List<Joueur> players = new ArrayList<Joueur>();
		
		if (apps.size() > 0) {
			for (Appartenir a: apps) {
				players.add(joueurDao.findPlayer(a.getIdJoueur()));
			}
		}
		
		return players;
	}
	
	public List<Joueur> getTeamsAndOponents(int match) {
		List<Appartenir> apps = em.createQuery("select a from Appartenir a where a.TMatch.idMatch = :idm", Appartenir.class)
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
	
	public Map<TMatch, List<Joueur>> getInfoSimpleGame() {
		List<TMatch> games = em.createQuery("select m from TMatch m, Appartenir a"
				+ " where a.TMatch.idMatch = m.idMatch AND a.categorie <= 2", TMatch.class)
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
	
	public List<Joueur> getPlayersForSimpleGame(int idG) {
		List<Joueur> players = null;
		Map<TMatch, List<Joueur>> gamePlayers = getInfoSimpleGame();
		for (Map.Entry<TMatch, List<Joueur>> entry: gamePlayers.entrySet()) {
			if (entry.getKey().getIdMatch() == idG) {
				players = entry.getValue();
			}
		}
		return players;
	}
	
	public Map<TMatch, List<Joueur>> getInfoDoubleGame() {
		List<TMatch> games = em.createQuery("select m from TMatch m, Appartenir a"
				+ " where a.TMatch.idMatch = m.idMatch AND a.categorie > 2", TMatch.class)
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
	
	public List<Joueur> getPlayersForDoubleGame(int idG) {
		List<Joueur> players = null;
		Map<TMatch, List<Joueur>> gamePlayers = getInfoDoubleGame();
		for (Map.Entry<TMatch, List<Joueur>> entry: gamePlayers.entrySet()) {
			if (entry.getKey().getIdMatch() == idG) {
				players = entry.getValue();
			}
		}
		return players;
	}
	
	public List<Appartenir> getAllGameAp(int idM) {
		List<Appartenir> appartenirs = em.createQuery("select a from Appartenir a, TMatch m"
				+ " where a.TMatch.idMatch = m.idMatch AND m.idMatch = :idm", Appartenir.class)
				.setParameter("idm", idM).getResultList();
		return appartenirs;
	}
	
}
