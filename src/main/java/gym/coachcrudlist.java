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

public class coachcrudlist extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PgConnector pgc=new PgConnector();
		Connection c=pgc.getConnection();
		String html="<table border=\"1px\">";
		if(c!=null) {
			String sql="SELECT id, gun, bassaat, bitsaat, yer, alet\r\n"
					+ "	FROM public.t_coach;";
			
			Statement ps=null;
			try {
				ps=c.createStatement();
				ResultSet rs= ps.executeQuery(sql);
				while(rs.next()) {
					
					
					int id=rs.getInt(1);
					String Gun=rs.getString(2);
					String Bassaat=rs.getString(3);
					String Bitsaat=rs.getString(4);
					String Yer=rs.getString(5);
					String Alet=rs.getString(6);
					
					
					html+="<table style=\"width:650px; border:1px solid blue; text-align:center;\">"
							
				 	+ " <tr><td>"+id+"</td><td>"+Gun+"</td><td>"+Bassaat+"</td><td>"+Bitsaat+"</td><td>"+Yer+"</td><td>"+Alet+"</td><td>"
				 	+ "<form action=\"coachdel.bhr\" method=\"post\">"
				 	+ "<input type=\"hidden\" name=\"id\" value=\""+id+"\">"
				 			+ "<input type=\"submit\" value=\"Sil\">"
					+ "</form>"
					+ "</td><td>"
					+ "<input type=\"button\" value=\"Guncelle\" onclick=\"update("+id+",'"+Gun+"','"+Bassaat+"','"+Bitsaat+"','"+Yer+"','"+Alet+"');\"></td></tr></table>";
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
