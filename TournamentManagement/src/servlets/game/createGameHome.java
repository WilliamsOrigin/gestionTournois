package servlets.game;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Arbitre;
import beans.Joueur;
import dao.ArbitreDao;
import dao.JoueurDao;

public class createGameHome extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private JoueurDao joueurDao = new JoueurDao(em);
	private ArbitreDao arbitreDao = new ArbitreDao(em);

	String error;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Joueur> players = joueurDao.findSelectedPlayers();
		List<Arbitre> referees = arbitreDao.findSelectedReferees();
		
		request.setAttribute("players", players);
		request.setAttribute("referees", referees);
		
		this.getServletContext().getRequestDispatcher("/createGame.jsp")
		.forward(request, response);							
	}
}
