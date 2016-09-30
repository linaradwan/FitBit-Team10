package ca.uwo.csd.cs2212.team10;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.event.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;


/**
 * This class represents the information for the Dashboard element 'Active Minutes'
 * This will house all the data (as parameters in the constructor) and the graphical elements.
 * As of Stage 2, only data is being supplied
 * 
 * @author Gustavo Murcia
 */
public class ActiveMinutesFrame extends JPanel {

	Color bg = new Color(155,155,155);
	String[] userDate = {"yyyy","mm","dd"};

	/**
	 * The constructor for Active Minutes Frame. 
	 * The parameters represent information coming from the API
	 * 
	 * @param lightActiveMins integer that contains light active minutes
	 * @param fairlyActiveMins integer that contains fairly active minutes
	 * @param veryActiveMins integer that contains very active minutes
	 * @param activeMinGoals integer that contains active minute goals
     * @param floors integer value of floors
     * @param steps integer value of steps
     * @param distance double value that contains distance
     * @param floorGoals integer value that contains floor goal
     * @param stepGoals integer value that contains step goal
     * @param distanceGoals double value that contains distance goal 
	 */
	public ActiveMinutesFrame(int lightActiveMins, int fairlyActiveMins, int veryActiveMins, int activeMinGoals, int floors, int steps, double distance,int floorGoals,int stepGoals,double distanceGoals) {

		// Set the layout of this Frame to a BoxLayout. This allows all additional elements to be added horizontally
		//BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
		//this.setLayout(bl);

		this.setLayout(null);

		//tmp
		this.setBackground(this.bg); 


		// A top menu bar for the user input
		/*JMenuBar desktopMenuBar = new JMenuBar();
		desktopMenuBar.setBackground(new Color(100, 255, 100));
		desktopMenuBar.setBorderPainted(false);
		desktopMenuBar.setBounds(0, 0, 500, 20);
		//Create the UserInput Text Box
		JFormattedTextField userInput = new JFormattedTextField(
				createFormatter("####/##/##"));
		userInput.setBounds(0, 0, 150, 20);
		
		// Create a refresh button
		JButton refreshbutn = new JButton("Input Desired Date"); 
		//refreshbutn.setIcon();
		refreshbutn.setBackground(new Color(250, 150, 150));
		refreshbutn.setBorderPainted(false);
		refreshbutn.setBounds(400, 0, 100, 20);
		refreshbutn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActiveMinutesFrame.this.repaintAndUpdate();
				ActiveMinutesFrame.this.setUserDate(userInput);
				System.out.println(ActiveMinutesFrame.this.getUserDateString());
			}
		});
		
		// Add the elements for the top Menu bar
		this.add(userInput);
		desktopMenuBar.add(Box.createHorizontalGlue());
		desktopMenuBar.add(refreshbutn);
		// Add the menu bar
		this.add(desktopMenuBar);
*/
		////// Active Minutes Goal
		int totalActiveMins = lightActiveMins + fairlyActiveMins + veryActiveMins;		//Find the total active min's
		JPanel activeMinGoalsPanel = new JPanel();										//Create the subPanel
		activeMinGoalsPanel.setBackground(this.getBackground());
		//Calculate the progress label
		String activelabel = this.setProgressLabel("Minutes", "min's", totalActiveMins, stepGoals);
		activeMinGoalsPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("src/main/resources/Lightning.png"));
		lblNewLabel.setBounds(11, 7, 85, 87);
		activeMinGoalsPanel.add(lblNewLabel);
		JLabel lblActive = new JLabel(activelabel);
		lblActive.setBounds(144, 7, 286, 109);
		//lblActive.setBounds(150, 115, 140, 70);                                          
		activeMinGoalsPanel.add(lblActive); // Add the label to the subPanel
		

		// Calculate the progress
		double activeMin = (totalActiveMins)/(double)activeMinGoals;

		int angleProgressActiveMin = (int)((activeMin)*360);

		activeMinGoalsPanel.setBounds(0, 6, 500, 120);
		add(activeMinGoalsPanel);
		// Create the progress bar 
		ProgressBar progBar3 = new ProgressBar(0,0, Color.BLUE);
		progBar3.setBounds(0, 0, 120, 120);
		activeMinGoalsPanel.add(progBar3);
		progBar3.setArcAngle(angleProgressActiveMin);
		
