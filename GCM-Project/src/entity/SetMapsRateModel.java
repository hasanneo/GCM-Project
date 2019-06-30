package entity;

public class SetMapsRateModel {

	String map_info,map_name,map_version,authorized;

	public SetMapsRateModel(String map_info, String map_name, String map_version, String authorized) {
		super();
		this.map_info = map_info;
		this.map_name = map_name;
		this.map_version = map_version;
		this.authorized = authorized;
	}

	public String getMap_info() {
		return map_info;
	}

	public void setMap_info(String map_info) {
		this.map_info = map_info;
	}

	public String getMap_name() {
		return map_name;
	}

	public void setMap_name(String map_name) {
		this.map_name = map_name;
	}

	public String getMap_version() {
		return map_version;
	}

	public void setMap_version(String map_version) {
		this.map_version = map_version;
	}

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}
	
	
}
