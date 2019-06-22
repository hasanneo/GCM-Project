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
	String requestInfo;

	/**
	 * @param request
	 * @param requestUser
	 */
	public Notification(String request, String requestUser, String requestInfo) {
		super();
		this.request = request;
		this.requestUser = requestUser;
		this.requestInfo = requestInfo;
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

	public String getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
	}

}
