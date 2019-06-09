package controller;

import java.util.ArrayList;

import client.ClientConnection;

/**
 * 
 * @author Hasan
 *A class defining the queries that will be sent to the server, and other data base uses and set up.
 *
 */
public class DataBaseController {

	public static ClientConnection clientCon=null;
	/**
	 * Setting up the client connection
	 * @param newClientConnection
	 */
	public static void InitiateClient(ClientConnection newClientConnection) {
		clientCon = newClientConnection;
	}
/**
 * A method that will return true if the data was successfully selected.
 * @param tableName
 * @param colName
 * @param value to compare with
 * @return
 */
	public static void SelectLogInFromTable(String tableName,String user,String pass) {
		try {
		String query = "SELECT * FROM "+tableName+" where USERNAME='"+user+"' and PASS_WORD='"+pass+"';";
		clientCon.ExecuteQuery(query);
		}catch(Exception e) {			
			System.out.println("Exception thrown at Select from table:"+e.getMessage() +e.getClass().getName());
		}
	}
}
