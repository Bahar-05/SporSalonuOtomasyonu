package gym;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class adminsporculist extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PgConnector pgc=new PgConnector();
		Connection c=pgc.getConnection();
		String html="<table border=\"1px\">";
		if(c!=null) {
			String sql="SELECT id, ad, soyad, yas, mail,adres,spordali\r\n"
					+ "	FROM public.t_adminsporcu;";
			
			Statement ps=null;
			try {
				ps=c.createStatement();
				ResultSet rs= ps.executeQuery(sql);
				while(rs.next()) {
					
					int id=rs.getInt(1);
					String Ad=rs.getString(2);
					String Soyad=rs.getString(3);
					int Yas=rs.getInt(4);
					String Mail=rs.getString(5);
					String Adres=rs.getString(6);
					String Spordali=rs.getString(7);
					
					
					html+="<table style=\"width:650px; border:1px solid blue; text-align:center;\">"
							
				 	+ " <tr><td>"+id+"</td><td>"+Ad+"</td><td>"+Soyad+"</td><td>"+Yas+"</td><td>"+Mail+"</td><td>"+Adres+"</td><td>"+Spordali+"</td><td>"
				 	+ "<form action=\"adminsporcusil.bhr\" method=\"post\">"
				 	+ "<input type=\"hidden\" name=\"id\" value=\""+id+"\">"
				 			+ "<input type=\"submit\" value=\"Sil\">"
					+ "</form>"
					+ "</td><td>"
					+ "<input type=\"button\" value=\"Guncelle\" onclick=\"update("+id+",'"+Ad+"','"+Soyad+"',"+Yas+",'"+Mail+"','"+Adres+"',"+Spordali+");\"></td></tr></table>";
				}				
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		html+="</table>";
		resp.getWriter().write(html);
	}

}
