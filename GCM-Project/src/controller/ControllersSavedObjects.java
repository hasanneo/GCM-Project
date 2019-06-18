/**
 * 
 */
package controller;

import java.util.ArrayList;

import entity.Map;

/**
 * This class will act as a communication between controllers
 *
 * @author Hasan
 * 
 */
public class ControllersSavedObjects {
	public static Map selectedMapFromCombo = null;

	public static void SetSelectedMapFromCombo(String mapName, String mapDescirptionString, String cityName,
			String mapVersion) {
		selectedMapFromCombo = new Map(mapName, mapDescirptionString, cityName, mapVersion);
	}

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
}
