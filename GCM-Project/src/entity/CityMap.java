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
public class CityMap {
	private String cityName;
	private String info;
	private String mapName;
	private String mapVersion;
	private String authorized;
	/**
	 * @param cityName
	 * @param info
	 * @param mapName
	 */
	public CityMap(String cityName, String info, String mapName) {
		super();
		this.cityName = cityName;
		this.info = info;
		this.mapName = mapName;
	}
	
	/**
	 * @param cityName
	 * @param info
	 * @param mapName
	 * @param mapVersion
	 * @param authorized
	 */
	public CityMap(String cityName, String info, String mapName, String mapVersion, String authorized) {
		super();
		this.cityName = cityName;
		this.info = info;
		this.mapName = mapName;
		this.mapVersion = mapVersion;
		this.authorized = authorized;
	}



	/**
	 * 
	 */
	public CityMap() {
		
	}

	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	@Override
	public String toString() {
		return "CityMap [cityName=" + cityName + ", info=" + info + ", mapName=" + mapName + "]";
	}

	public String getMapVersion() {
		return mapVersion;
	}

	public void setMapVersion(String mapVersion) {
		this.mapVersion = mapVersion;
	}

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}
	
	
}
