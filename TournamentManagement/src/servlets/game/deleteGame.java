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

import beans.Appartenir;
import beans.TMatch;
import beans.Utilisateur;
import dao.AppartenirDao;
import dao.MatchDao;

public class deleteGame extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private MatchDao matchDao = new MatchDao(em);
	private AppartenirDao appartenirDao = new AppartenirDao(em);

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() == 0) {
				String error = "";
				int id = Integer.parseInt(request.getParameter("idmatch"));
				TMatch match = matchDao.findGameById(id);

				List<TMatch> games = matchDao.findAllGames();
				List<Appartenir> aps = appartenirDao.getAllGameAp(id);

				if (match.getStatut() == 0) {
					error += "Vous ne pouvez supprimer un Match qui est en cours";
					request.setAttribute("error", error);
				}
				else {
					for(Appartenir ap: aps) {
						appartenirDao.delete(ap.getIdApp());
					}
					matchDao.deleteGame(id);
				}

				request.setAttribute("games", games);

				this.getServletContext().getRequestDispatcher("/updateGame.jsp")
				.forward(request, response);							
			}
			response.sendRedirect("http://localhost:8080/TournamentManagement/home");
		}
		else
			response.sendRedirect("http://localhost:8080/TournamentManagement/loginPage.jsp");
	}
}
