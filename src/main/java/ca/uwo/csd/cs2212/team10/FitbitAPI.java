package ca.uwo.csd.cs2212.team10;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class will be used to get information from Fitbit services as well as calling for new tokens and saving data Consists
 * of: Heart Statistics, Best and Lifetime Statistics, Daily Statistics and Goals
 * @author Pearson and Patrick
 */
public class FitbitAPI implements Fitbit
{
	/*
	 * Main for testing purposes only public static void main (String [] args) throws JSONException, TokensException {
	 * getHeartActivity("2016", "01", "29"); getBestLifeActivity(); getDailyActivity("2016", "01", "08"); }
	 */

	// Variables for error handling
	private boolean error = false;
	private boolean tokensError = false;
	private boolean rateError = false;

	/**
	 * Best Statistics Call
	 * @return BestLifeStats object containing all best life statistics data
	 * @throws JSONException method calls a JSON file which can throw this error
	 * @throws TokensException method uses tokens which can throw this error
	 */
	public BestLifeStats getBestLifeActivity() throws JSONException, TokensException, RateExceededException
	{
		double valueDist;
		String dateDist;
		double valueFloors;
		String dateFloors;
		long valueSteps;
		String dateSteps;
		double lifeDist;
		double lifeFloors;
		long lifeSteps;

		try
		{
			// API requests
			String requestUrl = "https://api.fitbit.com/1/user/3WGW2P/activities.json";
			String jsonResult = RefreshTokens.getTokens(requestUrl);

			// Get information via JSON string result
			JSONObject object = new JSONObject(jsonResult);
			JSONObject best = object.getJSONObject("best");
			JSONObject tracker = best.getJSONObject("tracker");

			// Best distance
			JSONObject dist = tracker.getJSONObject("distance");
			valueDist = dist.getDouble("value");
			dateDist = dist.getString("date");

			// Best floors
			JSONObject floors = tracker.getJSONObject("floors");
			valueFloors = floors.getDouble("value");
			dateFloors = floors.getString("date");

			// Best steps
			JSONObject steps = tracker.getJSONObject("steps");
			valueSteps = steps.getLong("value");
			dateSteps = steps.getString("date");

			// Lifetime statistics
			JSONObject lifetime = object.getJSONObject("lifetime");
			JSONObject total = lifetime.getJSONObject("total");
			lifeDist = total.getDouble("distance");
			lifeFloors = total.getInt("floors");
			lifeSteps = total.getInt("steps");
			
			setError(false);
			setTokensError(false);
			setRateError(false);
		}
		catch (RateExceededException e)
		{
			valueDist = 0.00;
			dateDist = "Date not found";
			valueFloors = 0.00;
			dateFloors = "Date not found";
			valueSteps = 0;
			dateSteps = "Date not found";
			lifeDist = 0.00;
			lifeFloors = 0.00;
			lifeSteps = 0;

			setRateError(true);
			System.out.println("Rate Error: " + rateError);

		}
		catch (TokensException e)
		{
			valueDist = 0.00;
			dateDist = "Date not found";
			valueFloors = 0.00;
			dateFloors = "Date not found";
			valueSteps = 0;
			dateSteps = "Date not found";
			lifeDist = 0.00;
			lifeFloors = 0.00;
			lifeSteps = 0;

			setTokensError(true);
			System.out.println("Tokens Error: " + tokensError);

		}
		catch (Exception e)
		{
			valueDist = 0.00;
			dateDist = "Date not found";
			valueFloors = 0.00;
			dateFloors = "Date not found";
			valueSteps = 0;
			dateSteps = "Date not found";
			lifeDist = 0.00;
			lifeFloors = 0.00;
			lifeSteps = 0;

			setError(true);
			System.out.println("General Error: " + error);
		}

		// Return a new BestLifeStats object
		return new BestLifeStats(valueDist, dateDist, valueFloors, dateFloors, valueSteps, dateSteps, lifeDist,
				lifeFloors, lifeSteps);
	}

