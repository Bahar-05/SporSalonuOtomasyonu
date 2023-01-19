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

public class admindietadd_db extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Ad= req.getParameter("ad");
		String Soyad= req.getParameter("soyad");
		String Mail= req.getParameter("mail");
		String Adres= req.getParameter("adres");
		
		
		PgConnector pgc=new PgConnector();
		Connection c=pgc.getConnection();
		
		if(c!=null) {
			
			PreparedStatement ps=null;
			
			String sql="INSERT INTO public.t_admindiet(id,ad,soyad,mail,adres) VALUES (nextval('s_admindiet'),?, ?, ?, ?);";
					
			try {
				ps=c.prepareStatement(sql);
				
				ps.setString(1, Ad);
				ps.setString(2, Soyad);
				ps.setString(3, Mail);
				ps.setString(4, Adres);
				
							
				ps.executeUpdate();
				c.commit();
				c.close();	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		resp.sendRedirect("admindietCrud.html");
			
	}
}