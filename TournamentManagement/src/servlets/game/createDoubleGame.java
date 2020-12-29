package servlets.game;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Appartenir;
import beans.Arbitre;
import beans.Equipe;
import beans.Joueur;
import beans.TMatch;
import beans.Utilisateur;
import dao.AppartenirDao;
import dao.ArbitreDao;
import dao.EquipeDao;
import dao.JoueurDao;
import dao.MatchDao;

public class createDoubleGame extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private JoueurDao joueurDao = new JoueurDao(em);
	private EquipeDao equipeDao = new EquipeDao(em);
	private ArbitreDao arbitreDao = new ArbitreDao(em);
	private AppartenirDao appartenirDao = new AppartenirDao(em);
	private MatchDao matchDao = new MatchDao(em);

	String error;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() == 0) {
				String error = "";
				int category = Integer.parseInt(request.getParameter("dcategorie"));
				String jour = request.getParameter("djour");

				SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
				Date jourMatch = null;

				try {
					jourMatch = formatter.parse(jour);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Time heureDb = Time.valueOf(LocalTime.parse(request.getParameter("dheureDeb")));
				Time heureFn = Time.valueOf(LocalTime.parse(request.getParameter("dheureFin")));

				int court = Integer.parseInt(request.getParameter("dcourt"));
				String score = request.getParameter("dscore");

				int idJ = Integer.parseInt(request.getParameter("djoueur"));
				Joueur player = joueurDao.findPlayer(idJ);

				int idOp = Integer.parseInt(request.getParameter("dadversaire"));
				Joueur opponent = joueurDao.findPlayer(idOp);

				int idJe = Integer.parseInt(request.getParameter("j_equipier"));
				Joueur p_equipier = joueurDao.findPlayer(idJe);

				int idOpe = Integer.parseInt(request.getParameter("o_equipier"));
				Joueur o_equipier = joueurDao.findPlayer(idOpe);

				int idArb = Integer.parseInt(request.getParameter("darbitre"));
				Arbitre referee = arbitreDao.findReferee(idArb);

				if (category == 3) {
					if (!player.getSexe().equals("Masculin"))
						error += "Le joueur choisi n'est pas approprié pour cette catégorie \n";	

					if (!p_equipier.getSexe().equals("Masculin"))
						error += "Le coéquipier du joueur choisi n'est pas approprié pour cette catégorie \n";

					if (!opponent.getSexe().equals("Masculin"))
						error += "L'adversaire choisi n'est pas approprié pour cette catégorie \n";

					if (!o_equipier.getSexe().equals("Masculin"))
						error += "Le coéquipier l'adversaire choisi n'est pas approprié pour cette catégorie \n";
				}
				else if (category == 4) {
					if (player.getSexe().equals("Masculin"))
						error += "Le joueur choisi n'est pas approprié pour cette catégorie \n";	

					if (p_equipier.getSexe().equals("Masculin"))
						error += "Le coéquipier du joueur choisi n'est pas approprié pour cette catégorie \n";

					if (opponent.getSexe().equals("Masculin"))
						error += "L'adversaire choisi n'est pas approprié pour cette catégorie \n";

					if (o_equipier.getSexe().equals("Masculin"))
						error += "Le coéquipier l'adversaire choisi n'est pas approprié pour cette catégorie \n";
				}
				else {
					if (!player.getSexe().equals("Masculin"))
						error += "Le joueur choisi n'est pas approprié pour cette catégorie \n";	

					if (p_equipier.getSexe().equals("Masculin"))
						error += "Le coéquipier du joueur choisi n'est pas approprié pour cette catégorie \n";

					if (!opponent.getSexe().equals("Masculin"))
						error += "L'adversaire choisi n'est pas approprié pour cette catégorie \n";

					if (o_equipier.getSexe().equals("Masculin"))
						error += "Le coéquipier l'adversaire choisi n'est pas approprié pour cette catégorie \n";
				}

				long cmpr = heureDb.getTime() - heureFn.getTime();
				if (cmpr > 0)
					error += "L'heure de début doit être avant celle de fin!";

				if (matchDao.isCourtNotDisponible(court)) 
					error += "Désolé mais le court "+court+" n'est pas disponible \n";

				if (idJ == idJe || idJ == idOp || idJ == idOpe)
					error += "Vous avez choisi un joueur 2 fois de suite!! \n";
				else if (idJe == idOp || idJe == idOpe)
					error += "Vous avez choisi un joueur 2 fois de suite!! \n";
				else if (idOp == idOpe)
					error += "Vous avez choisi un joueur 2 fois de suite!! \n";

				if (error.isEmpty()) {
					TMatch match = new TMatch(category, court, heureDb, heureFn, jourMatch, score, 5, 0, referee);
					Equipe equipe1 = new Equipe(idJe, idJ);
					Equipe equipe2 = new Equipe(idOpe, idOp);

					equipeDao.addTeam(equipe1);
					equipeDao.addTeam(equipe2);

					int ideq1 = equipeDao.findTeamByContent(idJ, idJe).getIdEquipe();
					int ideq2 = equipeDao.findTeamByContent(idOp, idOpe).getIdEquipe();

					Appartenir appartenir1 = new Appartenir(category, ideq1, null, match);
					Appartenir appartenir2 = new Appartenir(category, ideq2, null, match);
					player.setIsselected(1);
					opponent.setIsselected(1);
					p_equipier.setIsselected(1);
					o_equipier.setIsselected(1);
					referee.setIsselected(1);

					matchDao.addGame(match);
					appartenirDao.add(appartenir1);
					appartenirDao.add(appartenir2);
					joueurDao.updatePlayer(player);
					joueurDao.updatePlayer(opponent);
					joueurDao.updatePlayer(p_equipier);
					joueurDao.updatePlayer(o_equipier);
					arbitreDao.updateReferee(referee);
				}
				else 
					request.setAttribute("d_error", error);

				List<Joueur> players = joueurDao.findSelectedPlayers();
				List<Arbitre> referees = arbitreDao.findSelectedReferees();

				request.setAttribute("players", players);
				request.setAttribute("referees", referees);

				this.getServletContext().getRequestDispatcher("/createGame.jsp")
				.forward(request, response);	
			}
			response.sendRedirect("http://localhost:8080/TournamentManagement/home");
		}
		else
			response.sendRedirect("http://localhost:8080/TournamentManagement/loginPage.jsp");						
	}
}
