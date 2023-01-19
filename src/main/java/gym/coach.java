package gym;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class coach extends HttpServlet{
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String ad = req.getParameter("username");
	String sifre = req.getParameter("password");


		if (ad.equals("coach") && sifre.equals("123")) {
			req.getSession().setAttribute("login", "true");
			resp.sendRedirect("coach1.html");
						
		} else {

			req.getSession().setAttribute("login", "false");
			resp.sendRedirect("coach.html");
		}
	}
	
}

