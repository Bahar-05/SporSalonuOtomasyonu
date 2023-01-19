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

public class sporcudietlist extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PgConnector pgc=new PgConnector();
		Connection c=pgc.getConnection();
		String html="<table border=\"1px\">";
		if(c!=null) {
			String sql="SELECT id, sabah, ogle, aksam\r\n"
					+ "	FROM public.t_diet;";
			
			Statement ps=null;
			try {
				ps=c.createStatement();
				ResultSet rs= ps.executeQuery(sql);
				while(rs.next()) {
					
					
					int id=rs.getInt(1);
					String Sabah=rs.getString(2);
					String Ogle=rs.getString(3);
					String Aksam=rs.getString(4);
					
					
					html+="<table style=\"width:650px; border:1px solid blue; text-align:center;\">"
							
				 	+ " <tr><td>"+id+"</td><td>"+Sabah+"</td><td>"+Ogle+"</td><td>"+Aksam+"</td><td>"
					+ "</form>"
					+ "</td><td>"
					+"</table>";
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
