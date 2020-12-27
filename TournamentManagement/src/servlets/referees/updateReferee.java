package servlets.referees;

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

import beans.Arbitre;
import dao.ArbitreDao;

public class updateReferee extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();
	
	private ArbitreDao ArbitreDao = new ArbitreDao(em);
	
	String error;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		List<Arbitre> referees = ArbitreDao.findAllReferees();
		
		request.setAttribute("referees", referees);
		
		this.getServletContext().getRequestDispatcher("/updateReferee.jsp")
			.forward(request, response);							
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		error = "";
		int id = Integer.parseInt(request.getParameter("ida"));
		String nom = request.getParameter("nom").trim();
		String nationalite = request.getParameter("nationalite").trim();
		String description = request.getParameter("description").trim();
		
		Arbitre arbitre = ArbitreDao.findReferee(id);
		
		final Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");
		
		if (nom.isEmpty())
			nom = arbitre.getNom();
		else {
			if(!arbitre.getNom().equals(nom) ) {
				if (ArbitreDao.existName(nom))
					error += "Un Arbitre de ce nom existe déjà!! \n";
				else if (!pattern.matcher(nom).matches()) {
					error += "Veuillez entrer un nom valide \n";
				}
			}
		}
		
		if (nationalite.isEmpty()) 
			nationalite = arbitre.getNationalite();
		
		if (description.isEmpty())
			description = arbitre.getDescription();
		else {
			if (description.length() < 30)
				error += "Veuillez entrer une description d'au minimum 30 charactères \n";
		}
		
		if (error.isEmpty()) {
			Arbitre referee = new Arbitre(description, nationalite, nom);
			referee.setIdArbitre(arbitre.getIdArbitre());
			ArbitreDao.updateReferee(referee);
		}
		else {
			request.setAttribute("error", error);
		}
		
		this.getServletContext().getRequestDispatcher("/updateReferee.jsp")
			.forward(request, response);							
	}
}
