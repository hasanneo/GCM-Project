/**
 * 
 */
package controller;

import entity.Map;

/**
 * This class will act as a communication between controllers
 *
 * @author Hasan
 * 
 */
public class ControllersSavedObjects {
	public static Map selectedMapFromCombo=null;
	public static void SetSelectedMapFromCombo(String mapName,String mapDescirptionString ,String cityName,String mapVersion) {
		selectedMapFromCombo=new Map(mapName,mapDescirptionString,cityName,mapVersion);
	}
}
