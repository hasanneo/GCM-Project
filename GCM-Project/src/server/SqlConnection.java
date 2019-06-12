package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;
/**
 * 
 * @author Hasan
 *A class that connects to the mysql database
 *
 */
public class SqlConnection {
	final String DATABASE_URL = "jdbc:mysql://";
	final String timeZone="?useTimezone=true&serverTimezone=UTC";
	private Connection conn;
	private String schema;
	private Object queryResult;
	
	public Object ExecuteQuery(Object query) {
		ArrayList<String> queryArr = (ArrayList<String>) query;
		String queryType = String.valueOf(queryArr.get(queryArr.size() - 1));
		System.out.println("SQL CONNECTION >> "+queryArr.toString());
		try {
		if(queryType.equals("select")) {
			System.out.println("SQL CONNECTION >> in the select with "+queryArr.get(0));
			return ExecuteSelectQuery(queryArr.get(0));
		}
		else if(queryType.equals("insert")) {
			int i;
			PreparedStatement ps = conn.prepareStatement(queryArr.get(queryArr.size() - 2));
			for (i = 0; i < queryArr.size() - 2; i++) {
				ps.setString(i + 1, queryArr.get(i));
			}
			ps.executeUpdate();
			ps.close();
			System.out.println("DB: InsertQuery => Executed Successfully");
			return null;
		}
		}catch(Exception e) {
			System.out.println("SqlConnection query exception >> "+e.getMessage());
		}
		
		return null;
	
	}
	
	/**
	 * Parse database result set into an ArrayList with rows separated by commas
	 * 
	 * @param rs
	 * @return ArrayList<String>
	 */
	public ArrayList<String> ParseResultToArrayList(ResultSet rs) {
		ArrayList<String> arr = new ArrayList<>();
		int i;
			
		try {//convert the the rs to ArrayList 
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				i = 1;
				while (i <= rsmd.getColumnCount()) {
					arr.add(rs.getString(i++));
				}
			}
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
	
	public Object ExecuteSelectQuery(Object query) {
		String msg = query.toString(); 
		int size;
		try {
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(msg);
			System.out.println("DB: " + msg + " => Executed Successfully");
			return ParseResultToArrayList(rs);
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
