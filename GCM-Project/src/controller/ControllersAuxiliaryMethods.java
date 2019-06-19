/**
 * 
 */
package controller;

import java.util.ArrayList;

import entity.CityMap;
import entity.Map;

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

	public static ArrayList<CityMap> GetCityMapsRowsAsList(String[] rowsArray,int tableColumnsNumber) {
		int row = 0;
		int tableColumns = tableColumnsNumber;
		ArrayList<CityMap> rows = new ArrayList<CityMap>();
		// populate the array list
		for (int i = 0; row < rowsArray.length / tableColumns; i += tableColumns, row++) {
			rows.add(new CityMap(rowsArray[i+1], rowsArray[i + 2], rowsArray[i + 3]));
		}
		return rows;
	}
	
}
