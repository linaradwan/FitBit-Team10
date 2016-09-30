package ca.uwo.csd.cs2212.team10;

/**
 * This class will be used to modify and store daily statistics
 * @author Pearson and Patrick
 */
public class DailyStats
{
	// Daily Statistics Variables
	private int floors;
	private int steps;
	private double distance;
	private int calories;
	private int sedentaryMins;
	private int lightActiveMins;
	private int fairlyActiveMins;
	private int veryActiveMins;

	// Goal Statistics Variables
	private int activeMinGoals;
	private int caloriesOutGoals;
	private double distanceGoals;
	private int floorGoals;
	private int stepGoals;

	/**
	 * Daily and Goal Constructor
	 * @param floors integer that contains daily floor count
	 * @param steps integer that contains daily step count
	 * @param distance double float that contains daily distance
	 * @param calories integer that contains daily calorie count
	 * @param sedentaryMins integer that contains daily sedentary minutes
	 * @param lightActiveMins integer that contains daily light active minutes
	 * @param fairlyActiveMins integer that contains daily fairly active minutes
	 * @param veryActiveMins integer that contains daily very active minutes
	 * @param activeMinGoals integer that contains daily active minute goal
	 * @param caloriesOutGoals integer that contains daily calorie usage goal
	 * @param distanceGoals double float that contains daily distance goal
	 * @param floorGoals integer that contains daily floor count goal
	 * @param stepGoals integer that conntains daily step count goal
	 */
	public DailyStats(int floors, int steps, double distance, int calories, int sedentaryMins, int lightActiveMins,
			int fairlyActiveMins, int veryActiveMins, int activeMinGoals, int caloriesOutGoals, double distanceGoals,
			int floorGoals, int stepGoals)
	{
		this.floors = floors;
		this.steps = steps;
		this.distance = distance;
		this.calories = calories;
		this.sedentaryMins = sedentaryMins;
		this.lightActiveMins = lightActiveMins;
		this.fairlyActiveMins = fairlyActiveMins;
		this.veryActiveMins = veryActiveMins;
		this.activeMinGoals = activeMinGoals;
		this.caloriesOutGoals = caloriesOutGoals;
		this.distanceGoals = distanceGoals;
		this.floorGoals = floorGoals;
		this.stepGoals = stepGoals;

		System.out.println(toString());
	}

	@Override
	/**
	 * Method takes all the variables declared by the constructor and returns a string containing all the data in JSON format
	 * @return String that contains all data in JSON format
	 */
	public String toString()
	{
		return "DailyStats [floors = " + floors + ", steps = " + steps + ", distance = " + distance + ", calories = "
				+ calories + ", sedentaryMins = " + sedentaryMins + ", lightActiveMins = " + lightActiveMins
				+ ", fairlyActiveMins = " + fairlyActiveMins + ", veryActiveMins = " + veryActiveMins + "]"
				+ "\nGoalStats [activeMinGoals = " + activeMinGoals + ", caloriesOutGoals = " + caloriesOutGoals
				+ ", distanceGoals = " + distanceGoals + ", floorGoals = " + floorGoals + ", stepGoals = " + stepGoals
				+ "]";
	}

	// Daily Statistics Getters
	/**
	 * Method returns daily floor count
	 * @return int floor
	 */
	public int getFloors()
	{
		return floors;
	}

	/**
	 * Method returns daily step count
	 * @return int steps
	 */
	public int getSteps()
	{
		return steps;
	}

	/**
	 * Method returns daily distance
	 * @return double distance
	 */
	public double getDistance()
	{
		return distance;
	}

	/**
	 * Method returns daily calorie count
	 * @return int calories
	 */
	public int getCalories()
	{
		return calories;
	}

	/**
	 * Method returns daily sedentary minutes
	 * @return int sedentaryMins
	 */
	public int getSedentaryMins()
	{
		return sedentaryMins;
	}

	/**
	 * Method returns daily light active minutes
	 * @return int lightActiveMins
	 */
	public int getLightActiveMins()
	{
		return lightActiveMins;
	}

	/**
	 * Method returns daily fairly active minutes
	 * @return int farilyActiveMins
	 */
	public int getFairlyActiveMins()
	{
		return fairlyActiveMins;
	}

	/**
	 * Method returns daily very active minutes
	 * @return int veryActiveMins
	 */
	public int getVeryActiveMins()
	{
		return veryActiveMins;
	}

	// Goal Statistics Getters
	/**
	 * Method returns daily active minute goal
	 * @return int activeMinGoals
	 */
	public int getActiveMinGoals()
	{
		return activeMinGoals;
	}

	/**
	 * Method returns daily calorie usage goal
	 * @return int caloriesOutGoals
	 */
	public int getCaloriesOutGoals()
	{
		return caloriesOutGoals;
	}

	/**
	 * Method returns daily distance goal
	 * @return double distanceGoals
	 */
	public double getDistanceGoals()
	{
		return distanceGoals;
	}

	/**
	 * Method returns daily floor count goal
	 * @return int floorGoals
	 */
	public int getFloorGoals()
	{
		return floorGoals;
	}

	/**
	 * Method returns daily step count goal
	 * @return int stepGoals
	 */
	public int getStepGoals()
	{
		return stepGoals;
	}
}
