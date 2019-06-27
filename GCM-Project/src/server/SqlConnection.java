package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
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
 * @author Hasan A class that connects to the mysql database
 *
 */
public class SqlConnection {
	final String DATABASE_URL = "jdbc:mysql://";
	final String timeZone = "?useTimezone=true&serverTimezone=UTC";
	private Connection conn;
	private String schema;
	private Object queryResult = null;

	public Object ExecuteQuery(Object query) {
		ArrayList<String> queryArr = (ArrayList<String>) query;
		String queryType = String.valueOf(queryArr.get(queryArr.size() - 1));// get the type of query
		System.out.println("SQL CONNECTION >> " + queryArr.toString());
		try {

			switch (queryType) {
			case "select":
				System.out.println("SQL CONNECTION >> in the select with " + queryArr.get(0)); // return
				System.out.println("ABOUT TO HIT BREAK IN SELECT AND RETURN VALUE"); // queryResult =
				return ExecuteSelectQuery(queryArr.get(0));
			case "insert":
				queryResult = ExecuteInsertQuery(queryArr);
				break;
			case "file":
				System.out.println("IN GET FILE CASE");
				queryResult = ExecuteGetFileQuery(queryArr);
				break;
			case "update":
			case "delete":
				queryResult = ExecuteUpdateQuery(queryArr);
				break;
			default:
				throw new Exception("SqConnection >> undefined query type");
			}
			return queryResult;
		} catch (Exception e) {
			System.out.println("SqlConnection query exception >> " + e.getMessage());
		}

		return null;
	}

	/**
	 * @param queryArr
	 * @return
	 * @author Hasan
	 */
	private Object ExecuteUpdateQuery(ArrayList<String> queryArr) {
		try {
			PreparedStatement updateQuery = conn.prepareStatement(queryArr.get(0));// pass the query string
			return updateQuery.executeUpdate();// return affected rows
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method will search by the first coulmn of the table in the data base
	 * 
	 * @param queryArr -the queryArr will have the query string at index 0,index 1
	 *                 the name of the blob field, index 2 taget value to search by.
	 * @return bytes of the selected file. Null when failed
	 * @throws SQLException
	 * @author Hasan
	 */
	private Object ExecuteGetFileQuery(ArrayList<String> queryArr) throws Exception {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		File blobFile = null;
		FileOutputStream output;
		String result;
		String blobColumn = queryArr.get(1);
		pstmt = conn.prepareStatement(queryArr.get(0));

		rs = pstmt.executeQuery();

		if (rs.next() != false) {
			blobFile = new File("map_picture.png");
			String filePath;
			InputStream input = rs.getBinaryStream(blobColumn);// read blob
			output = new FileOutputStream(blobFile);
			byte[] buffer = new byte[16777215];// 1024
			// while(input.read(buffer)>0);
			/*
			 * warning it saves the file in the procject directory
			 */
			while (input.read(buffer) > 0) {
				output.write(buffer);
			}
			System.out.println("\nSaved to file: " + blobFile.getAbsolutePath());
			System.out.println("SqlConnection >> ExecuteGetFileQuery >> SUCESS");
			filePath = blobFile.getAbsolutePath();
			// convery file path string
			result = filePath.replace("\\", "/");
			filePath = "file:///";
			result = filePath.concat(result);
			rs.close();// added
			return result;
		}
		System.out.println(" SqlConnection >> ExecuteGetFileQuery >> failure");
		return null;
	}

	/**
	 * Parse database result set into an ArrayList with rows separated by commas
	 * 
	 * @param rs - a result set
	 * @return ArrayList<String>
	 * @author Hasan
	 */
	public ArrayList<String> ParseResultToArrayList(ResultSet rs) {
		ArrayList<String> arr = new ArrayList<>();
		int i;
		// convert the the rs to ArrayList
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				i = 1;
				while (i <= rsmd.getColumnCount()) {
					arr.add(rs.getString(i++));
				}
			}
			// catch any possible error
		} catch (SQLException Exception) {
			System.out.println("SqlConnection >> ParseResultToArrayList >> ERROR while parsing array!");
		}
		System.out.println("Array size:" + arr.size());
		return arr;
	}

	/**
	 * execute only select queries
	 * 
	 * @param msg
	 * @return object
	 * @author Hasan
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
	 * 
	 * @param queryArr
	 * @return 1 when inserted successfully
	 * @throws SQLException
	 * @author Hasan
	 */
	public Object ExecuteInsertQuery(ArrayList<String> queryArr) throws SQLException {
		int i;
		PreparedStatement ps = conn.prepareStatement(queryArr.get(queryArr.size() - 2));
		for (i = 0; i < queryArr.size() - 2; i++) {
			ps.setString(i + 1, queryArr.get(i));
		}
		ps.executeUpdate();
		ps.close();
		System.out.println("DB: InsertQuery => Executed Successfully");
		return 1;

	}

	/**
	 * SqlConnection to establish SQL connection.
	 * 
	 * @param data base schema name
	 * @param data base user name
	 * @param data base password
	 * 
	 */
	public SqlConnection(String schema, String userName, String password, String hostname, String port) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Exception in the SqlConnection ctor :" + ex.getMessage());
		}
		try {
			this.schema = schema;
			conn = DriverManager.getConnection(DATABASE_URL + hostname + ":" + port + "/" + schema + timeZone, userName,
					password);
			System.out.println("SQL connection succeed ");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println(DATABASE_URL + hostname + ":" + port + "/" + schema + timeZone);
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(0);
		}
	}
}
