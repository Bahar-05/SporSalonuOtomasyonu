package gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgConnector {
	Connection getConnection() {
		
		Connection c=null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/spor", "postgres", "int");
			c.setAutoCommit(false);
			return c;
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
