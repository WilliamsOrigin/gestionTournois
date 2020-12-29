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

import beans.TMatch;
import beans.Utilisateur;
import dao.MatchDao;

public class findGame extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private MatchDao matchDao = new MatchDao(em);

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() == 0) {
				int id = Integer.parseInt(request.getParameter("search").trim());
				String error = "";

				TMatch match = matchDao.findGameById(id);
				List<TMatch> games = matchDao.findAllGames();

				if (match == null) 
					error += "Désolé mais un match de cet id n'existe pas ! \n";
				else
					request.setAttribute("game", match);

				if (!error.isEmpty())
					request.setAttribute("searchError", error);

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
