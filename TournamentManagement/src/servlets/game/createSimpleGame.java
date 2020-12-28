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

import beans.Appartenir;
import beans.Arbitre;
import beans.Joueur;
import beans.TMatch;
import dao.AppartenirDao;
import dao.ArbitreDao;
import dao.JoueurDao;
import dao.MatchDao;

public class createSimpleGame extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private JoueurDao joueurDao = new JoueurDao(em);
	private ArbitreDao arbitreDao = new ArbitreDao(em);
	private AppartenirDao appartenirDao = new AppartenirDao(em);
	private MatchDao matchDao = new MatchDao(em);

	String error;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String error = "";
		int category = Integer.parseInt(request.getParameter("categorie"));
		String jour = request.getParameter("jour");

		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date jourMatch = null;

		try {
			jourMatch = formatter.parse(jour);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Time heureDb = Time.valueOf(LocalTime.parse(request.getParameter("heureDeb")));
		Time heureFn = Time.valueOf(LocalTime.parse(request.getParameter("heureFin")));

		int court = Integer.parseInt(request.getParameter("court"));
		String score = request.getParameter("score");

		int idJ = Integer.parseInt(request.getParameter("joueur"));
		Joueur player = joueurDao.findPlayer(idJ);

		int idOp = Integer.parseInt(request.getParameter("adversaire"));
		Joueur opponent = joueurDao.findPlayer(idOp);

		int idArb = Integer.parseInt(request.getParameter("arbitre"));
		Arbitre referee = arbitreDao.findReferee(idArb);

		if (category == 1) {
			if (!player.getSexe().equals("Masculin"))
				error += "Le joueur choisi n'est pas approprié pour cette catégorie \n";
			if (!opponent.getSexe().equals("Masculin"))
				error += "L'adversaire choisi n'est pas approprié pour cette catégorie \n";
		}
		else {
			if (player.getSexe().equals("Masculin"))
				error += "Le joueur choisi n'est pas approprié pour cette catégorie \n";
			if (opponent.getSexe().equals("Masculin"))
				error += "L'adversaire choisi n'est pas approprié pour cette catégorie \n";
		}
		long cmpr = heureDb.getTime() - heureFn.getTime();
		if (cmpr > 0)
			error += "L'heure de début doit être avant celle de fin!";

		if (matchDao.isCourtNotDisponible(court)) 
			error += "Désolé mais le court "+court+" n'est pas disponible \n";

		if (idJ == idOp)
			error += "Veuillez choisir un adversaire différent ! \n";

		if (error.isEmpty()) {
			TMatch match = new TMatch(category, court, heureDb, heureFn, jourMatch, score, 3, 0);
			Appartenir appartenir1 = new Appartenir(category, null, idJ, match);
			Appartenir appartenir2 = new Appartenir(category, null, idOp, match);
			player.setIsselected(1);
			opponent.setIsselected(1);
			referee.setIsselected(1);

			matchDao.addGame(match);
			appartenirDao.add(appartenir1);
			appartenirDao.add(appartenir2);
			joueurDao.updatePlayer(player);
			joueurDao.updatePlayer(opponent);
			arbitreDao.updateReferee(referee);
		}
		else 
			request.setAttribute("error", error);

		List<Joueur> players = joueurDao.findSelectedPlayers();
		List<Arbitre> referees = arbitreDao.findSelectedReferees();

		request.setAttribute("players", players);
		request.setAttribute("referees", referees);

		this.getServletContext().getRequestDispatcher("/createGame.jsp")
		.forward(request, response);							
	}
}