	/**
	 * Heart Rate Statistics Call Gathers information for Heart Statistics from a specified date
	 * @param year String that contains the year of request
	 * @param month String that contains the month of request
	 * @param day String that contains the day of request
	 * @return HeartStats object containing all the heart rate data
	 * @throws JSONException Method requests a JSON file that can throw this error
	 * @throws TokensException Method uses tokens to interface with API which can throw this error
	 */
	public HeartStats getHeartActivity(String year, String month, String day) throws JSONException, TokensException, RateExceededException
	{
		int outOfRange;
		int fatBurn;
		int cardio;
		int peak;
		int restHeartRate;

		try
		{
			// API Request
			String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/activities/heart/date/";
			String requestUrl = requestUrlPrefix + year + "-" + month + "-" + day + "/" + "1d" + ".json";
			System.out.println(requestUrl);
			String jsonResult = RefreshTokens.getTokens(requestUrl);

			// Get information via JSON string result
			JSONObject object = new JSONObject(jsonResult);
			JSONArray activitiesHeart = object.getJSONArray("activities-heart");
			JSONObject heartZones = activitiesHeart.getJSONObject(0);
			JSONObject value = heartZones.getJSONObject("value");
			JSONArray heartRateZones = value.getJSONArray("heartRateZones");

			// Assign the values for zones
			outOfRange = heartRateZones.getJSONObject(0).getInt("minutes");
			fatBurn = heartRateZones.getJSONObject(1).getInt("minutes");
			cardio = heartRateZones.getJSONObject(2).getInt("minutes");
			peak = heartRateZones.getJSONObject(3).getInt("minutes");

			// Get the resting heart rate value
			restHeartRate = value.getInt("restingHeartRate");
			
			setError(false);
			setTokensError(false);
			setRateError(false);
		}
		catch (RateExceededException e)
		{
			outOfRange = 0;
			fatBurn = 0;
			cardio = 0;
			peak = 0;
			restHeartRate = 0;

			setRateError(true);
			System.out.println("Rate Error: " + rateError);
		}
		catch (TokensException e)
		{
			outOfRange = 0;
			fatBurn = 0;
			cardio = 0;
			peak = 0;
			restHeartRate = 0;

			setTokensError(true);
			System.out.println("Tokens Error: " + tokensError);
		}
		catch (Exception e)
		{
			outOfRange = 0;
			fatBurn = 0;
			cardio = 0;
			peak = 0;
			restHeartRate = 0;

			setError(true);
			System.out.println("General Error: " + error);
		}

		// Return new HeartStats object
		return new HeartStats(outOfRange, fatBurn, cardio, peak, restHeartRate);
	}

