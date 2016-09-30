package ca.uwo.csd.cs2212.team10;

/**
 * This class will be used to handle token exceptions
 * @author Pearson
 */
public class TokensException extends Exception
{
	/**
	 * Exception Constructor
	 * @param message String that contains the caught error message
	 */
	public TokensException(String message)
	{
		super(message);
	}
}
