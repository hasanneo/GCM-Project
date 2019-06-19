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
	
	
}
