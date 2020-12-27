package servlets.referees;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArbitreDao;

public class deleteReferee extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();
	
	private ArbitreDao ArbitreDao = new ArbitreDao(em);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idArbitre"));
		
		ArbitreDao.deleteReferee(id);
		
		this.getServletContext().getRequestDispatcher("/updateReferee.jsp")
			.forward(request, response);							
	}
}
