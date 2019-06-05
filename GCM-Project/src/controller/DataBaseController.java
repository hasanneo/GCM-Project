package controller;

import java.util.ArrayList;

import client.ClientConnection;


public class DataBaseController {

	private ArrayList[] queryString;
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
	public static void SelectFromTable(String tableName,String colName,String value) {
		try {
		//String query = "SELECT * FROM accounts where username='john';";
			String query = "SELECT * FROM "+tableName+" where "+colName+"='"+value+"';";
		clientCon.ExecuteQuery(query);
		}catch(Exception e) {			
			System.out.println("Exception thrown at Select from table:"+e.getMessage() +e.getClass().getName());
		}
	}
}
