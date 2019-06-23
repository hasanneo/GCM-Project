/**
 * 
 */
package entity;

import java.util.ArrayList;
import java.util.List;

import controller.DataBaseController;

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

	/**
	 * @param request
	 * @param requestUser
	 * @param requestInfo
	 * @param cityName
	 * @param mapName
	 * @param mapVersion
	 */
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
		Double ver=Double.parseDouble(mapVersion);
		ver+=(double)0.1;
		mapVersion=ver.toString();
		/*ArrayList<String> vals=new ArrayList<String>(List.of(cityName,this.getRequestInfo(),mapName,mapVersion,this.requestUser));
		ArrayList<String> cols=new ArrayList<String>(List.of("CITY_NAME","INFO","MAP_NAME","MAP_VERSION","USER_ACCOUNT"));*/
		//DataBaseController.InsertIntoTable("Maps_TO_AUTHORIZE", cols, vals);
	}
}
