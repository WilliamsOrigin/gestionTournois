package servlets.referees;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Arbitre;
import dao.ArbitreDao;

public class findReferee extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();
	
	private ArbitreDao ArbitreDao = new ArbitreDao(em);
	
	String error;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		String nom = request.getParameter("search").trim();
		final Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");
		error = "";
		
		Arbitre referee = ArbitreDao.findRefereeByName(nom);
		
		if (nom.isEmpty() || !pattern.matcher(nom).matches())
			error += "Veuillez entrer un nom valide \n";
		else if(referee == null)
			error += "Désolé l'Arbitre "+nom+" n'existe pas";
			
		if (error.isEmpty()) {
			request.setAttribute("referee", referee);			
		}
		else {
			request.setAttribute("searchError", error);
		}
		
		this.getServletContext().getRequestDispatcher("/updateReferee.jsp")
			.forward(request, response);							
	}
}
