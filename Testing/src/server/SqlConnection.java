package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
	final String DATABASE_URL = "jdbc:mysql://";
	final String timeZone="?useTimezone=true&serverTimezone=UTC";
	private Connection conn;
	private String schema;
	/**
	 * SqlConnection to establish SQL connection.
	 * 
	 * @param data base schema name
	 * @param data base user name
	 * @param data base password
	 * 
	 */
	public SqlConnection(String schema, String userName, String password,String hostname,String port) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Exception in the SqlConnection ctor :"+ex.getMessage());
			}
		try {
			this.schema = schema;			
			conn = DriverManager.getConnection(DATABASE_URL +hostname+":"+port+"/" +schema+timeZone, userName, password);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println(DATABASE_URL +hostname+":"+port+"/" +schema+timeZone);
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(0);
		}
	}
}
