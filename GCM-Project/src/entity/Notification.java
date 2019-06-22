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
public class Notification {
	String request;
	String requestUser;

	/**
	 * @param request
	 * @param requestUser
	 */
	public Notification(String request, String requestUser) {
		super();
		this.request = request;
		this.requestUser = requestUser;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}
	public void SendNotificationForManagerApproval(String cityName,String info,String mapName,String mapVersion) {
		Double ver=Double.parseDouble(mapVersion);
		ver+=(double)0.1;
		mapVersion=ver.toString();
		ArrayList<String> vals=new ArrayList<String>(List.of(cityName,info,mapName,mapVersion));
		ArrayList<String> cols=new ArrayList<String>(List.of("CITY_NAME","INFO","MAP_NAME","MAP_VERSION"));
		DataBaseController.InsertIntoTable("Maps_TO_AUTHORIZE", cols, vals);
	}
}
