package ca.uwo.csd.cs2212.team10;

import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;

/**
 * This class will be used to hold the information that will be serialized
 * @author Pearson
 *
 */
public class UserSettings implements Serializable
{
	private String units = "metric";
	private Point[] pointArray;

	/**
	 * This constructor will hold all the settings to be saved
	 */
	public UserSettings()
	{
		this.units = units;
		this.pointArray = pointArray;
	}

	@Override
    /**
     * Method ouputs settings to string
     * @return String user settings
     */
	public String toString()
	{
		return "UserSettings [units=" + units + ", pointArray=" + Arrays.toString(pointArray) + "]";
	}

	/**
	 * Getter for the units setting
	 * @return String units
	 */
	public String getUnits()
	{
		return units;
	}

	/**
	 * Setter for the units setting
	 * @param units String
	 */
	public void setUnits(String units)
	{
		this.units = units;
	}

	/**
	 * Getter for the point array locations
	 * @return Point[] array object
	 */
	public Point[] getPointArray()
	{
		return pointArray;
	}

	/**
	 * Setter for the point array locations
	 * @param pointArray Point[] array
	 */
	public void setPointArray(Point[] pointArray)
	{
		this.pointArray = pointArray;
	}
}
