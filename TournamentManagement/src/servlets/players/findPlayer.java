package servlets.players;

 import java.io.IOException;
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

public class findPlayer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();
	
	private JoueurDao joueurDao = new JoueurDao(em);
	
	String error;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		String nom = request.getParameter("search").trim();
		final Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");
		error = "";
		
		Joueur player = joueurDao.findPlayerByName(nom);
		
		if (nom.isEmpty() || !pattern.matcher(nom).matches())
			error += "Veuillez entrer un nom valide \n";
		else if(player == null)
			error += "Désolé le joueur "+nom+" n'existe pas";
			
		if (error.isEmpty()) {
			request.setAttribute("player", player);			
		}
		else {
			request.setAttribute("searchError", error);
		}
		
		this.getServletContext().getRequestDispatcher("/updatePlayer.jsp")
			.forward(request, response);							
	}
}
