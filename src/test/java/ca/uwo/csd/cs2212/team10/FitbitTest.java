package ca.uwo.csd.cs2212.team10;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author Pearson and Patrick
 * This class will be used to get information from Fitbit services
 * as well as calling for new tokens and saving data
 * Consists of: Heart Statistics, Best and Lifetime Statistics, Daily Statistics and Goals
 */
public class FitbitTest
{	
	/*
	/**
	 * THIS MAIN CAN BE MOVED ELSEWHERE, JUST HERE FOR TESTING
	 * @throws JSONException 
	 * @throws TokensException 
	 *
	public static void main (String [] args) throws JSONException, TokensException
	{
		getHeartActivity("2016", "01", "29");
		getBestLifeActivity();
		getDailyActivity("2016", "01", "08");
	}
	*/
	
	/**
	 * Best Statistics Call
	 * @return
	 * @throws JSONException
	 * @throws TokensException
	 */
	public static BestLifeStats getBestLifeActivity() throws JSONException, TokensException
	{
		//API fake requests
		String jsonResult = "{'best':{'total':{'distance':{'date':'2016-02-10','value':10.35796},'floors':{'date':'2016-02-03','value':30.0000000456},'steps':{'date':'2016-01-14','value':13700}},'tracker':{'distance':{'date':'2016-02-10','value':10.35796},'floors':{'date':'2016-02-03','value':30.0000000456},'steps':{'date':'2016-01-14','value':13700}}},'lifetime':{'total':{'activeScore':-1,'caloriesOut':-1,'distance':202.93,'floors':559,'steps':272769},'tracker':{'activeScore':-1,'caloriesOut':-1,'distance':202.93,'floors':559,'steps':272769}}}";
				
		//get information via JSON string result
		JSONObject object = new JSONObject(jsonResult);
		JSONObject best = object.getJSONObject("best");
		JSONObject tracker = best.getJSONObject("tracker");
		
		//best distance
		JSONObject dist = tracker.getJSONObject("distance");
		double valueDist = dist.getDouble("value");
		String dateDist = dist.getString("date");
		
		//best floors
		JSONObject floors = tracker.getJSONObject("floors");
		double valueFloors = floors.getDouble("value");
		String dateFloors = floors.getString("date");
		
		//best steps
		JSONObject steps = tracker.getJSONObject("steps");
		Long valueSteps = steps.getLong("value");
		String dateSteps = steps.getString("date");
		
		//Lifetime statistics
		JSONObject lifetime = object.getJSONObject("lifetime");
		JSONObject total = lifetime.getJSONObject("total");
		double lifeDist = total.getDouble("distance");
		double lifeFloors = total.getInt("floors");
		long lifeSteps = total.getInt("steps");
		
		//Return a new BestLifeStats object
		return new BestLifeStats(valueDist, dateDist, valueFloors, dateFloors, valueSteps, dateSteps, lifeDist, lifeFloors, lifeSteps);
	}

	/**
	 * Heart Rate Statistics Call
	 * @return
	 * @throws JSONException
	 * @throws TokensException
	 * Gathers information for Heart Statistics
	 */
	public static HeartStats getHeartActivity(String year, String month, String day) throws JSONException, TokensException
	{
		//API fake request
		String jsonResult = "{'activities-heart':[{'dateTime':'2016-01-29','value':{'customHeartRateZones':[],'heartRateZones':[{'caloriesOut':511.55104,'max':94,'min':30,'minutes':319,'name':'Out of Range'},{'caloriesOut':449.3958,'max':131,'min':94,'minutes':138,'name':'Fat Burn'},{'caloriesOut':16.09776,'max':159,'min':131,'minutes':2,'name':'Cardio'},{'caloriesOut':0,'max':220,'min':159,'minutes':0,'name':'Peak'}],'restingHeartRate':70}}]}";
		
		//get information via JSON string result
		JSONObject object = new JSONObject(jsonResult);
		JSONArray activitiesHeart = object.getJSONArray("activities-heart");
		JSONObject heartZones = activitiesHeart.getJSONObject(0);
		JSONObject value = heartZones.getJSONObject("value");
		JSONArray heartRateZones = value.getJSONArray("heartRateZones");
		
		//assign the values for zones
		int outOfRange = heartRateZones.getJSONObject(0).getInt("minutes");
		int fatBurn = heartRateZones.getJSONObject(1).getInt("minutes");
		int cardio = heartRateZones.getJSONObject(2).getInt("minutes");
		int peak = heartRateZones.getJSONObject(3).getInt("minutes");
		
		//get the resting heart rate value
		int restHeartRate = value.getInt("restingHeartRate");
		
		//Return a new HeartStats object
		return new HeartStats(outOfRange, fatBurn, cardio, peak, restHeartRate);
	}
		
	/**
	 * Daily Statistics Call
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws JSONException
	 * @throws TokensException
	 */
	public static DailyStats getDailyActivity(String year, String month, String day) throws JSONException, TokensException
	{
		//API fake request
		String jsonResult = "{'activities':[],'goals':{'activeMinutes':30,'caloriesOut':2551,'distance':8.05,'floors':10,'steps':10000},'summary':{'activeScore':-1,'activityCalories':1183,'caloriesBMR':1609,'caloriesOut':2565,'distances':[{'activity':'total','distance':7.52},{'activity':'tracker','distance':7.52},{'activity':'loggedActivities','distance':0},{'activity':'veryActive','distance':3.38},{'activity':'moderatelyActive','distance':0.28},{'activity':'lightlyActive','distance':3.85},{'activity':'sedentaryActive','distance':0}],'elevation':82.3,'fairlyActiveMinutes':6,'floors':27,'lightlyActiveMinutes':218,'marginalCalories':669,'sedentaryMinutes':1175,'steps':10042,'veryActiveMinutes':41}}";
		
		//get information via JSON string result
		JSONObject object = new JSONObject(jsonResult);
		JSONObject summary = object.getJSONObject("summary"); 
		JSONArray distances = summary.getJSONArray("distances");
		
		//Get the Daily Values of the 8 daily activities
		double distance = distances.getJSONObject(1).getDouble("distance");
		int calories = summary.getInt("caloriesOut");
		int floors = summary.getInt("floors");
		int steps = summary.getInt ("steps");
		int lightActiveMins = summary.getInt ("lightlyActiveMinutes");
		int fairlyActiveMins = summary.getInt ("fairlyActiveMinutes");
		int sedentaryMins = summary.getInt ("sedentaryMinutes");
		int veryActiveMins = summary.getInt ("veryActiveMinutes");
		
		//get the Daily Goal Values
		JSONObject goals = object.getJSONObject("goals");
		int activeMinGoals = goals.getInt("activeMinutes"); 
		int caloriesOutGoals = goals.getInt("caloriesOut");
		double distanceGoals = goals.getDouble("distance");
		int floorGoals = goals.getInt("floors");
		int stepGoals = goals.getInt("steps");
		
		//Return new DailyStats object
		return new DailyStats (floors, steps, distance, calories, sedentaryMins, lightActiveMins, fairlyActiveMins, veryActiveMins, 
				activeMinGoals, caloriesOutGoals, distanceGoals, floorGoals, stepGoals);
	}
}
