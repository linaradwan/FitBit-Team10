package ca.uwo.csd.cs2212.team10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * This class will be used to load and save user settings
 * @author Pearson
 */
public class ObjectSerialization implements Serializable
{
	private UserSettings userSettings;

	/**
	 * Constructor to save hold the user settings Will later save these settings
	 * @param newUserSettings UserSettings object
	 */
	public ObjectSerialization(UserSettings newUserSettings)
	{
		userSettings = newUserSettings;
	}

	/**
	 * This method will save the user settings
     * @param newUserSettings UserSettings object
	 * @throws Exception caused by IO operations
	 */
	public void storeUserSettings(UserSettings newUserSettings) throws Exception
	{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/fitbit.settings"));
		userSettings = newUserSettings;
		out.writeObject(userSettings);
		System.out.println(userSettings.toString());
		System.out.println(out.toString() + " - testing");
		out.close();
	}

	/**
	 * This method will load user settings
	 * @return UserSettings object
	 * @throws Exception thrown by IO operations
	 */
	public UserSettings loadUserSettings() throws Exception
	{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/fitbit.settings"));
		UserSettings userSettings = (UserSettings) in.readObject();
		System.out.println(userSettings.toString());
		System.out.println(in.toString() + " - testing"); //test
		in.close();

		return userSettings;
	}
}
