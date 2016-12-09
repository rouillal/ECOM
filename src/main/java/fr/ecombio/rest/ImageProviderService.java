package fr.ecombio.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ecombio.data.PanierRepository;
import fr.ecombio.data.RegistreRepository;
import fr.ecombio.model.SendEmail;
import fr.ecombio.model.ValidationCommande;

/**
 * <p>
 * Permet un service web pour acceder a l'image d'un produit
 *
 */
@SuppressWarnings("serial")
@WebServlet(
        urlPatterns = "/image",
        initParams =
        {
            @WebInitParam(name = "saveDir", value = "C:\\tempEcom\\"),
            @WebInitParam(name = "filenameParamName", value = "name")
        }
)
public class ImageProviderService extends HttpServlet implements ServletContextListener {
	//Directory path where the files are stored
	private String fileDirName;

	/**
	 * requete de l'image en http
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String saveDir = getInitParameter("saveDir");
		String fileName = request.getParameter("name");
		File file = new File(saveDir+fileName+".png");
		if (file.exists()) {

			response.setContentType("image/png");
			OutputStream out = response.getOutputStream();

			response.setContentLength((int) file.length());

			FileInputStream in = new FileInputStream(file);
			// Copy the contents of the file to the output stream
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			out.close();
			in.close();

		} else {
			response.setContentType("text/html");  
			PrintWriter writer = response.getWriter();
			writer.println("<html>Hello, I am a Java servlet!</html>");
			writer.flush();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
