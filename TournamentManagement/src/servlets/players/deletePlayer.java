package servlets.players;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Joueur;
import dao.JoueurDao;

public class deletePlayer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();
	
	private JoueurDao joueurDao = new JoueurDao(em);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idjoueur"));
		
		joueurDao.deletePlayer(id);
		
		this.getServletContext().getRequestDispatcher("/updatePlayer.jsp")
			.forward(request, response);							
	}
}
