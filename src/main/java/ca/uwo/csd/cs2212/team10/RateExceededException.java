package ca.uwo.csd.cs2212.team10;

/**
 * This class will be used to handle exceptions when the rate limit is exceeded
 * @author Pearson
 *
 */
public class RateExceededException extends Exception
{
	/**
	 * Exception constructor
	 * @param message String that contains error message
	 */
	public RateExceededException(String message)
	{
		super(message);
	}
}
