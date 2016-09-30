package ca.uwo.csd.cs2212.team10;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class will be used to obtain the current date to enable refreshing FitBit Data
 * @author Neeraja Murali Dharan 
 */
public class Date {
	//Fields to hold values for day, month and year
	private String day;
	private String month; 
	private String year;
	private String [] date; //Holds the parsed values 
	
	/**
	 * Date constructor 
	 */
	public Date(){
		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date (year/month/day)
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		// Get the date today using Calendar object.
		java.util.Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format stored in a String.
		String reportDate = df.format(today);

		//Parse through the string to split into day, month and year
		this.date=reportDate.split("/");
		this.year=date[0];
		this.month=date[1];
		this.day=date[2];
	}
	/**
     * Method returns the current day as a String
     * @return String Day
     */
	public String getDay(){
		return this.day;
	}
	/**
     * Method returns the current month as a String
     * @return String Month
     */
	public String getMonth(){
		return this.month; 
	}
	/**
     * Method returns the current year as a String
     * @return String Year
     */
	public String getYear(){
		return this.year;
	}
}


