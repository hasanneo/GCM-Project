package controller;

import java.util.ArrayList;

import client.ClientConnection;

import entity.Account;

import entity.City;


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
	public static void SelectAccountFromTable(String tableName,String user,String pass) {
		ArrayList<String> queryArr =new ArrayList<String>();
		try {
		String query = "SELECT * FROM "+tableName+" where USERNAME='"+user+"' and PASS_WORD='"+pass+"';";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
		}catch(Exception e) {			
			System.out.println("Exception thrown at Select from table:"+e.getMessage() +e.getClass().getName());
		}
	}

	public static void InsertNewUser(Account account) {
		ArrayList<String> queryArr =new ArrayList<String>();
		String query = "INSERT INTO accounts(FIRST_NAME, LAST_NAME, USERNAME, PASS_WORD, PHONE_NUMBER, EMAIL, USER_TYPE)VALUES (?,?,?,?,?,?,?)";
		queryArr.addAll(account.GetFieldsAsList());
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	
	/**
	 * A method that will return true if the data was successfully inserted.
	 * @param newCity
	 * @return
	 */
	public static void AddCityToDb(City newCity) {
		try {
		String query = "INSERT INTO city"+"VALUES ("+newCity.getCityName()+","+newCity.getNumberOfMaps()+","+newCity.getNumberOfPOI()+","+newCity.getNumberOfTours()+","+newCity.getNumberOfVersions()+")"+"';";
		clientCon.ExecuteQuery(query);
		}catch(Exception e) {			
			System.out.println("Exception thrown at Select from table:"+e.getMessage() +e.getClass().getName());
		}
		
	}
	
	
	public static void getMaps(String tableName,String type,String searchText)
	{
		ArrayList<String> queryArr =new ArrayList<String>();
		try {
		String query = "SELECT * FROM "+tableName+" where "+type+" LIKE "+"'%"+searchText+"%'"+";";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
		}catch(Exception e) {			
			System.out.println("Exception thrown at Select from table:"+e.getMessage() +e.getClass().getName());
		}
	}
}
