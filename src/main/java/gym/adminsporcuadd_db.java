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

public class adminsporcuadd_db extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Ad= req.getParameter("ad");
		String Soyad= req.getParameter("soyad");
		int Yas=Integer.parseInt(req.getParameter("yas"));
		String Mail= req.getParameter("mail");
		String Adres= req.getParameter("adres");
		String Spordali= req.getParameter("spordali");
		
		PgConnector pgc=new PgConnector();
		Connection c=pgc.getConnection();
		
		if(c!=null) {
			
			PreparedStatement ps=null;
			
			String sql="INSERT INTO public.t_adminsporcu(id,ad,soyad,yas,mail,adres,spordali) VALUES (nextval('s_adminsporcu'),?, ?, ?, ?, ?, ?);";
					
			try {
				ps=c.prepareStatement(sql);
				
				ps.setString(1, Ad);
				ps.setString(2, Soyad);
				ps.setInt(3, Yas);
				ps.setString(4, Mail);
				ps.setString(5, Adres);
				ps.setString(6, Spordali);
							
				ps.executeUpdate();
				c.commit();
				c.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		resp.sendRedirect("adminsporCrud.html");		
	}
}