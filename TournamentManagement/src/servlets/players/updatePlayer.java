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
import beans.Utilisateur;
import dao.JoueurDao;

public class updatePlayer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private JoueurDao joueurDao = new JoueurDao(em);

	String error;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() == 0) {

				List<Joueur> players = joueurDao.findAllPlayers();

				request.setAttribute("players", players);

				this.getServletContext().getRequestDispatcher("/updatePlayer.jsp")
				.forward(request, response);					
			}
			response.sendRedirect("http://localhost:8080/TournamentManagement/home");
		}
		else
			response.sendRedirect("http://localhost:8080/TournamentManagement/loginPage.jsp");							
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() == 0) {
				error = "";
				int id = Integer.parseInt(request.getParameter("idj"));
				String nom = request.getParameter("nom").trim();
				String nationalite = request.getParameter("nationalite").trim();
				String classementMd = request.getParameter("classementMondial");
				int rank;
				String sexe = request.getParameter("sexe").trim();
				String description = request.getParameter("description").trim();

				Joueur joueur = joueurDao.findPlayer(id);

				final Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");

				if (nom.isEmpty())
					nom = joueur.getNom();
				else {
					if(!joueur.getNom().equals(nom) ) {
						if (joueurDao.existName(nom))
							error += "Un joueur de ce nom existe d�j�!! \n";
						else if (!pattern.matcher(nom).matches()) {
							error += "Veuillez entrer un nom valide \n";
						}
					}
				}

				if (nationalite.isEmpty()) 
					nationalite = joueur.getNationalite();

				if (sexe.isEmpty())
					sexe = joueur.getSexe();

				if (classementMd.isEmpty())
					rank = joueur.getClassementmondial();
				else {
					rank = Integer.parseInt(classementMd.trim());
					if (joueur.getClassementmondial() != rank) {
						if (joueurDao.existRanking(rank, sexe))
							error += "D�sol� Mais ce classement existe d�j� pour ce sexe \n";
					}
				}

				if (description.isEmpty())
					description = joueur.getDescription();
				else {
					if (description.length() < 30)
						error += "Veuillez entrer une description d'au minimum 30 charact�res \n";
				}

				if (error.isEmpty()) {
					Joueur player = new Joueur(rank, description, joueur.getImage(), nationalite, nom, sexe);			
					player.setIdJoueur(joueur.getIdJoueur());
					joueurDao.updatePlayer(player);
				}
				else {
					request.setAttribute("error", error);
				}

				this.getServletContext().getRequestDispatcher("/updatePlayer.jsp")
				.forward(request, response);				
			}
			response.sendRedirect("http://localhost:8080/TournamentManagement/home");
		}
		else
			response.sendRedirect("http://localhost:8080/TournamentManagement/loginPage.jsp");	

	}
}
