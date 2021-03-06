package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import client.ClientConnection;

import entity.Account;

import entity.City;
import entity.Map;
import entity.PlaceInMap;

/**
 * 
 * 
 * A class defining the queries that will be sent to the server, and other data
 * base uses and set up.
 * 
 * @author majdh
 * @author Hasan
 * @date 6/17/2019
 *
 */
public class DataBaseController {

	public static ClientConnection clientCon = null;

	/**
	 * Setting up the client connection
	 * 
	 * @param newClientConnection
	 */
	public static void InitiateClient(ClientConnection newClientConnection) {
		clientCon = newClientConnection;
	}

	/**
	 * A method that will return true if the data was successfully selected.
	 * 
	 * @param tableName
	 * @param colName
	 * @param value     to compare with
	 *
	 * @author Hasan
	 */
	public static void SelectAccountFromTable(String tableName, String user, String pass) {
		ArrayList<String> queryArr = new ArrayList<String>();
		try {
			String query = "SELECT * FROM " + tableName + " where USERNAME='" + user + "' and PASS_WORD='" + pass
					+ "';";
			queryArr.add(query);
			queryArr.add("select");
			clientCon.ExecuteQuery(queryArr);
		} catch (Exception e) {
			System.out.println("Exception thrown at Select from table:" + e.getMessage() + e.getClass().getName());
		}
	}

	/**
	 * 
	 * 
	 * Creating anew user account in the DB
	 * 
	 * @param account account entity
	 * @return the result will be set in the client server object
	 * @author Hasan
	 */
	public static void InsertNewUser(Account account) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "INSERT INTO accounts(FIRST_NAME, LAST_NAME, USERNAME, PASS_WORD, PHONE_NUMBER, EMAIL, USER_TYPE)VALUES (?,?,?,?,?,?,?)";
		queryArr.addAll(account.GetFieldsAsList());
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	

