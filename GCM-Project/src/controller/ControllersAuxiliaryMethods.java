/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;

import entity.City;
import entity.CityMap;
import entity.Map;
import entity.MapVersionNotification;
import entity.Notification;

/**
 * This class will act as a communication between controllers
 *
 * @author Hasan
 * 
 */
public class ControllersAuxiliaryMethods {
	public static Map selectedMapFromCombo = null;

	/**
	 * Auxiliary function to know which map was selected from the combo box from the
	 * previous screen.
	 * 
	 * @param mapName
	 * @param mapDescirptionString
	 * @param cityName
	 * @param mapVersion
	 * @author Hasan
	 */
	public static void SetSelectedMapFromCombo(String mapName, String mapDescirptionString, String cityName,
			String mapVersion) {
		selectedMapFromCombo = new Map(mapName, mapDescirptionString, cityName, mapVersion);
	}

	/**
	 * Auxiliary to get the rows from the map tablse as an arraylist.
	 * 
	 * @return ArrayList of all the maps (without files)
	 * @author Hasan
	 */
	public static ArrayList<Map> GetMapRowsAsList() {
		// get map list from db
		int row = 0;
		int tableColumns = 4;
		String[] mapsArray;
		ArrayList<Map> maps = new ArrayList<Map>();
		DataBaseController.GetMapsFromDB();// get maps from DB
		mapsArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		// populate the maps array list
		for (int i = 0; row < mapsArray.length / tableColumns; i += tableColumns, row++) {
			maps.add(new Map(mapsArray[i], mapsArray[i + 1], mapsArray[i + 2], mapsArray[i + 3]));
		}
		return maps;
	}

	/**
	 * 
	 * @param rowsArray
	 * @param tableColumnsNumber
	 * @return arraylist of city maps 
	 */
	public static ArrayList<CityMap> GetCityMapsRowsAsList(String[] rowsArray, int tableColumnsNumber) {
		int row = 0;
		int tableColumns = tableColumnsNumber;
		ArrayList<CityMap> rows = new ArrayList<CityMap>();
		CityMap cityMap;
		// populate the array list
		for (int i = 0; row < rowsArray.length / tableColumns; i += tableColumns, row++) {

			if (tableColumnsNumber == 2) {
				cityMap=new CityMap();
				cityMap.setMapName(rowsArray[i]);
				cityMap.setInfo(rowsArray[i+1]);
				rows.add(cityMap);
			} else
				rows.add(new CityMap(rowsArray[i + 1], rowsArray[i + 2], rowsArray[i + 3]));
		}
		return rows;
	}

	/**
	 * 
	 * 
	 * @param tableName: table to search for maps
	 * @param type: map type
	 * @param searchText: value to look by
	 * @return
	 */
	public static ArrayList<Map> GetMapRowsAsList(String tableName, String type, String searchText) {
		// get map list from db
		int i1, i2, i3, i4;

		int row = 0;
		int tableColumns = 3;// changed to 6 by hasan was 4 before
		String[] mapsArray;
		ArrayList<Map> maps = new ArrayList<Map>();
		if (type == "CITY_NAME") {
			DataBaseController.getMaps(tableName, type, searchText);// get maps from DB
			
		} else {
			if (type == "DESC")
				DataBaseController.getMapsbydesc(tableName, type, searchText);
			else {
				if (type == "Place")
					DataBaseController.getMapsbyplace(tableName, null, searchText);// get maps from DB
			}
		}
		if (DataBaseController.clientCon.GetServerObject() == null) {
			return null;
		}
		mapsArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		// populate the maps array list
		for (int i = 0; row < mapsArray.length / tableColumns; i += tableColumns, row++) {
			i1 = i;
			i2 = i + 1;
			i3 = i + 2;
			//i4 = i + 4;

			maps.add(new Map(mapsArray[i1], mapsArray[i2], mapsArray[i3]));//, mapsArray[i2]));
		}
		return maps;
	}

	public static ArrayList<CityMap> GetCityMapsRowsAsListForRelease(String[] rowsArray, int tableColumnsNumber) {
		int row = 0;
		int tableColumns = tableColumnsNumber;
		ArrayList<CityMap> rows = new ArrayList<CityMap>();
		CityMap c;
		// populate the array list
		for (int i = 0; row < rowsArray.length / tableColumns; i += tableColumns, row++) {
			c = new CityMap();
			c.setMapName(rowsArray[i]);
			c.setInfo(rowsArray[i + 1]);
			if (rowsArray[i + 2].equals("null"))
				c.setMapVersion("0");
			System.out.println(rowsArray[i] + " |" + rowsArray[i + 1] + " |" + rowsArray[i + 2]);
			rows.add(c);
		}
		return rows;
	}

	/**
	 * 
	 * @return the map chosen at the combobox
	 */
	public static Map getSelectedMapFromCombo() {
		return selectedMapFromCombo;
	}

	public static void setSelectedMapFromCombo(Map selectedMapFromCombo) {
		ControllersAuxiliaryMethods.selectedMapFromCombo = selectedMapFromCombo;
	}

	/**
	 * @param getObjectAsStringArray
	 * @param i
	 * @return
	 */
	public static Collection<? extends MapVersionNotification> GetTableNewVersionNotificationRowsAsList(
			String[] rowsArray, int tableColumns) {
		int row = 0;
		ArrayList<MapVersionNotification> rows = new ArrayList<MapVersionNotification>();
		// populate the array list
		for (int i = 0; row < rowsArray.length / tableColumns; i += tableColumns, row++) {
			rows.add(new MapVersionNotification("AUTHORIZE MAP", rowsArray[i + 4], rowsArray[i], rowsArray[i + 3],
					rowsArray[i + 1], rowsArray[i + 2]));
		}
		return rows;
	}

	/**
	 * 
	 * @param rowsArray -array of the rows after the select
	 * @param columns   -number of columns specified in the select
	 * @return number of rows
	 * @author Hasan
	 */
	public static int CountRows(String[] rowsArray, int columns) {
		return rowsArray.length / columns;
	}

	public static void SetCityToMap(String cityName) {
		selectedMapFromCombo.setCityName(cityName);

	}
	
	
	/**
	 * 
	 * @param cityName: which city to fetch details
	 * @return city description
	 */
	public static City getcitydetails(String cityName)
	{
		City city=null;
		DataBaseController.getCityByName(cityName);
		String[] cityarray = DataBaseController.clientCon.GetObjectAsStringArray();
		if( cityarray==null)
			return null;
		city=new City(cityName);
		city.setNumberOfPOI(Integer.parseInt(cityarray[1]));
		city.setNumberOfTours(Integer.parseInt(cityarray[2]));
		city.setCityDescription(cityarray[3]);
		return city;
		
		
	}
	
	/**
	 * 
	 * @param PlaceName: place to get from city
	 * @return: place in city
	 */
	public static String getPlaceCityName(String PlaceName)
	{
		String cityName = null;
		
		DataBaseController.GenericSelectFromTable("places", "CITY_NAME", "NAME", PlaceName);
		if (DataBaseController.clientCon.GetServerObject()!=null) {
			cityName= DataBaseController.clientCon.GetObjectAsStringArray()[0];
		}

		return cityName;

	}
}
