package gym;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class dietcrud_db extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Sabah= req.getParameter("sabah");
		String Ogle= req.getParameter("ogle");
		String Aksam= req.getParameter("aksam");
		
		
		
		PgConnector pgc=new PgConnector();
		Connection c=pgc.getConnection();
		
		if(c!=null) {
			
			PreparedStatement ps=null;
			
			String sql="INSERT INTO public.t_diet(id,sabah,ogle,aksam) VALUES (nextval('s_diet'),?, ?, ?);";
					
			try {
				ps=c.prepareStatement(sql);
				
				
				ps.setString(1, Sabah);
				ps.setString(2, Ogle);
				ps.setString(3, Aksam);
				
							
				ps.executeUpdate();
				c.commit();
				c.close();	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		resp.sendRedirect("dietCrud.html");
			
	}
}