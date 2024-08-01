package configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionConfig {
	/**
	 * Establishes a connection to the database using pre-defined credentials and driver information.
	 * 
	 * @return A Connection object representing the established connection to the database.
	 * @throws SQLException if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public Connection getDbConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/oclock";
			String userName = "root";
			String password = "";
			
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException ex) {
            // JDBC driver not found, handle the error
            ex.printStackTrace();
            throw new SQLException("JDBC driver not found", ex);
        } catch (SQLException ex) {
            // Error occurred while establishing the connection, handle it
            ex.printStackTrace();
            throw ex; // Rethrow the SQLException to indicate connection failure
        }
		return conn;
	}
}