	/**
	 * @author majdh
	 * @param cityName=city name 
	 * @param DateFrom=the first selected date.
	 * @param DateTo=the second selected date. 
	 * 
	 * */
	public static void SelectReportDataToCombobox(String cityName ,LocalDate DateFrom ,LocalDate DateTo){
		ArrayList<String> queryArr = new ArrayList<String>();
		try {
		                  //SELECT * FROM viewreportstable WHERE CITY_NAME='akko' AND (ReportDate BETWEEN '2019-06-1' AND '2020-09-29');
			String query = "SELECT * FROM viewreportstable WHERE CITY_NAME='"+cityName+"' AND (ReportDate BETWEEN '"+DateFrom+"' AND '"+DateTo+"');";
			queryArr.add(query);
			queryArr.add("select");
			clientCon.ExecuteQuery(queryArr);
		} catch (Exception e) {
			System.out.println("Exception thrown at Select from table:" + e.getMessage() + e.getClass().getName());
		}
		
	}
	
	
	/**
	 * @author majdh
	 * @param DateFrom=the first selected date.
	 * @param DateTo=the second selected date. 
	 * 
	 * */
	public static void SelectReportToAllCities(LocalDate DateFrom ,LocalDate DateTo){
		ArrayList<String> queryArr = new ArrayList<String>();
		try {             //SELECT * FROM viewreportstable WHERE (ReportDate BETWEEN '2019-06-1   ' AND '2020-09-29');
			String query = "SELECT * FROM viewreportstable WHERE (ReportDate BETWEEN '"+DateFrom+"' AND '"+DateTo+"');";
			queryArr.add(query);
			queryArr.add("select");
			clientCon.ExecuteQuery(queryArr);
		} catch (Exception e) {
			System.out.println("Exception thrown at Select from table:" + e.getMessage() + e.getClass().getName());
		}
	}
	/**
	 * @author majdh
	 * simple function to know the date that defined in the DB.
	 * 
	 * */
	public static void KnowDate(){
		ArrayList<String> queryArr =new ArrayList<String>();
		String query ="INSERT INTO `gcm`.`datet` (`ReportDate`) VALUES (current_date());";
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	
	/**
	 * Delete a row from table.
	 * 
	 * @param tableName     -name of the table in the DB.
	 * @param columnName    -name of the column that you compare by.
	 * @param columnCompare -the compare value that is given
	 * @author Hasan
	 */
	public static void DeleteRow(String tableName, String columnName, String columnCompare) {
		String query = "DELETE FROM " + tableName + " WHERE " + columnName + "='" + columnCompare + "'";
		ArrayList<String> queryArr = new ArrayList<String>();
		queryArr.add(query);
		queryArr.add("delete");
		clientCon.ExecuteQuery(queryArr);
	}
	/**
	 * 
	 * 
	 * @author majdh
	 * @param cityName=city Name.
	 * @param reportTableMapsNumber=Maps Number.
	 * @param reportTablSubscriptions=Subscriptions Number.
	 * @param reportTablSubscriptionRenew=Subscription Renew Number.
	 * @param reportTablViews=Views Number.
	 * @param reportTablDownloads=Downloads Number.
	 * @param reportTablOneTimePurchase=One Time Purchase Number.
	 * 
	 * this function inserts the values to the viewreportstable table. 
	 * 
	 * */
	public static void InsertReportsToDB(String cityName, int reportTableMapsNumber, 
			            int reportTablSubscriptions,int reportTablSubscriptionRenew,
			            int reportTablViews, int reportTablDownloads,int reportTablOneTimePurchase)
	{
		ArrayList<String> queryArr =new ArrayList<String>();
		String query = "INSERT INTO `gcm`.`viewreportstable` (`CITY_NAME`, `MapsNum`, `SubscriptionsNum`, `SubscriptionRenewNum`, `ViewsNum`, `DownloadsNum`, `OneTimePurchase`,`ReportDate`) VALUES(?,?,?,?,?,?,?,current_date())";
		queryArr.add(cityName);
		queryArr.add(Integer.toString(reportTableMapsNumber));
		queryArr.add(Integer.toString(reportTablSubscriptions));
		queryArr.add(Integer.toString(reportTablSubscriptionRenew));
		queryArr.add(Integer.toString(reportTablViews));
		queryArr.add(Integer.toString(reportTablDownloads));
		queryArr.add(Integer.toString(reportTablOneTimePurchase));
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}


	/**
	 * @author majdh
	 * @param cityName=city name  
	 * @param Onetimepurchase = the One time purchase price. 
	 * @param SubscriptionPurchase = the Subscription Purchase price. 
	 * @param status = the price status.
	 * 
	 * Department content manager inserts & updates the values in the city_maps_rate table.
	 * Company manager decides the price status. 
	 * 
	 * 
	 * 
	 * this function is to the Update process.
	 * 
	 * */
	public static void UpdateCityMapsRates(String CityName,String Onetimepurchase,String SubscriptionPurchase,String status) {
		//UPDATE `gcm`.`city_maps_rate` SET `OneTimePurchase_price` = '60', `SubsciptionPurchase_price` = '60', `status` = 'waiting' WHERE (`CITY_NAME` = 'Sekhnin');
		ArrayList<String> queryArr =new ArrayList<String>();
		String query = "UPDATE `gcm`.`city_maps_rate` SET `OneTimePurchase_price` = '"+Onetimepurchase+"', `SubsciptionPurchase_price` = '"+SubscriptionPurchase+"',`status` = '"+status+"' WHERE (`CITY_NAME` = '"+CityName+"')";
		queryArr.add(query);
     	queryArr.add("update");
		clientCon.ExecuteQuery(queryArr);
	}
	
	
	/**
	 * @author majdh
	 * @param cityName=city name  
	 * @param Onetimepurchase = the One time purchase price. 
	 * @param SubscriptionPurchase = the Subscription Purchase price. 
	 * @param status = the price status.
	 * 
	 * Department content manager inserts & updates the values in the city_maps_rate table.
	 * Company manager decides the price status. 
	 * 
	 * 
	 * 
	 * this function is to the Insert process.
	 * 
	 * */
	public static void InsertCityMapsRates(String CityName,String Onetimepurchase,String SubscriptionPurchase,String status) {
		//INSERT INTO `gcm`.`city_maps_rate` (`CITY_NAME`, `OneTimePurchase_price`, `SubsciptionPurchase_price`, `status`) VALUES ('Nazerth', '10', '10', 'disapprove');
		ArrayList<String> queryArr =new ArrayList<String>();
		String query = "INSERT INTO `gcm`.`city_maps_rate` (`CITY_NAME`, `OneTimePurchase_price`, `SubsciptionPurchase_price`, `status`) VALUES(?,?,?,?)";
		queryArr.add(CityName);
		queryArr.add(Onetimepurchase);
		queryArr.add(SubscriptionPurchase);
		queryArr.add(status);
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	
	
	

	/**
	 * 
	 * @author majd
	 * 
	 * 
	 * Increment with value 1 in "viewreportstable" table ,
	 * this function that takes column name,city names,date
	 * and updated the wanted cell on DB.
	 *     
	 * 
	 * */
	public static void CityIncFieldsInDB(String fName,String CityName,String Date) {
		
		ArrayList<String> queryArr =new ArrayList<String>();
		String query1="UPDATE viewreportstable SET "+fName+"="+ fName +"+ 1 WHERE (CITY_NAME='"+CityName+"')AND(ReportDate='"+Date+"');";
		queryArr.add(query1);
     	queryArr.add("update");
		clientCon.ExecuteQuery(queryArr);
	}
	

	/**
	 * Will get the all rows from the map table except for the blob column.
	 * 
	 * @author Hasan
	 */
	public static void GetMapsFromDB() {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT `MAP_NAME`,`MAP_DESC`,`CITY_NAME`,`MAP_VERSION` FROM `MAP`";
		queryArr.add(query);
		queryArr.add("select");
		System.out.println("ABOUT TO EXECUTE >>" + queryArr.toString());
		clientCon.ExecuteQuery(queryArr);
	}

	/**
	 * Executes query to get file from the DB
	 * 
	 * @param tableName
	 * @param           searchByCol-column to search by in the table.( for example
	 *                  mapName)
	 * @param           target-word to search by
	 * @param           blobColumn-name of the blob column in the table
	 * @author Hasan
	 */
	public static void GetFileFromTable(String tableName, String searchByCol, String target, String blobColumn) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT " + blobColumn + " FROM " + tableName + " WHERE " + searchByCol + "='" + target + "'";
		queryArr.add(query);
		queryArr.add(blobColumn);
		queryArr.add(target);
		queryArr.add("file");
		clientCon.ExecuteQuery(queryArr);
	}

	/**
	 * A method that will return true if the data was successfully inserted.
	 * 
	 * @param newCity
	 * @return
	 * @author Jawad
	 */
	public static void AddCityToDb(City newCity) {
		try {
			String query = "INSERT INTO city" + "VALUES (" + newCity.getCityName() + "," + newCity.getNumberOfMaps()
					+ "," + newCity.getNumberOfPOI() + "," + newCity.getNumberOfTours() + ","
					+ newCity.getNumberOfVersions() + ")" + "';";
			clientCon.ExecuteQuery(query);
		} catch (Exception e) {
			System.out.println("Exception thrown at Select from table:" + e.getMessage() + e.getClass().getName());
		}
	}

	public static void getMaps(String tableName, String type, String searchText) {
		ArrayList<String> queryArr = new ArrayList<String>();
		try {

			String query = "SELECT MAP_NAME, MAP_DESC, CITY_NAME FROM " + tableName + " where " + type + " = " + "'"
					+ searchText + "'" + ";";

			queryArr.add(query);
			queryArr.add("select");
			clientCon.ExecuteQuery(queryArr);
		} catch (Exception e) {
			System.out.println("Exception thrown at Select from table:" + e.getMessage() + e.getClass().getName());
		}

	}
	

	public static void getMapsbyplace(String tableName, String type, String searchText) {
		ArrayList<String> queryArr = new ArrayList<String>();
		try {
			String query = "SELECT  MAP_NAME, MAP_DESC, CITY_NAME FROM map where " + "MAP_NAME"
					+ " IN (SELECT MAP_NAME FROM places_in_maps WHERE places_in_maps.PLACE_NAME = '" + searchText
					+ "' AND APPROVED = 1);";
			queryArr.add(query);
			queryArr.add("select");
			clientCon.ExecuteQuery(queryArr);
		} catch (Exception e) {
			System.out.println("Exception thrown at Select from table:" + e.getMessage() + e.getClass().getName());
		}
	}

	public static void getMapsbydesc(String tableName, String type, String searchText) {
		ArrayList<String> queryArr = new ArrayList<String>();
		try {
			/*
			 * String query = "SELECT  MAP_NAME, MAP_DESC, CITY_NAME FROM " + tableName +
			 * " where CITY_NAME IN" +
			 * "(select CITY_NAME from city where cityDescription LIKE " + "'%" + searchText
			 * + "%')" + "UNION SELECT MAP_NAME, MAP_DESC, CITY_NAME FROM " + tableName + ""
			 * + " where MAP_NAME IN (SELECT " + "places_in_maps.MAP_NAME " + "FROM " +
			 * " places_in_maps " + " INNER JOIN " +
			 * " places ON places.NAME = places_in_maps.PLACE_NAME where DESCRIPTION like '%"
			 * + searchText + "%');";
			 */
			String query = "SELECT  MAP_NAME, MAP_DESC, CITY_NAME FROM " + tableName + " WHERE MAP_DESC LIKE '%"
					+ searchText + "%'";
			queryArr.add(query);
			queryArr.add("select");
			clientCon.ExecuteQuery(queryArr);
		} catch (Exception e) {
			System.out.println("Exception thrown at Select from table:" + e.getMessage() + e.getClass().getName());
		}

	}
	public static void getCityByName(String cityName)
	{
		ArrayList<String> queryArr = new ArrayList<String>();
		try {
			String query = "SELECT * FROM city where CITY_NAME = '"  + cityName + "';";
			queryArr.add(query);
			queryArr.add("select");
			clientCon.ExecuteQuery(queryArr);
		} catch (Exception e) {
			System.out.println("Exception thrown at Select from table:" + e.getMessage() + e.getClass().getName());
		}
	}

	/**
	 * Select all rows from table in the data base.
	 * 
	 * @param tableName table name in the DB
	 * @author Hasan
	 */
	public static void SelectAllRowsFromTable(String tableName) {

		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT * FROM `" + tableName + "`;";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}

	/**
	 * Select all rows from table in the data base based on search value given.
	 * 
	 * @param tableName   table name in the DB
	 * @param tableField  the table field that is searched by
	 * @param searchValue value to compare by in the table
	 * @author Hasan
	 */
	public static void SelectAllRowsFromTable(String tableName, String tableField, String searchValue) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT * FROM `" + tableName + "` where " + tableField + "='" + searchValue + "';";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}

	/**
	 * Insert a place into the places_in_maps table.
	 * 
	 * @param place
	 * @author Hasan
	 */
	public static void InsertIntoPlacesInMaps(PlaceInMap place) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "INSERT INTO places_in_maps(MAP_NAME, PLACE_NAME, X_LOCATION, Y_LOCATION)VALUES (?,?,?,?)";
		queryArr.addAll(place.GetFieldsAsArrayList());
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}

	/**
	 * A generic method for inserting a row to a table (convert all values to string
	 * before using this method)
	 * 
	 * @param tableName   table name in the database
	 * @param tableColums arraylist of table column names
	 * @param values      the arraylist of values required to insert
	 * @author Hasan
	 */
	public static void InsertIntoTable(String tableName, ArrayList<String> tableColums, ArrayList<String> values) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "INSERT INTO " + tableName + "(";
		for (int i = 0; i < tableColums.size(); i++) {
			if (i == tableColums.size() - 1)
				query = query.concat(tableColums.get(i) + ")");
			else
				query = query.concat(tableColums.get(i) + ",");
		}
		query = query.concat("VALUES (");
		for (int i = 0; i < values.size(); i++) {
			if (i == values.size() - 1)
				query = query.concat("?);");
			else
				query = query.concat("?,");
		}
		System.out.println(query);
		queryArr.addAll(values);
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}

