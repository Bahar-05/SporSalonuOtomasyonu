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

public class coachcrud_db extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Gun= req.getParameter("gun");
		String Bassaat= req.getParameter("bassaat");
		String Bitsaat= req.getParameter("bitsaat");
		String Yer= req.getParameter("yer");
		String Alet= req.getParameter("alet");
		
		PgConnector pgc=new PgConnector();
		Connection c=pgc.getConnection();
		
		if(c!=null) {
			
			PreparedStatement ps=null;
			
			String sql="INSERT INTO public.t_coach(id,gun,bassaat,bitsaat,yer,alet) VALUES (nextval('s_coach'),?, ?, ?, ?, ?);";
					
			try {
				ps=c.prepareStatement(sql);
				
				ps.setString(1, Gun);
				ps.setString(2, Bassaat);
				ps.setString(3, Bitsaat);
				ps.setString(4, Yer);
				ps.setString(5, Alet);
					
				ps.executeUpdate();
				c.commit();
				c.close();	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		resp.sendRedirect("coachCrud.html");
			
	}
}