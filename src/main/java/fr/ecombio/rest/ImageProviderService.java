package fr.ecombio.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        urlPatterns = "/image",
        initParams =
        {
            @WebInitParam(name = "saveDir", value = "C:\\tempEcom\\")
        }
)
public class ImageProviderService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String saveDir = getInitParameter("saveDir");
		File file = new File(saveDir+"lac.jpg");
		if (file.exists()) {

			response.setContentType("image/jpg");
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
}