	/**
	 * Generic select one column from table query by value.
	 * 
	 * @param tableName     -name of the table in the DB
	 * @param columnName    -name of the column that you want to show
	 * @param compareColumn -name of the column that you want to compare values with
	 * @param value         -the actual compare value.
	 * @author Hasan
	 */
	public static void GenericSelectFromTable(String tableName, String columnName, String compareColumn, String value) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT " + columnName + " FROM `" + tableName + "` WHERE " + compareColumn + "='" + value
				+ "';";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}
/**
 * @author mohamed
 * @param tableName
 * @param columnName
 * @param compareColumn
 * @param value
 * @param compareColumn2
 * @param authorized
 * @param compareColumn3
 * @param map_version
 */
	public static void SelectCityNameFieldFromTable(String tableName, String columnName, String compareColumn, String value,String compareColumn2,int authorized,String compareColumn3,int map_version) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT " + columnName + " FROM `" + tableName + "` WHERE " + compareColumn + "='" + value
				+"' AND "+compareColumn2+"="+authorized + " AND "+compareColumn3+"="+map_version+ ";";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}
	
	
	
	/**
	 * Generic select one column from table query without compare
	 * 
	 * @param tableName  -name of the table in the DB
	 * @param columnName -name of the column that you want to show
	 * @author Hasan
	 *
	 */
	public static void GenericSelectFromTable(String tableName, String columnName) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT " + columnName + " FROM `" + tableName + "`;";
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}

	/**
	 * A generic function that will update a row in a table.(Not checked if you give
	 * false parameters yet)
	 * 
	 * @param tableName     -table name in the db.
	 * @param tableColumns  -insert your table columns in the appropriate manner as
	 *                      in the defined table.
	 * @param newValues     - your new values arraylist. The order here matters
	 *                      (reletive to the tableColumns order given).
	 * @param compareColumn -the coulmn you want to compare to (EX.
	 *                      name,lastname,id,...).
	 * @param comepareValue - the compare value that you compare with.
	 * @return will return the number of affected rows in ->
	 *         clientCon.getServerObject()
	 * @author Hasan
	 */
	public static void GenericUpdateTableRow(String tableName, ArrayList<String> tableColumns,
			ArrayList<String> newValues, String compareColumn, String comepareValue) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "UPDATE " + tableName + " SET ";
		for (int i = 0; i < tableColumns.size(); i++) {
			if (i == tableColumns.size() - 1) {
				query = query.concat("`" + tableColumns.get(i) + "` = '" + newValues.get(i) + "' WHERE ");
				query = query.concat("`" + compareColumn + "`='" + comepareValue + "';");
			} else {
				query = query.concat("`" + tableColumns.get(i) + "` = '" + newValues.get(i) + "' ,");
			}
		}
		queryArr.add(query);
		queryArr.add("update");
		clientCon.ExecuteQuery(queryArr);
	}

	/**
	 * This will select the columns that is provided with on compare value.
	 * 
	 * @param tableName
	 * @param tableColumns
	 * @param compareColumn
	 * @param comapreValue
	 * @author Hasan
	 */
	public static void GenericSelectColumnsFromTable(String tableName, ArrayList<String> tableColumns,
			String compareColumn, String comapreValue) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT ";
		// add columns to the query
		for (int i = 0; i < tableColumns.size(); i++) {
			if (i == tableColumns.size() - 1) {
				query = query.concat(tableColumns.get(i) + " FROM " + tableName + " WHERE " + compareColumn + "='"
						+ comapreValue + "'");
			} else {
				query = query.concat(tableColumns.get(i) + ",");
			}
		}
		queryArr.add(query);
		queryArr.add("select");
		System.out.println("ABOUT TO EXECUTE>>" + queryArr.toString());
		clientCon.ExecuteQuery(queryArr);
	}
