package controller;

import java.util.ArrayList;

import client.ClientConnection;

import entity.Account;

import entity.City;
import entity.Map;
import entity.PlaceInMap;


/**
 * 
 * 
 *A class defining the queries that will be sent to the server, and other data base uses and set up.
 *@author Hasan
 *@date 6/17/2019
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
 *
 * @author Hasan
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
/**
 * 
 * 
 * Creating anew user account in the DB
 * @param account account entity
 * @return the result will be set in the client server object
 * @author Hasan
 */
	public static void InsertNewUser(Account account) {
		ArrayList<String> queryArr =new ArrayList<String>();
		String query = "INSERT INTO accounts(FIRST_NAME, LAST_NAME, USERNAME, PASS_WORD, PHONE_NUMBER, EMAIL, USER_TYPE)VALUES (?,?,?,?,?,?,?)";
		queryArr.addAll(account.GetFieldsAsList());
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	
	/**
	 * Will get the all rows from the map table except for the blob column.
	 * @author Hasan
	 */
	public static void GetMapsFromDB() {
		ArrayList<String> queryArr =new ArrayList<String>();
		String query="SELECT `MAP_NAME`,`MAP_DESC`,`CITY_NAME`,`MAP_VERSION` FROM `MAP`";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}
	/**
	 * Executes query to get file from the DB
	 * @param tableName
	 * @param searchByCol-column to search by in the table.( for example mapName)
	 * @param target-word to search by
	 * @param blobColumn-name of the blob column in the table
	 * @author Hasan
	 */
	public static void GetFileFromTable(String tableName,String searchByCol,String target,String blobColumn) {
		ArrayList<String> queryArr =new ArrayList<String>();
		String query="SELECT "+blobColumn+" FROM "+tableName+" WHERE "+searchByCol+"='"+target+"'";
		queryArr.add(query);
		queryArr.add(blobColumn);
		queryArr.add(target);
		queryArr.add("file");
		clientCon.ExecuteQuery(queryArr);
	}
	/**
	 * A method that will return true if the data was successfully inserted.
	 * @param newCity
	 * @return
	 * @author Jawad
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
	/**
	 * Select all rows from table in the data base.
	 * @param tableName table name in the DB
	 * @author Hasan
	 */
	public static void SelectAllRowsFromTable(String tableName) {
		ArrayList<String> queryArr =new ArrayList<String>();
		String query="SELECT * FROM `"+tableName+"`;";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}
	/**
	 * Select all rows from table in the data base based on search value given.
	 * @param tableName table name in the DB
	 * @param tableField the table field that is searched by
	 * @param searchValue value to compare by in the table
	 * @author Hasan
	 */
	public static void SelectAllRowsFromTable(String tableName,String tableField,String searchValue) {
		ArrayList<String> queryArr =new ArrayList<String>();
		String query="SELECT * FROM `"+tableName+"` where "+tableField+"='"+searchValue+"';";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}
	public static void InsertIntoPlacesInMaps(PlaceInMap place) {
		ArrayList<String> queryArr =new ArrayList<String>();
		String query = "INSERT INTO places_in_maps(MAP_VERSION, MAP_NAME, PLACE_NAME, X_LOCATION, Y_LOCATION)VALUES (?,?,?,?,?)";
		queryArr.addAll(place.GetFieldsAsArrayList());
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	/*majd
	public static void SelectUserTypeFromTable(String tableName) {
		ArrayList<String> queryArr =new ArrayList<String>();
		try {
		String query = "SELECT * FROM "+tableName+"";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
		}catch(Exception e) {			
			System.out.println("Exception thrown at Select from table:"+e.getMessage() +e.getClass().getName());
		}
	}*/

}
