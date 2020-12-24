package servlets.players;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Joueur;
import dao.JoueurDao;

public class listofPlayers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();
	
	private JoueurDao joueurDao = new JoueurDao(em);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		List<Joueur> players = joueurDao.findAllPlayers();
		
		request.setAttribute("players", players);
		
		this.getServletContext().getRequestDispatcher("/listofPlayers.jsp")
			.forward(request, response);							
	}
}
