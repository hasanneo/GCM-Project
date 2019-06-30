package entity;
//R
import java.util.*;

import controller.DataBaseController;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 * majd
 * 
 * */
public class Report {

	/*
	 * parameters.
	 * */
	private  String CityName;
	private  Integer ReportTableMapsNumber;
	private  Integer ReportTablSubscriptions;
	private  Integer ReportTablSubscriptionRenew;
    private  Integer ReportTablViews;
	private  Integer ReportTablDownloads;
	private  Integer ReportTablOneTimePurchase;
	static int i=1;
	
	public Report(String cityName, int reportTableMapsNumber, int reportTablSubscriptions,
			int reportTablSubscriptionRenew, int reportTablViews, int reportTablDownloads,
			int reportTablOneTimePurchase)
	{
		CityName = cityName;
		ReportTableMapsNumber = reportTableMapsNumber;
		ReportTablSubscriptions =reportTablSubscriptions;
		ReportTablSubscriptionRenew = reportTablSubscriptionRenew;
		ReportTablViews = reportTablViews;
		ReportTablDownloads =reportTablDownloads;
		ReportTablOneTimePurchase =reportTablOneTimePurchase;
	}
	/*getters & setters*/
	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public int getReportTableMapsNumber() {
		return ReportTableMapsNumber;
	}

	public void setReportTableMapsNumber(int reportTableMapsNumber) {
		ReportTableMapsNumber = reportTableMapsNumber;
	}

	public int getReportTablSubscriptions() {
		return ReportTablSubscriptions;
	}

	public void setReportTablSubscriptions(int reportTablSubscriptions) {
		ReportTablSubscriptions = reportTablSubscriptions;
	}

	public int getReportTablSubscriptionRenew() {
		return ReportTablSubscriptionRenew;
	}

	public void setReportTablSubscriptionRenew(int reportTablSubscriptionRenew) {
		ReportTablSubscriptionRenew = reportTablSubscriptionRenew;
	}

	public int getReportTablViews() {
		return ReportTablViews;
	}

	public void setReportTablViews(int reportTablViews) {
		ReportTablViews =reportTablViews;
	}

	public int getReportTablDownloads() {
		return ReportTablDownloads;
	}

	public void setReportTablDownloads(int reportTablDownloads) {
		ReportTablDownloads = reportTablDownloads;
	}

	public int getReportTablOneTimePurchase() {
		return ReportTablOneTimePurchase;
	}

	public void setReportTablOneTimePurchase(int reportTablOneTimePurchase) {
		ReportTablOneTimePurchase =reportTablOneTimePurchase;
	}

