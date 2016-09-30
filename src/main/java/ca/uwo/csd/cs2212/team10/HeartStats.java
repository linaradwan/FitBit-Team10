package ca.uwo.csd.cs2212.team10;

/**
 * This class will be used to modify and store heart rate statistics
 * @author Pearson and Patrick
 */
public class HeartStats 
{
	//Heart Rate Variables
	private int outOfRange;
	private int fatBurn;
	private int cardio;
	private int peak;
	private int restHeartRate;
	
	/**
	 * Heart Rate Constructor
	 * @param outOfRange integer that contains out of range heart rate
	 * @param fatBurn integer that contains fat burn heart rate
	 * @param cardio integer that contains cardio heart rate
	 * @param peak integer that contains peak heart rate
	 * @param restHeartRate integer that contains resting heart rate 
	 */
	public HeartStats(int outOfRange, int fatBurn, int cardio, int peak, int restHeartRate) 
	{
		this.outOfRange = outOfRange;
		this.fatBurn = fatBurn;
		this.cardio = cardio;
		this.peak = peak;
		this.restHeartRate = restHeartRate;
		
		System.out.println(toString());
	}

	@Override
    /**
     * Method takes all the data declared in the constructor and returns a string containing all the data in a JSON format
     * @return String of all data in JSON format
     */
	public String toString() 
	{
		return "HeartStats [outOfRange = " + outOfRange + ", fatBurn = " + fatBurn + ", cardio = " 
				+ cardio + ", peak = " + peak + ", restHeartRate = " + restHeartRate + "]";
	}
	
	//Heart rate statistics getters
    /**
     * Method returns out of range heart rate
     * @return int outofRange
     */
	public int getOutOfRange() {
		return outOfRange;
	}
    /**
     * Method returns fat burn heart rate
     * @return int fatBurn
     */
	public int getFatBurn() {
		return fatBurn;
	}
    /**
     * Method returns cardio heart rate
     * @return int cardio
     */
	public int getCardio() {
		return cardio;
	}
    /**
     * Method returns peak heart rate
     * @return int peak
     */
	public int getPeak() {
		return peak;
	}
    /**
     * Method returns testing heart rate
     * @return int restHeartRate
     */
	public int getRestHeartRate() {
		return restHeartRate;
	}
}
