package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
	final String DATABASE_URL = "jdbc:mysql://localhost/";
	private Connection conn;
	private String schema;
	/**
	 * Establish SQL connection
	 * 
	 * @param data base schema name
	 * @param data base user name
	 * @param data base password
	 * 
	 */
	public SqlConnection(String schema, String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Exception in the SqlConnection ctor :"+ex.getMessage());
			}
		try {
			this.schema = schema;
			conn = DriverManager.getConnection(DATABASE_URL + schema, userName, password);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(0);
		}
	}
}
