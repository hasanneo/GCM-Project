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

	ArrayList<String> fields;
	private  String CityName;
	private  Integer ReportTableMapsNumber;
	private  Integer ReportTablSubscriptions;
	private  Integer ReportTablSubscriptionRenew;
    private  Integer ReportTablViews;
	private  Integer ReportTablDownloads;
	private  Integer ReportTablOneTimePurchase;
	
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

    public void AddMapsNumber(String cityName) {
		
	}
	public void AddSubscriptions(String cityName) {
		
	}
	public void AddSubscriptionRenew(String cityName) {
	
	}
	public void AddReportTablViews(String cityName) {
		
	}
	public void AddTablDownloads(String cityName){
	
	}
	public void AddReportTablOneTimePurchase(String cityName){
	
	}
	
}
