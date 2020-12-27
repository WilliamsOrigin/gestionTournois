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

public class listofPlayers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();
	
	private JoueurDao joueurDao = new JoueurDao(em);
	
	String error;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		List<Joueur> players = joueurDao.findAllPlayers();
		
		request.setAttribute("players", players);
		
		this.getServletContext().getRequestDispatcher("/listofPlayers.jsp")
			.forward(request, response);							
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		error = "";
		String nom = request.getParameter("nom").trim();
		String nationalite = request.getParameter("nationalite").trim();
		int classementMd = Integer.parseInt(request.getParameter("classementMondial").trim());
		String sexe = request.getParameter("sexe").trim();
		String description = request.getParameter("description").trim();
		String img = "profile.jpg";
		
		final Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");
		
		if(joueurDao.existName(nom)) {
			error += "Un joueur de ce nom existe déjà!! \n";
		}
		else if (nom.isEmpty() || !pattern.matcher(nom).matches()) {
			error += "Veuillez entrer un nom valide \n";
		}
		
		if (joueurDao.existRanking(classementMd, sexe))
			error += "Désolé Mais ce classement existe déjà pour ce sexe \n";
		
		if (description.length() < 30)
			error += "Veuillez entrer une description d'au minimum 30 charactères \n";
		
		if (error.isEmpty()) {
			Joueur player = new Joueur(classementMd, description, img, nationalite, nom, sexe);			
			joueurDao.addPlayer(player);
		}
		else 
			request.setAttribute("error", error);
		
		List<Joueur> players = joueurDao.findAllPlayers();
		request.setAttribute("players", players);
		this.getServletContext().getRequestDispatcher("/listofPlayers.jsp")
			.forward(request, response);							
	}
}
