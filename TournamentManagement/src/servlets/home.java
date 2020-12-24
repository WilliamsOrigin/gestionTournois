package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Joueur;
import beans.TMatch;
import dao.AppartenirDao;

public class home extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private AppartenirDao appDao = new AppartenirDao(em);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		Map<TMatch, List<Joueur>> infoSimpleGame = appDao.getInfoSimpleGame();
		Map<TMatch, List<Joueur>> infoDoubleGame = appDao.getInfoDoubleGame();
		
		request.setAttribute("infoSimpleGame", infoSimpleGame);
		request.setAttribute("infoDoubleGame", infoDoubleGame);
		
		this.getServletContext().getRequestDispatcher("/home.jsp")
			.forward(request, response);		
				
	}
}
