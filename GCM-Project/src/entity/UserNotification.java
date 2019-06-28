/**
 * 
 */
package entity;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class UserNotification {
	int id;
	String userName;
	String header;
	String content;
	
	/**
	 * @param id
	 * @param userName
	 * @param header
	 * @param content
	 */
	public UserNotification(int id, String userName, String header, String content) {
		super();
		this.id = id;
		this.userName = userName;
		this.header = header;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