/*
	public static void SelectPurchasesBasedOnCityAndUserName(String userName, String cityName) {
		String query="SELECT CITY FROM purchase_history WHERE USERNAME='"+userName+"' AND CITY='"+
	}*/

	/**
	 * This will select the columns that is provided
	 * 
	 * @param tableName    -name of the table in the db
	 * @param tableColumns -provided table columns
	 * @author Hasan
	 */
	public static void GenericSelectColumnsFromTable(String tableName, ArrayList<String> tableColumns) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "SELECT ";
		// add columns to the query
		for (int i = 0; i < tableColumns.size(); i++) {
			if (i == tableColumns.size() - 1) {
				query = query.concat(tableColumns.get(i) + " FROM " + tableName);
			} else {
				query = query.concat(tableColumns.get(i) + ",");
			}
		}
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}

	public static void GetRowCount(String tableName, String columnName, String columnCompare) {
		String query = "SELECT * FROM " + tableName;
		ArrayList<String> queryArr = new ArrayList<String>();
		if (columnName != null && columnCompare != null) {
			query = "SELECT * FROM " + tableName + " WHERE " + columnName + "='" + columnCompare + "';";
		}
		queryArr.add(query);
		queryArr.add("select");
		clientCon.ExecuteQuery(queryArr);
	}
	
	/**
	 * @author mohamed
	 * @param TableName the name of the table we want to update
	 * @param ColumnName the name of the column that we want to make changes on it
	 * @param NewValue   the new value that we want to put(the updated data)
	 * @param KeyCol     the key column that we choose the cell according to it
	 * @param NameOfKeyColumn   the name of the key column
	 */
	public static void updatefield(String TableName, String ColumnName, int NewValue,String KeyCol,String NameOfKeyColumn ) {
		ArrayList<String> queryArr = new ArrayList<String>();
		String query = "UPDATE " + TableName+ " SET " + ColumnName + " = " + NewValue + " WHERE " + KeyCol + " = '"+ NameOfKeyColumn+"';";
		queryArr.add(query);
		queryArr.add("update");
		clientCon.ExecuteQuery(queryArr);
	}
	
	

	/**
	 * Insert into user_notifications table.
	 * 
	 * @param values -username and notification header and content
	 * @author Hasan
	 */
	public static void InsertIntoUsersNotifications(ArrayList<String> values) {
		String query = "INSERT INTO user_notifications(USERNAME,NOTIFICATION_HEADER,NOTIFICATION)VALUES (?,?,?)";
		ArrayList<String> queryArr = new ArrayList<String>();
		queryArr.add(values.get(0));
		queryArr.add(values.get(1));
		queryArr.add(values.get(2));
		queryArr.add(query);
		queryArr.add("insert");
		clientCon.ExecuteQuery(queryArr);
	}
	/**
	 * Delete all records present in the table that is given.
	 * @param tableName -table name in the DB
	 */
	public static void DeleteAllRecordFromTable(String tableName) {
		String query = "DELETE FROM " + tableName + ";";
		ArrayList<String> queryArr = new ArrayList<String>();
		queryArr.add(query);
		queryArr.add("delete");
	}
}
