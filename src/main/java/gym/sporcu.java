package gym;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class sporcu extends HttpServlet{
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String ad = req.getParameter("username");
	String sifre = req.getParameter("password");


		if (ad.equals("spor") && sifre.equals("123")) {
			req.getSession().setAttribute("login", "true");
			resp.sendRedirect("sporcu1.html");
						
		} else {

			req.getSession().setAttribute("login", "false");
			resp.sendRedirect("sporcu.html");
		}
	}
	
}

