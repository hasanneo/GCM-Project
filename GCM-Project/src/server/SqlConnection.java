package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

public class SqlConnection {
	final String DATABASE_URL = "jdbc:mysql://";
	final String timeZone="?useTimezone=true&serverTimezone=UTC";
	private Connection conn;
	private String schema;
	private Object queryResult;
	/**
	 * Parse database result set into an ArrayList with rows separated by commas
	 * 
	 * @param rs
	 * @return ArrayList<String>
	 */
	public ArrayList<String> parseResultSet(ResultSet rs) {
		ArrayList<String> arr = new ArrayList<>();
		int i;
		
		//convert the the rs to ArrayList
		try { 
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				i = 1;
				while (i <= rsmd.getColumnCount()) {
					arr.add(rs.getString(i++));
				}
			}
			//catch any possible error	
		} catch (SQLException Exception) { 
			System.out.println("ERROR while parsing array!");
		}
		return arr;
	}
	/**
	 * execute only select queries
	 * @param msg
	 * @return object
	 */
	
	public Object ExecuteQuery(Object query) {
		String msg = query.toString();
		try {
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(msg);
			System.out.println("DB: " + msg + " => Executed Successfully");
			return parseResultSet(rs);

		} catch (SQLException sqlException) {
			System.out.println("Couldn't execute query");
			sqlException.printStackTrace();
			return null;
		}
	}
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
			System.out.println("SQL connection succeed ");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println(DATABASE_URL +hostname+":"+port+"/" +schema+timeZone);
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(0);
		}
	}
}