	/**
	 * @author majdh
	 * this function accepts city name and updated the Maps Number.
	 * */			    
    public static void AddReportTablMapsNumber(String cityName) {
    	ArrayList<String> array=null;
    	DataBaseController.KnowDate();
		DataBaseController.SelectAllRowsFromTable("datet");
		ArrayList<String> arrDate=DataBaseController.clientCon.getList();
		String date=arrDate.get(1);
		DataBaseController.DeleteRow("datet","ReportDate",date);
		DataBaseController.SelectAllRowsFromTable("viewreportstable","CITY_NAME",cityName);
		array=DataBaseController.clientCon.getList();
    	if(array==null ||!array.contains(cityName)||!array.contains(date)) {
        	DataBaseController.InsertReportsToDB(cityName,1,0,0,0,0,0); 
    	}else {
    		if(array.contains(date) && array.contains(cityName)) 
    			DataBaseController.CityIncFieldsInDB("MapsNum",cityName,date);
    	}
	}
    /**
	 * @author majdh
	 * this function accepts city name and updated the Subscriptions Number.
	 * */
	public static void AddReportTablSubscriptions(String cityName) {
		ArrayList<String> array=null;
    	DataBaseController.KnowDate();
		DataBaseController.SelectAllRowsFromTable("datet");
		ArrayList<String> arrDate=DataBaseController.clientCon.getList();
		String date=arrDate.get(1);
		DataBaseController.DeleteRow("datet","ReportDate",date);
		DataBaseController.SelectAllRowsFromTable("viewreportstable","CITY_NAME",cityName);
		array=DataBaseController.clientCon.getList();
		if(array==null ||!array.contains(cityName)||!array.contains(date)) {
	
    	DataBaseController.InsertReportsToDB(cityName,0,1,0,0,0,0);
		}else {
			if(array.contains(date)&& array.contains(cityName))
				DataBaseController.CityIncFieldsInDB("SubscriptionsNum",cityName,date);
    	}
		
	}
	/**
	 * @author majdh
	 * this function accepts city name and updated the Subscription Renew Number.
	 * */
	public static void AddReportTablSubscriptionRenew(String cityName) {
		ArrayList<String> array=null;
    	DataBaseController.KnowDate();
		DataBaseController.SelectAllRowsFromTable("datet");
		ArrayList<String> arrDate=DataBaseController.clientCon.getList();
		String date=arrDate.get(1);
		DataBaseController.DeleteRow("datet","ReportDate",date);
		DataBaseController.SelectAllRowsFromTable("viewreportstable","CITY_NAME",cityName);
		array=DataBaseController.clientCon.getList();
		if(array==null ||!array.contains(cityName)||!array.contains(date))  {
    	DataBaseController.InsertReportsToDB(cityName,0,0,1,0,0,0);
		}else {
			if(array.contains(date)&& array.contains(cityName))
    			DataBaseController.CityIncFieldsInDB("SubscriptionRenewNum",cityName,date);
    	}
		
	}
	/**
	 * @author majdh
	 * this function accepts city name and updated the Views Number.
	 * */
	public static void AddReportTablViews(String cityName) {
		ArrayList<String> array=null;
    	DataBaseController.KnowDate();
		DataBaseController.SelectAllRowsFromTable("datet");
		ArrayList<String> arrDate=DataBaseController.clientCon.getList();
		String date=arrDate.get(1);
		DataBaseController.DeleteRow("datet","ReportDate",date);
		DataBaseController.SelectAllRowsFromTable("viewreportstable","CITY_NAME",cityName);
		array=DataBaseController.clientCon.getList();
		if(array==null ||!array.contains(cityName)||!array.contains(date)) {
    	DataBaseController.InsertReportsToDB(cityName,0,0,0,1,0,0);
		}else {
			if(array.contains(date) && array.contains(cityName))
				DataBaseController.CityIncFieldsInDB("ViewsNum",cityName,date);  
		}
		
	}
	/**
	 * @author majdh
	 * this function accepts city name and updated the Downloads Number.
	 * */
	public static void AddReportTablTablDownloads(String cityName){
		ArrayList<String> array=null;
    	DataBaseController.KnowDate();
		DataBaseController.SelectAllRowsFromTable("datet");
		ArrayList<String> arrDate=DataBaseController.clientCon.getList();
		String date=arrDate.get(1);
		DataBaseController.DeleteRow("datet","ReportDate",date);
		DataBaseController.SelectAllRowsFromTable("viewreportstable","CITY_NAME",cityName);
		array=DataBaseController.clientCon.getList();
		if(array==null ||!array.contains(cityName)||!array.contains(date)) {		
    	DataBaseController.InsertReportsToDB(cityName,0,0,0,0,1,0);
		}else {
			if(array.contains(date)&& array.contains(cityName))
	    		DataBaseController.CityIncFieldsInDB("DownloadsNum",cityName,date);
		} 
		
	}
	/**
	 * @author majdh
	 * this function accepts city name and updated the One Time Purchase Number.
	 * */
	public static void AddReportTablOneTimePurchase(String cityName){
		ArrayList<String> array=null;
    	DataBaseController.KnowDate();
		DataBaseController.SelectAllRowsFromTable("datet");
		ArrayList<String> arrDate=DataBaseController.clientCon.getList();
		String date=arrDate.get(1);
		DataBaseController.DeleteRow("datet","ReportDate",date);
		DataBaseController.SelectAllRowsFromTable("viewreportstable","CITY_NAME",cityName);
		array=DataBaseController.clientCon.getList();
		if(array==null ||!array.contains(cityName)||!array.contains(date)) {		
    	DataBaseController.InsertReportsToDB(cityName,0,0,0,0,0,1);
		}else {
			if(array.contains(date)&& array.contains(cityName))
				DataBaseController.CityIncFieldsInDB("OneTimePurchase",cityName,date);
		}
		
	}
}
