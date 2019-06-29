/**
 * 
 */
package entity;

import java.util.ArrayList;
import java.util.List;

import controller.DataBaseController;
import javafx.scene.control.CheckBox;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class MapVersionNotification extends Notification {
	String cityName;
	String mapName;
	String mapVersion;
	CheckBox approaveCheck = new CheckBox();

	/**
	 * @param request
	 * @param requestUser
	 * @param requestInfo
	 * @param cityName
	 * @param mapName
	 * @param mapVersion
	 */
	public MapVersionNotification(String request, String requestUser, ArrayList<String> requestInfo, Map map,
			String mapVersion) {
		super(request, requestUser, requestInfo);
		this.cityName = map.getCityName();
		this.mapName = map.getMapName();
		this.mapVersion = mapVersion;
	}

	public MapVersionNotification(String request, String requestUser, String requestInfo, String cityName,
			String mapName, String mapVersion) {
		super(request, requestUser, requestInfo);
		this.cityName = cityName;
		this.mapName = mapName;
		this.mapVersion = mapVersion;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapVersion() {
		return mapVersion;
	}

	public void setMapVersion(String mapVersion) {
		this.mapVersion = mapVersion;
	}

	public void SendNotificationForManagerApproval() {
		Double ver = Double.parseDouble(mapVersion);
		ver += (double) 0.1;// set new version for request
		mapVersion = ver.toString();
		ArrayList<String> vals = new ArrayList<String>();
		ArrayList<String> cols = new ArrayList<String>();
		/*
		ArrayList<String> vals = new ArrayList<String>(
				List.of(cityName, this.getRequestInfo(), mapName, mapVersion, this.requestUser));
		ArrayList<String> cols = new ArrayList<String>(
				List.of("CITY_NAME", "INFO", "MAP_NAME", "MAP_VERSION", "USER_ACCOUNT"));*/
		cols.add("CITY_NAME");
		cols.add("INFO");
		cols.add("MAP_NAME");
		cols.add("MAP_VERSION");
		cols.add("USER_ACCOUNT");
		vals.add(this.cityName);
		vals.add(this.getRequestInfo());
		vals.add(mapName);
		vals.add(mapVersion);
		vals.add(this.requestUser);
		DataBaseController.InsertIntoTable("Maps_TO_AUTHORIZE", cols, vals);
	}

	public CheckBox getApproaveCheck() {
		return approaveCheck;
	}

	public void setApproaveCheck(CheckBox approaveCheck) {
		this.approaveCheck = approaveCheck;
	}

}
