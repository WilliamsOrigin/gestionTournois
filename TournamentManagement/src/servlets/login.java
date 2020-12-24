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
import beans.Utilisateur;
import dao.AppartenirDao;
import dao.UtilisateurDao;


public class login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();
	
	private UtilisateurDao userDao = new UtilisateurDao(em); 
	private AppartenirDao appDao = new AppartenirDao(em);
	
	Utilisateur user;
	
	String error;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String motdepasse = request.getParameter("motdepasse");
		
		List<Utilisateur> users = userDao.findAllUsers();
		
		for (Utilisateur u : users) {
			if (u.getPseudo().equals(pseudo) && u.getMotdepasse().equals(motdepasse)) {
				user = u;
				break;
			}
		}
		
		if (user != null) {
			Map<TMatch, List<Joueur>> infoSimpleGame = appDao.getInfoSimpleGame();
			Map<TMatch, List<Joueur>> infoDoubleGame = appDao.getInfoDoubleGame();
			
			request.setAttribute("infoSimpleGame", infoSimpleGame);
			request.setAttribute("infoDoubleGame", infoDoubleGame);
			
			//request.getSession().setAttribute("user", user);
			
			this.getServletContext().getRequestDispatcher("/home.jsp")
				.forward(request, response);
		}
		else {
			error = "Veuillez vérifier que votre pseudo et mot de passe concordent !!";
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher("/loginPage.jsp")
			.forward(request, response);
		}
	}
			
}
