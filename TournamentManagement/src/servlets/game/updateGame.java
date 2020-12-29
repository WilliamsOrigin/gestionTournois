package servlets.game;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Joueur;
import beans.TMatch;
import beans.Utilisateur;
import dao.AppartenirDao;
import dao.ArbitreDao;
import dao.JoueurDao;
import dao.MatchDao;

public class updateGame extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private JoueurDao joueurDao = new JoueurDao(em);
	private ArbitreDao arbitreDao = new ArbitreDao(em);
	private AppartenirDao appartenirDao = new AppartenirDao(em);
	private MatchDao matchDao = new MatchDao(em);

	String error;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() == 0) {
				List<TMatch> games = matchDao.findAllGames();
				request.setAttribute("games", games);

				this.getServletContext().getRequestDispatcher("/updateGame.jsp")
				.forward(request, response);							
			}
			response.sendRedirect("http://localhost:8080/TournamentManagement/home");
		}
		else
			response.sendRedirect("http://localhost:8080/TournamentManagement/loginPage.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() == 0) {
				String error = "";
				int idGame = Integer.parseInt(request.getParameter("idmatch"));
				TMatch currentGame = matchDao.findGameById(idGame);
				String jour = request.getParameter("jour");
				Date jourMatch = null;
				String strHeuredb = request.getParameter("heureDeb");
				Time heureDb;
				String strHeurefn = request.getParameter("heureFin");
				Time heureFn;
				int statut = Integer.parseInt(request.getParameter("statut").trim());
				String strcourt = request.getParameter("court");
				int court;
				String score = request.getParameter("score");
				List<Joueur> players;
				List<TMatch> games = matchDao.findAllGames();

				if (currentGame.getCategorie() < 2) 
					players = appartenirDao.getPlayersForSimpleGame(idGame);
				else 
					players = appartenirDao.getPlayersForDoubleGame(idGame);

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				if (jour.isEmpty()) 
					jourMatch = currentGame.getJour();
				else {
					try {
						jourMatch = formatter.parse(jour);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (strHeuredb.isEmpty())
					heureDb = currentGame.getHeuredb();
				else
					heureDb = Time.valueOf(LocalTime.parse(strHeuredb));

				if (strHeurefn.isEmpty())
					heureFn = currentGame.getHeurefin();
				else
					heureFn = Time.valueOf(LocalTime.parse(strHeurefn));

				long cmpr = heureDb.getTime() - heureFn.getTime();

				if (cmpr > 0)
					error += "L'heure de début doit être avant celle de fin!";

				if (strcourt.isEmpty())
					court = currentGame.getCourt();
				else {
					court = Integer.parseInt(strcourt);
					if (court != currentGame.getCourt()) {
						if (matchDao.isCourtNotDisponible(court)) 
							error += "Désolé mais le court "+court+" n'est pas disponible \n";
					}
				}

				if (error.isEmpty()) {
					TMatch match = new TMatch(currentGame.getCategorie(), court, heureDb, heureFn, jourMatch, score, currentGame.getSets(), 0, currentGame.getArbitre());
					match.setIdMatch(currentGame.getIdMatch());
					if (statut == 1) {
						match.setStatut(1);
						for (Joueur p: players) {
							p.setIsselected(0);
							joueurDao.updatePlayer(p);
						}
						match.getArbitre().setIsselected(0);
					}
					else {
						for (Joueur p: players) {
							p.setIsselected(1);
							joueurDao.updatePlayer(p);
						}
						match.getArbitre().setIsselected(1);
					}
					matchDao.updateGame(match);
					arbitreDao.updateReferee(currentGame.getArbitre());
				}
				else 
					request.setAttribute("error", error);

				request.setAttribute("games", games);

				this.getServletContext().getRequestDispatcher("/updateGame.jsp")
				.forward(request, response);							
			}
			response.sendRedirect("http://localhost:8080/TournamentManagement/home");
		}
		else
			response.sendRedirect("http://localhost:8080/TournamentManagement/loginPage.jsp");							
	}
}