		/////// Floor Goal
		JPanel floorGoalsPanel = new JPanel();
		floorGoalsPanel.setBackground(this.getBackground());
		String floorslabel = this.setProgressLabel("Floors", "floors", floors, floorGoals);
		floorGoalsPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("src/main/resources/Stairs.png"));
		lblNewLabel_1.setBounds(11, 9, 84, 83);
		floorGoalsPanel.add(lblNewLabel_1);
		JLabel lblFloors = new JLabel(floorslabel);
		lblFloors.setBounds(143, 5, 304, 109);
		
		floorGoalsPanel.add(lblFloors);

		// Calculate the progress
		//int trial=7;
		double floorsArc = (floors)/(double)floorGoals;
		int angleProgressFloors = (int)((floorsArc)*360);

		floorGoalsPanel.setBounds(0, 130, 500, 120);
		add(floorGoalsPanel);
		// Create the progress Bar
		ProgressBar progBar2 = new ProgressBar(0,0, Color.GREEN);
		progBar2.setBounds(0, 0, 120, 120);
		floorGoalsPanel.add(progBar2);
		progBar2.setArcAngle(angleProgressFloors);
		////// Steps Goal
		JPanel stepsGoalsPanel = new JPanel();
		stepsGoalsPanel.setBackground(this.getBackground());
		String stepslabel = this.setProgressLabel("Steps", "steps", steps, stepGoals);
		stepsGoalsPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("src/main/resources/Steps.png"));
		lblNewLabel_2.setBounds(11, 10, 85, 90);
		stepsGoalsPanel.add(lblNewLabel_2);
		JLabel lblSteps = new JLabel(stepslabel);
		lblSteps.setBounds(141, 5, 306, 111);
		stepsGoalsPanel.add(lblSteps);

		// Calculate the progress
		double stepsArc = (steps)/(double)stepGoals;
		int angleProgressSteps = (int)((stepsArc)*360);

		stepsGoalsPanel.setBounds(0, 250, 500, 120);
		add(stepsGoalsPanel);
		// Create the progress bar
		ProgressBar progBar1 = new ProgressBar(0, 0,  Color.YELLOW);
		progBar1.setBounds(0, 5, 120, 120);
		stepsGoalsPanel.add(progBar1);
		progBar1.setArcAngle(angleProgressSteps);
		////// Distance Goal
		String units = "km"; //tmp - here we put the unit change
		JPanel distanceGoalsPanel = new JPanel();
		distanceGoalsPanel.setBackground(this.getBackground());
		String distanceLabel = this.setProgressLabel("Distance", units, distance, distanceGoals);
		distanceGoalsPanel.setLayout(null);
		JLabel lblDistance = new JLabel(distanceLabel);
		lblDistance.setBounds(136, 5, 311, 99);
		distanceGoalsPanel.add(lblDistance);

		// Calculate the progress
		double distArc = (distance)/(double)distanceGoals;
		int angleProgressDistance = (int)((distArc)*360);

		distanceGoalsPanel.setBounds(0, 370, 600, 120);
		add(distanceGoalsPanel);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("src/main/resources/MapIcon.png"));
		lblNewLabel_3.setBounds(11, 9, 84, 83);
		distanceGoalsPanel.add(lblNewLabel_3);
		// Create the progress bar
		ProgressBar progBar4 = new ProgressBar(0, 0, Color.ORANGE);
		progBar4.setBounds(0, 0, 120, 120);
		distanceGoalsPanel.add(progBar4);
		progBar4.setArcAngle(angleProgressDistance);

		/*
		JLabel btnNewButton_1 = new JLabel("");
		btnNewButton_1.setIcon(new ImageIcon("ActiveMin.png"));
		btnNewButton_1.setBounds(287, 85, 128, 108);
		progBar3.setBounds(302, 89, 128, 122);
		add(btnNewButton_1);
		add(progBar3);
		 */
	}
	
	/**
	 * Used to set the formatting for the JFormattedTextField at the top of the Panel
	 * 
	 * @param s String of the format
	 * @return MaskFormatter object
	 */
	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}
	
	/**
	 * Set the user date from the JFormattedTextField.
	 * This sets the String array's 3 respective elements
	 * 
	 * @param userInput JFormattedTextField object
	 */
	public void setUserDate(JFormattedTextField userInput) {
		String in = userInput.getText();
		if (in == null) {
			//do nothing - yet?
		}
		else {
			this.userDate[0] = in.substring(0, 4);
			this.userDate[1] = in.substring(6, 8);
			this.userDate[2] = in.substring(8, 10);
		}
	}
	
	/**
	 * Returns the User Date String Array.
	 * This will be used for creating new API objects with the user's requested date
	 * 
	 * @return String[] userDate
	 */
	public String[] getUserDate() {
		return this.userDate;
	}
	
	/**
	 * Returns a single String that holds the components of the userDate String array.
	 * This will be used to print the date where needed within the element itself
	 * 
	 * @return String 
	 */
	public String getUserDateString() {
		String[] tmpArr = this.getUserDate();
		String year = tmpArr[0];
		String month = tmpArr[1];
		String day = tmpArr[2];
		String out = year + "/" + month + "/" + day;
		return out;
	}
	
	/**
	 * Repaint the entire panel. ***in test***
	 */
	public void repaintAndUpdate() {
		this.setBg(new Color(100,0,0));
		this.validate();
		this.repaint();
	}

	/**
	 * Get the Background of the panel
	 * 
	 * @return Color
	 */
	public Color getBg() {
		return bg;
	}
	
	/**
	 * Set the background of the panel
	 * 
	 * @param bg Color
	 */
	public void setBg(Color bg) {
		this.bg = bg;
	}

	/**
	 * Method to make the label making easier since there are many conditions for the progress
	 * @param category	String		ex: Distance
	 * @param categoryUnits	String	ex: km's
	 * @param progress	double		
	 * @param progressGoal	double	
	 * @return	progressLabel
	 */
	public String setProgressLabel(String category, String categoryUnits, double progress, double progressGoal) {
		String progressLabel;
		double progressLeft = progressGoal - progress;
		if(progressLeft<0){
			// JLabels to print the text for the testFitBitAPI
			progressLabel = "<html>" +  "<br> You are above your goal by "+ -progressLeft +
					"<br>" + category + "       : " + progress     + " " + categoryUnits +
					"<br>" + category + "(Goals): " + progressGoal + " " + categoryUnits +
					"</html>";
		}
		else if(progressLeft>0){
			progressLabel = "<html>" +  "<br> You are below your goal by "+ progressLeft+
					"<br>" + category + "       : " + progress     + " " + categoryUnits +
					"<br>" + category + "(Goals): " + progressGoal + " " + categoryUnits +
					"</html>";
		}
		else{ 
			progressLabel = "<html>" +  "<br> You have exceeded your goal by "+ progressLeft+
					"<br>" + category + "       : " + progress     + " " + categoryUnits +
					"<br>" + category + "(Goals): " + progressGoal + " " + categoryUnits +
					"</html>";

		}

		return progressLabel;
	}
}


