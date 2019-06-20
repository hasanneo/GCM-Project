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
public class Place {
String Name;
String xLocation;
String yLocation;
String classification;
String accessability;


/**
 * @param name
 * @param xLocation
 * @param yLocation
 * @param classification
 * @param accessability
 */
public Place(String name, String xLocation, String yLocation, String classification, String accessability) {
	super();
	Name = name;
	this.xLocation = xLocation;
	this.yLocation = yLocation;
	this.classification = classification;
	this.accessability = accessability;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getxLocation() {
	return xLocation;
}
public void setxLocation(String xLocation) {
	this.xLocation = xLocation;
}
public String getyLocation() {
	return yLocation;
}
public void setyLocation(String yLocation) {
	this.yLocation = yLocation;
}
public String getClassification() {
	return classification;
}
public void setClassification(String classification) {
	this.classification = classification;
}
public String getAccessability() {
	return accessability;
}
public void setAccessability(String accessability) {
	this.accessability = accessability;
}

}
