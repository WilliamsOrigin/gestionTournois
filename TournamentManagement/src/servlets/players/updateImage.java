package servlets.players;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import beans.Joueur;
import beans.Utilisateur;
import dao.JoueurDao;

public class updateImage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TournamentManagement");
	EntityManager em = emf.createEntityManager();

	private JoueurDao joueurDao = new JoueurDao(em);

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() == 0) {

				int id;
				int random = new Random().nextInt(1000000);
				Joueur joueur = null;

				String error = "";

				if (ServletFileUpload.isMultipartContent(request)) {
					try {
						List<FileItem> items = new ServletFileUpload(
								new DiskFileItemFactory()).parseRequest(request);

						for(FileItem item : items) {

							if (item.isFormField()) {
								String name = item.getFieldName();
								if (name.equals("idjoueur")) {
									id = Integer.parseInt(item.getString().trim());
									joueur = joueurDao.findPlayer(id);
								}
							}
							else {
								String contentType = item.getContentType();

								if(!contentType.equals("image/jpeg") && !contentType.equals("image/jpg")) {
									error += "Only jpg and jpeg images are tolerated !";
								}

								String uploadDir = "C:\\Users\\easy\\git\\gestionTournois\\TournamentManagement\\WebContent\\assets\\img\\";

								if (joueur != null) {
									String fileName = joueur.getNom()+""+random+".jpg";
									File file = new File(uploadDir+fileName);
									item.write(file);
									joueur.setImage(fileName);
								}
							}
						}
					} catch (Exception ex) {
						error += "File Upload Failed due to " + ex;
					}
				}

				if (error.isEmpty())
					joueurDao.updatePlayer(joueur);
				else 
					request.setAttribute("uploadErr", error);		

				this.getServletContext().getRequestDispatcher("/updatePlayer.jsp")
				.forward(request, response);														
			}
			response.sendRedirect("http://localhost:8080/TournamentManagement/home");
		}
		else
			response.sendRedirect("http://localhost:8080/TournamentManagement/loginPage.jsp");
	}
}
