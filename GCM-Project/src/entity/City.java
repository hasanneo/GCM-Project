package entity;

import java.util.ArrayList;

public class City {
	private ArrayList<Map> maps;
	private ArrayList<Tour> tours;
	private String cityName;

	private int numberOfMaps;
	private int numberOfPOI;
	private int numberOfTours;
	private int numberOfVersions;
	
	private String cityDescription;
	
	public String getCityDescription() {
		return cityDescription;
	}

	public void setCityDescription(String cityDescription) {
		this.cityDescription = cityDescription;
	}

	public City(String cityName)
	{
		this.cityName=cityName;
	}
	
	public City(String cityName,ArrayList<Map> maps,ArrayList<Tour> tours)
	{
		this.cityName=cityName;
		this.maps=maps;
		this.tours=tours;
		numberOfTours=tours.size();
		numberOfMaps=maps.size();
	}
	
	
	public ArrayList<Map> getMaps() {
		return maps;
	}
	public void setMaps(ArrayList<Map> maps) {
		this.maps = maps;
	}
	public ArrayList<Tour> getTours() {
		return tours;
	}
	public void setTours(ArrayList<Tour> tours) {
		this.tours = tours;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getNumberOfMaps() {
		return numberOfMaps;
	}
	public void setNumberOfMaps(int numberOfMaps) {
		this.numberOfMaps = numberOfMaps;
	}
	public int getNumberOfPOI() {
		return numberOfPOI;
	}
	public void setNumberOfPOI(int numberOfPOI) {
		this.numberOfPOI = numberOfPOI;
	}
	public int getNumberOfTours() {
		return numberOfTours;
	}
	public void setNumberOfTours(int numberOfTours) {
		this.numberOfTours = numberOfTours;
	}
	public int getNumberOfVersions() {
		return numberOfVersions;
	}
	public void setNumberOfVersions(int numberOfVersions) {
		this.numberOfVersions = numberOfVersions;
	}
}
