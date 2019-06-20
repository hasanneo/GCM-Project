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
	
	
	public static void getMapsbyplace(String tableName,String type,String searchText)
	{
		ArrayList<String> queryArr =new ArrayList<String>();
		try {
		String query = "SELECT * FROM map where "+"MAP_NAME" +" IN (SELECT MAP_NAME FROM places_in_maps WHERE places_in_maps.PLACE_NAME like '%"+searchText+"%');";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
		}catch(Exception e) {			
			System.out.println("Exception thrown at Select from table:"+e.getMessage() +e.getClass().getName());
		}

	}
	
	public static void getMapsbydesc(String tableName,String type,String searchText)
	{
		ArrayList<String> queryArr =new ArrayList<String>();
		try {
		String query = "SELECT * FROM "+tableName+" where CITY_NAME IN"+"(select CITY_NAME from city where cityDescription LIKE "+"'%"+searchText+"%')"+"UNION SELECT * FROM "+tableName+""
		+ " where MAP_NAME IN (SELECT " + "places_in_maps.MAP_NAME " +"FROM " + " places_in_maps " + " INNER JOIN " + " places ON places.NAME = places_in_maps.PLACE_NAME where DESCRIPTION like '%"+searchText+"%');";
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
	/**
	 * Insert a place into the places_in_maps table.
	 * @param place
	 * @author Hasan
	 */
	public static void InsertIntoPlacesInMaps(PlaceInMap place) {
		ArrayList<String> queryArr =new ArrayList<String>();
		String query = "INSERT INTO places_in_maps(MAP_VERSION, MAP_NAME, PLACE_NAME, X_LOCATION, Y_LOCATION)VALUES (?,?,?,?,?)";
		queryArr.addAll(place.GetFieldsAsArrayList());
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	/**
	 * A generic method for inserting a row to a table (convert all values to string before using this method)
	 * @param tableName table name in the database
	 * @param tableColums arraylist of table column names
	 * @param values the arraylist of values required to insert
	 * @author Hasan
	 */
	public static void InsertIntoTable(String tableName,ArrayList<String> tableColums,ArrayList<String> values) {
		ArrayList<String> queryArr=new ArrayList<String>();
		String query = "INSERT INTO "+tableName+"(";
		for(int i=0;i<tableColums.size();i++) {
			if(i==tableColums.size()-1)
				query=query.concat(tableColums.get(i)+")");
			else
				query=query.concat(tableColums.get(i)+",");
		}
		query=query.concat("VALUES (");
		for(int i=0;i<values.size();i++) {
			if(i==values.size()-1)
				query=query.concat("?);");
			else 
				query=query.concat("?,");
		}
		System.out.println(query);
		queryArr.addAll(values);
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	/**
	 * Generic select one column from table query by value.
	 * @param tableName -name of the table in the DB
	 * @param columnName -name of the column that you want to show
	 * @param compareColumn -name of the column that you want to compare values with
	 * @param value -the actual compare value.
	 */
	public static void GenericSelectFromTable(String tableName,String columnName,String compareColumn,String value) {
		ArrayList<String> queryArr=new ArrayList<String>();
		String query = "SELECT "+columnName+" FROM `"+tableName+"` WHERE "+compareColumn+"='"+value+"';";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}
	/**
	 * Generic select one column from table query without compare
	 * @param tableName -name of the table in the DB
	 * @param columnName -name of the column that you want to show
	 * @param compareColumn -name of the column that you want to compare values with
	 *
	 */
	public static void GenericSelectFromTable(String tableName,String columnName) {
		ArrayList<String> queryArr=new ArrayList<String>();
		String query = "SELECT "+columnName+" FROM `"+tableName+"`;";
		queryArr.add(query);
		queryArr.add("select");
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
