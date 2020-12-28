package servlets.players;

import java.io.IOException;

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
		String error = "";
		int id = Integer.parseInt(request.getParameter("idjoueur"));
		Joueur joueur = joueurDao.findPlayer(id);
		
		if (joueur.getIsselected() == 1) {
			error += "Vous ne pouvez supprimer un joueur qui est en cours de match";
			request.setAttribute("error", error);
		}
		else
			joueurDao.deletePlayer(id);
		
		this.getServletContext().getRequestDispatcher("/updatePlayer.jsp")
			.forward(request, response);							
	}
}