	/**
	 * Daily Statistics Call
	 * @param year String that contains the year of request
	 * @param month String that contains the month of request
	 * @param day String that contains the day of request
	 * @return DailyStats object containing all the daily statistics data
	 * @throws JSONException Method requests a JSON file that can throw this error
	 * @throws TokensException Method uses tokens to interface with API which can throw this error
	 */
	public DailyStats getDailyActivity(String year, String month, String day) throws JSONException, TokensException, RateExceededException
	{
		double distance;
		int calories;
		int floors;
		int steps;
		int lightActiveMins;
		int fairlyActiveMins;
		int sedentaryMins;
		int veryActiveMins;
		int activeMinGoals;
		int caloriesOutGoals;
		double distanceGoals;
		int floorGoals;
		int stepGoals;

		try
		{
			// API Request
			String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/activities/date/";
			String requestUrl = requestUrlPrefix + year + "-" + month + "-" + day + ".json";
			String jsonResult = RefreshTokens.getTokens(requestUrl);

			// Get information via JSON string result
			JSONObject object = new JSONObject(jsonResult);
			JSONObject summary = object.getJSONObject("summary");
			JSONArray distances = summary.getJSONArray("distances");

			// Get the Daily Values of the 8 daily activities
			distance = distances.getJSONObject(1).getDouble("distance");
			calories = summary.getInt("caloriesOut");
			floors = summary.getInt("floors");
			steps = summary.getInt("steps");
			lightActiveMins = summary.getInt("lightlyActiveMinutes");
			fairlyActiveMins = summary.getInt("fairlyActiveMinutes");
			sedentaryMins = summary.getInt("sedentaryMinutes");
			veryActiveMins = summary.getInt("veryActiveMinutes");

			// Get the Daily Goal Values
			JSONObject goals = object.getJSONObject("goals");
			activeMinGoals = goals.getInt("activeMinutes");
			caloriesOutGoals = goals.getInt("caloriesOut");
			distanceGoals = goals.getDouble("distance");
			floorGoals = goals.getInt("floors");
			stepGoals = goals.getInt("steps");
			
			setError(false);
			setTokensError(false);
			setRateError(false);
		}
		catch (RateExceededException e)
		{
			distance = 0.00;
			calories = 0;
			floors = 0;
			steps = 0;
			lightActiveMins = 0;
			fairlyActiveMins = 0;
			sedentaryMins = 0;
			veryActiveMins = 0;
			activeMinGoals = 0;
			caloriesOutGoals = 0;
			distanceGoals = 0.00;
			floorGoals = 0;
			stepGoals = 0;

			setRateError(true);
			System.out.println("Rate Error: " + rateError);
		}
		catch (TokensException e)
		{
			distance = 0.00;
			calories = 0;
			floors = 0;
			steps = 0;
			lightActiveMins = 0;
			fairlyActiveMins = 0;
			sedentaryMins = 0;
			veryActiveMins = 0;
			activeMinGoals = 0;
			caloriesOutGoals = 0;
			distanceGoals = 0.00;
			floorGoals = 0;
			stepGoals = 0;

			setTokensError(true);
			System.out.println("Tokens Error: " + tokensError);
		}
		catch (Exception e)
		{
			distance = 0.00;
			calories = 0;
			floors = 0;
			steps = 0;
			lightActiveMins = 0;
			fairlyActiveMins = 0;
			sedentaryMins = 0;
			veryActiveMins = 0;
			activeMinGoals = 0;
			caloriesOutGoals = 0;
			distanceGoals = 0.00;
			floorGoals = 0;
			stepGoals = 0;

			setError(true);
			System.out.println("General Error: " + error);
		}

		// Return new DailyStats object
		return new DailyStats(floors, steps, distance, calories, sedentaryMins, lightActiveMins, fairlyActiveMins,
				veryActiveMins, activeMinGoals, caloriesOutGoals, distanceGoals, floorGoals, stepGoals);
	}

	// Getters and Setters for Error Variable
	/**
	 * Getter Method for general Error 
	 */
	public boolean getError()
	{
		// TODO Auto-generated method stub
		return error;
	}

	/**
	 * Setter Method for general Error 
	 */
	public boolean setError(boolean error)
	{
		// TODO Auto-generated method stub
		this.error = error;
		return error;
	}

	/**
	 * Getter Method for Tokens Error 
	 */
	public boolean getTokensError()
	{
		// TODO Auto-generated method stub
		return tokensError;
	}

	/**
	 * Setter Method for Tokens Error 
	 */
	public boolean setTokensError(boolean tokensError)
	{
		// TODO Auto-generated method stub
		this.tokensError = tokensError;
		return tokensError;
	}

	/**
	 * Getter Method for Rate Error 
	 */
	public boolean getRateError()
	{
		// TODO Auto-generated method stub
		return rateError;
	}

	/**
	 * Setter Methods for Rate Error 
	 */
	public boolean setRateError(boolean rateError)
	{
		// TODO Auto-generated method stub
		this.rateError = rateError;
		return rateError;
	}
}
