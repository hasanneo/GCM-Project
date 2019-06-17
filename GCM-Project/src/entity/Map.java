package entity;

import java.util.Arrays;

/**
 * 
 * @author Hasan
 *
 *
 */
public class Map {
	private String mapName;
	private String mapDescription;
	private String cityName;
	private byte[] mapFile;

	public Map(String mapName, String mapDescirption,String cityName) {
		setMapName(mapName);
		setMapDescription(mapDescirption);
		setCityName(cityName);
	}

	public Map(String mapName, String mapDescirption,String cityName, String file) {

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

	public String getMapDescription() {
		return mapDescription;
	}

	public void setMapDescription(String mapDescription) {
		this.mapDescription = mapDescription;
	}

	public byte[] getMapFile() {
		return mapFile;
	}

	public void setMapFile(byte[] mapFile) {
		this.mapFile = mapFile;
	}

	@Override
	public String toString() {
		return "Map [mapName=" + mapName + ", mapDescription=" + mapDescription + ", cityName=" + cityName
				+ ", mapFile=" + Arrays.toString(mapFile) + "]";
	}

	
}
