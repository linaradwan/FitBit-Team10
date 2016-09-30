package ca.uwo.csd.cs2212.team10;

/**
 * This class will be used to modify and store best statistics 
 * @author Pearson and Patrick
 */
public class BestLifeStats 
{
	//Best Statistics Variables
	private double bestDistance;
	private String bestDistanceDate;
	private double bestFloor;
	private String bestFloorDate;
	private long bestStep;
	private String bestStepDate;
	
	//Lifetime Statistics Variables
	private double lifeDistance;
	private double lifeFloors;
	private long lifeSteps;
	
	
	/**
	 * Best and Lifetime Constructor
	 * @param bestDistance double float that contains best distance in km
	 * @param bestDistanceDate String that contains the date that the best distance was achieved
	 * @param bestFloor double float that contains best floor count
	 * @param bestFloorDate String that contains the date that the best floor count was achieved
	 * @param bestStep long integer that contains the best step count
	 * @param bestStepDate String that contains the date that the best step count was achieved 
	 * @param lifeDistance double float that contains the total distance count 
	 * @param lifeFloors double float that contains the total floor count
	 * @param lifeSteps long integer that contains the total step count
	 */
	public BestLifeStats(double bestDistance, String bestDistanceDate, double bestFloor, String bestFloorDate,
			long bestStep, String bestStepDate, double lifeDistance, double lifeFloors, long lifeSteps) 
	{
		this.bestDistance = bestDistance;
		this.bestDistanceDate = bestDistanceDate;
		this.bestFloor = bestFloor;
		this.bestFloorDate = bestFloorDate;
		this.bestStep = bestStep;
		this.bestStepDate = bestStepDate;
		this.lifeDistance = lifeDistance;
		this.lifeFloors = lifeFloors;
		this.lifeSteps = lifeSteps;
		
		System.out.println(toString());
	}
	
	@Override
    /**
     * Takes all the variables set by the constructor and formats it into a string in a JSON format
     * @return String of all the data in JSON format
     */
	public String toString() 
	{
		return "BestStats [bestDistance = " + bestDistance + ", bestDistanceDate = " + bestDistanceDate + ", bestFloor = "
				+ bestFloor + ", bestFloorDate = " + bestFloorDate + ", bestStep = " + bestStep + ", bestStepDate = "
				+ bestStepDate + "]" + "\nLifeStats [lifeDistance = " + lifeDistance + ", lifeFloors = " + lifeFloors + ", lifeSteps = "
				+ lifeSteps + "]";
	}

	//Best Statistics Getters
    /**
     * Method returns best distance
     * @return double bestDistance
     */ 
	public double getBestDistance() {
		return bestDistance;
	}
    /**
     * Method returns best distance date
     * @return String bestDistanceDate
     */
	public String getBestDistanceDate() {
		return bestDistanceDate;
	}
    /**
     * Method returns best floor count
     * @return double bestFloor
     */
	public double getBestFloor() {
		return bestFloor;
	}
    /**
     * Method returns best floor date
     * @return String bestFloorDate
     */
	public String getBestFloorDate() {
		return bestFloorDate;
	}
    /**
     * Method returns best step count
     * @return long bestStep
     */
	public long getBestStep() {
		return bestStep;
	}
    /**
     * Method returns best step date
     * @return String bestStepDate
     */
	public String getBestStepDate() {
		return bestStepDate;
	}
	
	//Lifetime Statistics Getters
    /**
     * Method returns lifetime distance
     * @return double lifeDistance
     */
	public double getLifeDistance() {
		return lifeDistance;
	}
    /**
     * Method returns lifetime floor count
     * @return double lifeFloors
     */
	public double getLifeFloors() {
		return lifeFloors;
	}
    /**
     * Method returns lifetime step count
     * @return long lifeSteps
     */
	public long getLifeSteps() {
		return lifeSteps;
	}
}
