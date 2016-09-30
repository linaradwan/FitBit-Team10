package ca.uwo.csd.cs2212.team10;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import java.util.Calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;

import javax.swing.SwingUtilities;

import org.json.JSONException;

/**
 * This class represents the majority of the UI. This is a JTabbedPane that organizes the main sections of the program.
 * 
 * @author UI Team (Vincent, John, Lina, Gustavo)
 */
public class MainTabWindow extends JPanel
{
	// The Point Array holds point objects that refer to the position of the JDesktopPane elements
	private Point[] pointArray = {new Point(0,0), new Point(720,200), new Point(490,0), new Point(0,0), new Point(720,0)};
	private UserSettings userSettings;
	private ObjectSerialization objSerial;
	
	private final JInternalFrame heartRateFrame;
	private final JInternalFrame calBurnFrame; 
	private final JInternalFrame activeMinFrame;
	private final JInternalFrame sedMinFrame;

	private int dateSetting = 0; // The following will be the formats used and the corresponding numerical value
	// 0 = dd/mm/yyyy
	// 1 = yyyy/mm/dd
	String[] userDate = {"yyyy","mm","dd"};
	JLabel time;
	Boolean tmp;
	final private Fitbit fitbit;
	private static BestLifeStats bestlife;
	private static HeartStats heartrate ;
	private static DailyStats daily;

	int outOfRange;
	int fatBurn ;
	int cardio ;
	int peak;
	int restHeartRate;

	int floors;
	int steps ;
	double distance ;
	int calories;
	int sedentaryMins;
	int lightActiveMins;
	int fairlyActiveMins ;
	int veryActiveMins;
	int activeMinGoals;
	int caloriesOutGoals ;
	double distanceGoals;
	int floorGoals;
	int stepGoals;

	double bestDistance ;
	String bestDistanceDate ;
	double bestFloor;
	String bestFloorDate ;
	long bestStep ;
	String bestStepDate;
	double lifeDistance;
	double lifeFloors ;
	long lifeSteps;

    int mapZoomLevel = 1;
    ImageIcon mapImage;
    JScrollPane locationList;   
    final Map map;

	/**
	 * The main constructor the holds the majority of the UI. The constructor is separated into the following sections;
	 * Dashboard Dashboard Menu Stats Settings Each of these sections make their respective content that is housed inside a
	 * JTabbedPane container.
	 * @param fitbit1 Fitbit object
	 * @throws Exception handled by MainWindow
	 */
	//public MainTabWindow(Fitbit fitbit) throws Exception
public MainTabWindow(Fitbit fitbit1) throws Exception
	{

		super(new GridLayout(1, 1));

		this.fitbit = fitbit1;

		// //////////////TESTING OBJECT SERIALIZATION//////////////
		userSettings = new UserSettings();
		// userSettings.setUnits("imperial");
		// setPointArray(Point (1,1),2,3,4,5)
		objSerial = new ObjectSerialization(userSettings);
		// objSerial.storeUserSettings();
		userSettings = objSerial.loadUserSettings();

		
		System.out.println(userSettings + "\n TEST");
		Point[] savedPointArray = userSettings.getPointArray();
		if (savedPointArray != null) {
			setPointArray(savedPointArray[1], savedPointArray[2], savedPointArray[3], savedPointArray[4]);
			System.out.println(savedPointArray[0] + " hello");
		}
		RefreshTokens.setUnits(userSettings.getUnits());
		// //////////////TESTING OBJECT SERIALIZATION//////////////
		
        
        String curYear = Calendar.getInstance().get(Calendar.YEAR) + "";
		int intMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		
		int intDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) ;
		String curMonth="";
		String curDay="";
		if (intMonth<10)
			curMonth = "0"+intMonth;
		else
			curMonth = intMonth +"";
				

		if (intDay<10)
			curDay = "0"+intDay;
		else
			curDay = intDay +"";
			
	
			
		// Create the API classes and the relevant variables associated with each
		 heartrate = fitbit.getHeartActivity(curYear, curMonth, curDay);
		 outOfRange = heartrate.getOutOfRange();
		 fatBurn = heartrate.getFatBurn();
		 cardio = heartrate.getCardio();
		 peak = heartrate.getPeak();
		 restHeartRate = heartrate.getRestHeartRate();

		 bestlife = fitbit.getBestLifeActivity();
		 bestDistance = bestlife.getBestDistance();
		 bestDistanceDate = bestlife.getBestDistanceDate();
		 bestFloor = bestlife.getBestFloor();
		 bestFloorDate = bestlife.getBestFloorDate();
		 bestStep = bestlife.getBestStep();
		 bestStepDate = bestlife.getBestStepDate();
		 lifeDistance = bestlife.getLifeDistance();
		 lifeFloors = bestlife.getLifeFloors();
		 lifeSteps = bestlife.getLifeSteps();

		 daily = fitbit.getDailyActivity(curYear, curMonth, curDay);
		 floors = daily.getFloors();
		 steps = daily.getSteps();
		 distance = daily.getDistance();
		 calories = daily.getCalories();
		 sedentaryMins = daily.getSedentaryMins();
		 lightActiveMins = daily.getLightActiveMins();
		 fairlyActiveMins = daily.getFairlyActiveMins();
		 veryActiveMins = daily.getVeryActiveMins();
		 activeMinGoals = daily.getActiveMinGoals();
		 caloriesOutGoals = daily.getCaloriesOutGoals();
		 distanceGoals = daily.getDistanceGoals();
		 floorGoals = daily.getFloorGoals();
		 stepGoals = daily.getStepGoals();



		// create a tabbed pane that will hold the contents.
		final JTabbedPane tabbedPane = new ClosableTabbedPane();

		/**
		 * Dashboard
		 */

		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());

		// A top menu bar that appears when the user first uses the Dashboard Menu
		final JMenuBar desktopMenuBar = new JMenuBar();
		desktopMenuBar.setBackground(new Color(100, 100, 100));
		desktopMenuBar.setBorderPainted(false);

		// Add the menu bar
		panel1.add(desktopMenuBar, BorderLayout.NORTH);

		// Adding the JDesktopPane into the "Dashboard" Panel
		final JDesktopPane desktop = new JDesktopPane();
		desktop.setPreferredSize(new java.awt.Dimension(600, 400));
		/*
		 * //add a panel that we the elements are going to be added on final JPanel panelback1 = new JPanel();
		 * panelback1.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(35, 35, 35))); panelback1.setBackground(new
		 * Color(40, 40, 40)); panelback1.setForeground(new Color(40, 40, 40)); panelback1.setBounds(0, 0, 1128, 644);
		 * panel1.add(panelback1, BorderLayout.WEST); panelback1.setLayout(null);
		 */

		desktop.setBackground(new Color(40, 40, 40));

		/*
		 * Elements needed: Map HeartRate Zone Calories Burned Daily Activity Records // Sedentary Minutes //
		 *
		 * Note: The point array is in this order
		 * 		this.pointArray[0] = mapPoint;
				this.pointArray[1] = heartPoint; 720, 200, 485, 355, true, true
				this.pointArray[2] = calPoint;		490, 0, 235, 520, true, true, true
				this.pointArray[3] = activePoint;  0, 0, 510, 520, true, true, true
				this.pointArray[4] = sedPoint;	720, 0, 475, 210, true, true, true
		 */
	
		// Add the mapFrame one with Metric distance and one with imperial distance and set the imperial one to false

		/*
		 * final JInternalFrame mapFrameImperial = makeInternalFrame("Interactive Map", 400, 0, 200, 200, true, true, true);
		 * MapFrame mapContent2 = new MapFrame(bestDistanceImperialnum, bestDistanceDate, lifeDistanceImperial,"mile");
		 * mapFrameImperial.add( mapContent2); mapFrameImperial.setVisible(false); desktop.add( mapFrameImperial );
		 */

		// The Heart Rate Zone element
		this.heartRateFrame = makeInternalFrame("Heart Rate Zone", getPointArray()[1].x, getPointArray()[1].y, 485, 355, true, true,
				true);
		HeartRateZoneFrame heartRateContent = new HeartRateZoneFrame(outOfRange, fatBurn, cardio, peak, restHeartRate);
		heartRateFrame.add(heartRateContent);
		desktop.add(heartRateFrame);

		// The Calories Burne Element
		this.calBurnFrame = makeInternalFrame("Calories Burned",  getPointArray()[2].x,  getPointArray()[2].y, 235, 520, true, true, true);
		CaloriesBurnedFrame calBurnContent = new CaloriesBurnedFrame(calories, caloriesOutGoals);
		calBurnFrame.add(calBurnContent);
		desktop.add(calBurnFrame);

		
		// The Active Minutes element
		this.activeMinFrame = makeInternalFrame("Daily Goals",  getPointArray()[3].x,  getPointArray()[3].y, 510, 520, true, true, true);
		ActiveMinutesFrame activeMinContent = new ActiveMinutesFrame(lightActiveMins, fairlyActiveMins, veryActiveMins,
				activeMinGoals, floors, steps, distance, floorGoals, stepGoals, distanceGoals);

		activeMinFrame.add(activeMinContent);
		desktop.add(activeMinFrame);

		// Create the UserInput Text Box
		final JFormattedTextField userInput = new JFormattedTextField(createFormatter("####/##/##"));
		userInput.setBounds(0, 0, 150, 20);
		

		// Create a refresh button
		JButton desiredDate = new JButton("Input Desired Date");
		desiredDate.setBackground(new Color(250, 150, 150));
		desiredDate.setBorderPainted(false);
		desiredDate.setBounds(400, 0, 100, 20);
		desiredDate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				userDate[0] = userInput.getText().substring(0, 4);
				userDate[1] = userInput.getText().substring(5, 7);
			userDate[2] = userInput.getText().substring(8, 10);
		System.out.println(userDate[0].toString());
		System.out.println(userDate[1].toString());
		System.out.println(userDate[2].toString());

			}
		});
		
		  desktopMenuBar.add(desiredDate);
		desktopMenuBar.add(userInput);
		
		
		
		
		

		// Add a refresh button
		final JButton refreshbutn = new JButton("");
		refreshbutn.setIcon(new ImageIcon("src/main/resources/refreshbutton.png"));
		refreshbutn.setBackground(new Color(150, 150, 150));
		refreshbutn.setBorderPainted(false);
		desktopMenuBar.add(Box.createHorizontalGlue());
		desktopMenuBar.add(refreshbutn);

		JLabel thankYou = new JLabel("");
		thankYou.setText("<html>Credits to FitBit for their services</html>");
		thankYou.setFont(new Font("Courier New", Font.BOLD, 12));
		thankYou.setForeground(Color.LIGHT_GRAY);
		thankYou.setBounds(420, 510, 728, 93);
		panel1.add(thankYou);

		// Add the elements for the top Menu bar
		// this.add(userInput);
		desktopMenuBar.add(Box.createHorizontalGlue());
		desktopMenuBar.add(refreshbutn);
		// Add the menu bar

		// The Sedentary Minutes element
		this.sedMinFrame = makeInternalFrame("Sedentary Minutes", getPointArray()[4].x, getPointArray()[4].y, 475, 210, true, true, true);
		SedentaryMinutesFrame sedMinContent = new SedentaryMinutesFrame(sedentaryMins);
		sedMinFrame.add(sedMinContent);
		desktop.add(sedMinFrame);

		panel1.add(desktop);

		
		// add the the panel to the tabbed pane
		tabbedPane.addTab("Dashboard", null, panel1, "tmp1"); // Add the desktop pane to the tabbedPane
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		time = new JLabel(" " + new Date());

		// Retrieve the locations of all the frame elements from the desktop screen.
		Point heartRateFramePoint = heartRateFrame.getLocation();
		Point calBurnFramePoint = calBurnFrame.getLocation();
		Point activeMinFramePoint = activeMinFrame.getLocation();
		Point sedMinFramePoint = sedMinFrame.getLocation();

		// Add these points to the pointArray for the object serialization.
		pointArray = new Point[5];
		this.setPointArray(heartRateFramePoint, calBurnFramePoint, activeMinFramePoint, sedMinFramePoint);
		
		//Now that the point Array has been updated, we set the values for the object seriealization
		// //////////////TESTING OBJECT SERIALIZATION//////////////
		userSettings.setPointArray(this.getPointArray());
		
		userSettings.setUnits("metric");
		objSerial.storeUserSettings(userSettings);
		System.out.println("//////////////////\n" + userSettings + "\n \\\\\\\\\\\\\\\\");
		
		
		
		RefreshTokens.setUnits(userSettings.getUnits());
		
		// //////////////TESTING OBJECT SERIALIZATION//////////////
		/**
		 * Dashboard Menu
		 */
		JComponent panel2 = makeTextPanel("Dashboard Menu");
		// create a label named dashboard menu and add it to the panel

		JLabel lblmenu = new JLabel("Dashboard Menu");
		lblmenu.setForeground(SystemColor.inactiveCaption);
		lblmenu.setFont(new Font("Lucida Grande", Font.PLAIN, 49));
		lblmenu.setBounds(44, 6, 543, 72);
		panel2.add(lblmenu);
		// add a scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(40, 40, 40));
		scrollPane.setForeground(new Color(40, 40, 40));
		scrollPane.setBounds(18, 77, 1110, 567);

		panel2.add(scrollPane);
		// add a panel on top of the scroll pane to add the elements easier
		JPanel panelscroll = new JPanel();
		panelscroll.setBounds(79, 100, 945, 300);
		panelscroll.setBackground(new Color(40, 40, 40));
		panelscroll.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(35, 35, 35)));
		scrollPane.setViewportView(panelscroll);

		// Interactive Map description and button add
		

		// add a check box for map
		
		panelscroll.setLayout(null);
		

		/*
		 * TMP - commented out since the time series is not needed as of 2016.03.01 // Time Series description and button add
		 * JLabel tsDescript = new JLabel(""); tsDescript.setText(
		 * "<html>The Time Series displays <BR>the information for all your <BR>accumulated progress,data like: <BR>total steps,calories,distance,<BR>and heart rate.</html>"
		 * ); tsDescript.setFont(new Font ("Courier New",Font.BOLD,16)); tsDescript.setForeground(Color.LIGHT_GRAY);
		 * tsDescript.setBounds(430, 120, 728, 93); panelscroll.add(tsDescript); //add a check box for time series final
		 * JCheckBox chckbxTimeSeries = new JCheckBox("Time Series"); chckbxTimeSeries.setBounds(460, 220, 128, 23);
		 * chckbxTimeSeries.setFont(new Font("Lucida Grande", Font.BOLD, 15)); chckbxTimeSeries.setForeground(Color.WHITE);
		 * chckbxTimeSeries.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
		 * btnadd.setVisible(chckbxTimeSeries.isSelected()==false);
		 * //timeSeriesPanel.setVisible(chckbxTimeSeries.isSelected()); } }); panelscroll.add(chckbxTimeSeries);
		 */

		// Heart Rate Zone description and button add
		JLabel hrDescript = new JLabel("");
		hrDescript.setText(
				"<html>The Heart Rate displays <BR> your daily heart zone <BR>information and resting <BR>heart rate.</html>");
		hrDescript.setFont(new Font("Courier New", Font.BOLD, 16));
		hrDescript.setForeground(Color.LIGHT_GRAY);
		hrDescript.setBounds(850, 80, 728, 93);
		panelscroll.add(hrDescript);
		// add a check box for heart rate
		final JCheckBox chckbxHeartRate = new JCheckBox("Heart Rate");
		chckbxHeartRate.setSelected(true);
		chckbxHeartRate.setBounds(900, 180, 128, 23);
		chckbxHeartRate.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		chckbxHeartRate.setForeground(Color.WHITE);
		chckbxHeartRate.setBackground(new Color(40, 40, 40));
		chckbxHeartRate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				heartRateFrame.setVisible(chckbxHeartRate.isSelected());
			}
		});
		panelscroll.add(chckbxHeartRate);

		// Calorie Zone description and button add
		JLabel cbDescript = new JLabel("");
		cbDescript.setText("<html>The Calories Burned displays <BR>that amount of calories <BR>you burned</html>");
		cbDescript.setFont(new Font("Courier New", Font.BOLD, 16));
		cbDescript.setForeground(Color.LIGHT_GRAY);
		cbDescript.setBounds(30, 320, 728, 93);
		panelscroll.add(cbDescript);

		final JCheckBox caloriesBurned = new JCheckBox("Calories Burned");
		caloriesBurned.setSelected(true);
		caloriesBurned.setBounds(60, 430, 157, 23);
		caloriesBurned.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		caloriesBurned.setForeground(Color.WHITE);
		caloriesBurned.setBackground(new Color(40, 40, 40));
		caloriesBurned.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calBurnFrame.setVisible(caloriesBurned.isSelected());
			}
		});
		panelscroll.add(caloriesBurned);

		// Sedentary Minutes description and button add
		JLabel smDescript = new JLabel("");
		smDescript.setText("<html>The Sedentary Min displays <BR> the time you are <BR>not in active state.</html>");
		smDescript.setFont(new Font("Courier New", Font.BOLD, 16));
		smDescript.setForeground(Color.LIGHT_GRAY);
		smDescript.setBounds(30, 80, 328, 93);
		panelscroll.add(smDescript);

		final JCheckBox sedMin = new JCheckBox("Sedentary Min");
		sedMin.setSelected(true);

		sedMin.setBounds(60, 180, 160, 50);
		sedMin.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		sedMin.setForeground(Color.WHITE);
		sedMin.setBackground(new Color(40, 40, 40));
		sedMin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sedMinFrame.setVisible(sedMin.isSelected());
			}
		});
		panelscroll.add(sedMin);

		// Active Minutes description and button add
		JLabel daDescript = new JLabel("");
		daDescript.setText(
				"<html>The Daily Activity <BR>records your daily <BR> activity and progress<BR> you worked with FitBit.</html>");
		daDescript.setFont(new Font("Courier New", Font.BOLD, 16));
		daDescript.setForeground(Color.LIGHT_GRAY);
		daDescript.setBounds(860, 320, 728, 93);
		panelscroll.add(daDescript);

		final JCheckBox dailyAct = new JCheckBox("Daily Activity");
		dailyAct.setSelected(true);
		dailyAct.setBounds(900, 430, 157, 23);
		dailyAct.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		dailyAct.setForeground(Color.WHITE);
		dailyAct.setBackground(new Color(40, 40, 40));
		dailyAct.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				activeMinFrame.setVisible(dailyAct.isSelected());
			}
		});
		panelscroll.add(dailyAct);

		// add the panel to the tabbed pane
		tabbedPane.addTab("Menu", null, panel2, "tmp2");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		tabbedPane.setBackgroundAt(1, Color.WHITE);

		/**
		 * Stats page
		 */
		// create a panel and add it to the tabbed pane
		ImageIcon icon3 = new ImageIcon("stats_icon.png");
		JComponent panel3 = makeTextPanel("Stats");
		tabbedPane.addTab("Stats", icon3, panel3, "tmp3");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane.setBackgroundAt(2, Color.WHITE);
		panel3.setPreferredSize(new Dimension(410, 50));

		// panel of the life time button
		final JPanel panelLifeTime = new JPanel();
		panelLifeTime.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(35, 35, 35)));
		panelLifeTime.setBackground(new Color(40, 40, 40));
		panelLifeTime.setForeground(new Color(40, 40, 40));
		panelLifeTime.setBounds(150, 6, 1000, 639);
		panel3.add(panelLifeTime, BorderLayout.CENTER);
		panelLifeTime.setLayout(null);
		JLabel lblLifetime = new JLabel("Lifetime Totals");
		lblLifetime.setForeground(SystemColor.inactiveCaption);
		lblLifetime.setFont(new Font("Lucida Grande", Font.PLAIN, 49));
		lblLifetime.setBounds(44, 6, 382, 72);

		JLabel distancelifetime = new JLabel("Distance");
		distancelifetime.setForeground(Color.WHITE);
		distancelifetime.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		distancelifetime.setBounds(60, 65, 382, 72);
		panelLifeTime.add(distancelifetime);

		JLabel lifetimeDistanceMetric = new JLabel("Total distance Travelled:" + Double.toString(lifeDistance) + "km");
		lifetimeDistanceMetric.setForeground(Color.WHITE);
		lifetimeDistanceMetric.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lifetimeDistanceMetric.setBounds(90, 95, 382, 72);
		lifetimeDistanceMetric.setVisible(true);
		panelLifeTime.add(lifetimeDistanceMetric);

	

		JLabel floorsTitleLifetime = new JLabel("Floors");
		floorsTitleLifetime.setForeground(Color.WHITE);
		floorsTitleLifetime.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		floorsTitleLifetime.setBounds(60, 180, 382, 72);
		panelLifeTime.add(floorsTitleLifetime);

		JLabel totalFloorsLifeTime = new JLabel("Total Floors Climbed: " + lifeFloors + " floors");
		totalFloorsLifeTime.setForeground(Color.WHITE);
		totalFloorsLifeTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		totalFloorsLifeTime.setBounds(90, 210, 382, 72);
		panelLifeTime.add(totalFloorsLifeTime);

		JLabel lifeTimestepsTitle = new JLabel("Steps");
		lifeTimestepsTitle.setForeground(Color.WHITE);
		lifeTimestepsTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		lifeTimestepsTitle.setBounds(60, 295, 382, 72);
		panelLifeTime.add(lifeTimestepsTitle);

		JLabel lifeTimeStepsTotal = new JLabel("Total Steps taken: " + lifeSteps + " steps");
		lifeTimeStepsTotal.setForeground(Color.WHITE);
		lifeTimeStepsTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lifeTimeStepsTotal.setBounds(90, 325, 382, 72);
		panelLifeTime.add(lifeTimeStepsTotal);
		panelLifeTime.add(lblLifetime);

		// panel of the best days button
		final JPanel panelBestDays = new JPanel();
		panelBestDays.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(35, 35, 35)));
		panelBestDays.setBackground(new Color(40, 40, 40));
		panelBestDays.setForeground(new Color(40, 40, 40));
		panelBestDays.setBounds(150, 6, 1000, 639);
		panel3.add(panelBestDays, BorderLayout.CENTER);
		panelBestDays.setLayout(null);

		// these are the labels for the best days we're gonna add the test data when we write the data... I know how to do
		// that i still didn't add it because it doesn't run it on my elcipse but it works
		JLabel lblBestDays = new JLabel("Best Days");
		lblBestDays.setForeground(SystemColor.inactiveCaption);
		lblBestDays.setFont(new Font("Lucida Grande", Font.PLAIN, 49));
		lblBestDays.setBounds(44, 6, 382, 72);
		panelBestDays.add(lblBestDays);

		JLabel distance2 = new JLabel("Distance");
		distance2.setForeground(Color.WHITE);
		distance2.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		distance2.setBounds(60, 65, 382, 72);
		panelBestDays.add(distance2);

		JLabel bestDistancedate2 = new JLabel("Best Day: " + bestDistanceDate);
		bestDistancedate2.setForeground(Color.WHITE);
		bestDistancedate2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		bestDistancedate2.setBounds(90, 95, 382, 72);
		panelBestDays.add(bestDistancedate2);

		JLabel bestDistanceMetric = new JLabel("Best Distance: " + bestDistance+"km");
		bestDistanceMetric.setForeground(Color.WHITE);
		bestDistanceMetric.setVisible(true);
		bestDistanceMetric.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		bestDistanceMetric.setBounds(110, 130, 382, 72);
		panelBestDays.add(bestDistanceMetric);


		JLabel bestFloorstitle = new JLabel("Floors");
		bestFloorstitle.setForeground(Color.WHITE);
		bestFloorstitle.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		bestFloorstitle.setBounds(60, 180, 382, 72);
		panelBestDays.add(bestFloorstitle);

		JLabel bestFloorDtlbl = new JLabel("Best Floor Date: " + bestFloorDate);
		bestFloorDtlbl.setForeground(Color.WHITE);
		bestFloorDtlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		bestFloorDtlbl.setBounds(90, 210, 382, 72);
		panelBestDays.add(bestFloorDtlbl);

		JLabel bestfloorlbl = new JLabel("Best Floor: " + bestFloor + " floors");
		bestfloorlbl.setForeground(Color.WHITE);
		bestfloorlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		bestfloorlbl.setBounds(110, 245, 382, 72);
		panelBestDays.add(bestfloorlbl);

		JLabel bestStepstitle = new JLabel("Steps");
		bestStepstitle.setForeground(Color.WHITE);
		bestStepstitle.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		bestStepstitle.setBounds(60, 295, 382, 72);
		panelBestDays.add(bestStepstitle);

		JLabel bestStepsDtlbl = new JLabel("Best Steps Date: " + bestStepDate);
		bestStepsDtlbl.setForeground(Color.WHITE);
		bestStepsDtlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		bestStepsDtlbl.setBounds(90, 325, 382, 72);
		panelBestDays.add(bestStepsDtlbl);

		JLabel bestStepslbl = new JLabel("Best Steps: " + bestStep + " steps");
		bestStepslbl.setForeground(Color.WHITE);
		bestStepslbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		bestStepslbl.setBounds(110, 360, 382, 72);
		panelBestDays.add(bestStepslbl);

		// panel of the accolades button
		final JPanel panelAccolades = new JPanel();
		panelAccolades.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(35, 35, 35)));
		panelAccolades.setBackground(new Color(40, 40, 40));
		panelAccolades.setForeground(new Color(40, 40, 40));
		panelAccolades.setBounds(150, 6, 1000, 639);
		panel3.add(panelAccolades, BorderLayout.CENTER);
		panelAccolades.setLayout(null);

		JLabel lblAccolades = new JLabel("Accolades");
		lblAccolades.setForeground(SystemColor.inactiveCaption);
		lblAccolades.setFont(new Font("Lucida Grande", Font.PLAIN, 49));
		lblAccolades.setBounds(44, 6, 382, 72);
		panelAccolades.add(lblAccolades);

		/// ACColades
		Accolades accolades = new Accolades();
		accolades.set_accolades(0, bestlife, daily, heartrate);
		// accolades.getCheck(index)

		JLabel lock1Accold = new JLabel("");
		lock1Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock1Accold.setBounds(30, 110, 106, 72);
		lock1Accold.setToolTipText(accolades.getTitle(0));
		panelAccolades.add(lock1Accold);
		if (accolades.getCheck(0) == true)
		{
			lock1Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_1rsz_badge0.png"));
			lock1Accold.setBounds(30, 90, 106, 110);
			lock1Accold.setToolTipText(accolades.getDescription(0));

		}

		JLabel lock2Accold = new JLabel("");
		lock2Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock2Accold.setBounds(150, 110, 106, 72);
		panelAccolades.add(lock2Accold);
		lock2Accold.setToolTipText(accolades.getTitle(1));
		if (accolades.getCheck(1) == true)
		{
			lock2Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_1rsz_badge1.png"));
			lock2Accold.setBounds(150, 90, 106, 110);
			lock2Accold.setToolTipText(accolades.getDescription(1));

		}

		JLabel lock3Accold = new JLabel("");
		lock3Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock3Accold.setBounds(270, 110, 106, 72);
		lock3Accold.setToolTipText(accolades.getTitle(2));
		panelAccolades.add(lock3Accold);
		if (accolades.getCheck(2) == true)
		{
			lock3Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge2.png"));
			lock3Accold.setBounds(270, 90, 120, 110);
			lock3Accold.setToolTipText(accolades.getDescription(2));

		}

		JLabel lock4Accold = new JLabel("");
		lock4Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock4Accold.setBounds(390, 110, 106, 72);
		lock4Accold.setToolTipText(accolades.getTitle(3));
		panelAccolades.add(lock4Accold);
		if (accolades.getCheck(3) == true)
		{
			lock4Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge3.png"));
			lock4Accold.setBounds(390, 90, 120, 110);
			lock4Accold.setToolTipText(accolades.getDescription(3));

		}

		JLabel lock5Accold = new JLabel("");
		lock5Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock5Accold.setBounds(500, 110, 106, 72);
		lock5Accold.setToolTipText(accolades.getTitle(4));
		panelAccolades.add(lock5Accold);
		if (accolades.getCheck(4) == true)
		{

			lock5Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge4.png"));
			lock5Accold.setBounds(500, 90, 120, 110);

			lock5Accold.setToolTipText(accolades.getDescription(4));

		}

		JLabel lock6Accold = new JLabel("");
		lock6Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock6Accold.setBounds(610, 110, 106, 72);
		lock6Accold.setToolTipText(accolades.getTitle(5));
		panelAccolades.add(lock6Accold);
		if (accolades.getCheck(5) == true)
		{
			lock6Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge5.png"));
			lock6Accold.setBounds(610, 90, 120, 110);
			lock6Accold.setToolTipText(accolades.getDescription(5));

		}

		JLabel lock7Accold = new JLabel("");
		lock7Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock7Accold.setBounds(720, 110, 106, 72);
		lock7Accold.setToolTipText(accolades.getTitle(6));

		panelAccolades.add(lock7Accold);
		if (accolades.getCheck(6) == true)
		{
			lock7Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge6.png"));
			lock7Accold.setBounds(720, 90, 120, 110);
			lock7Accold.setToolTipText(accolades.getDescription(6));

		}

		JLabel lock8Accold = new JLabel("");
		lock8Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock8Accold.setBounds(830, 110, 106, 72);
		lock8Accold.setToolTipText(accolades.getTitle(7));
		panelAccolades.add(lock8Accold);

		if (accolades.getCheck(7) == true)
		{
			lock8Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge7.png"));
			lock8Accold.setBounds(830, 90, 120, 110);
			lock8Accold.setToolTipText(accolades.getDescription(7));

		}

		JLabel lock9Accold = new JLabel("");
		lock9Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock9Accold.setBounds(30, 240, 382, 72);
		lock9Accold.setToolTipText(accolades.getTitle(8));
		lock9Accold.setVisible(true);
		panelAccolades.add(lock9Accold);
		if (accolades.getCheck(8) == true)
		{
			lock9Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge8.png"));
			lock9Accold.setBounds(30, 240, 120, 110);
			lock9Accold.setToolTipText(accolades.getDescription(8));

		}

		JLabel lock10Accold = new JLabel("");
		lock10Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock10Accold.setBounds(150, 240, 382, 72);
		lock10Accold.setToolTipText(accolades.getTitle(9));
		panelAccolades.add(lock10Accold);
		if (accolades.getCheck(9) == true)
		{
			lock10Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge9.png"));
			lock10Accold.setBounds(150, 240, 120, 110);
			lock10Accold.setToolTipText(accolades.getDescription(9));

		}

		JLabel lock11Accold = new JLabel("");
		lock11Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock11Accold.setBounds(280, 240, 382, 72);
		lock11Accold.setToolTipText(accolades.getTitle(10));
		panelAccolades.add(lock11Accold);
		if (accolades.getCheck(10) == true)
		{
			lock11Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge10.png"));
			lock11Accold.setBounds(280, 240, 120, 110);
			lock1Accold.setToolTipText(accolades.getDescription(10));

		}

		JLabel lock12Accold = new JLabel("");
		lock12Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock12Accold.setBounds(390, 240, 382, 72);
		lock12Accold.setToolTipText(accolades.getTitle(11));
		panelAccolades.add(lock12Accold);
		if (accolades.getCheck(11) == true)
		{
			lock12Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge11.png"));
			lock12Accold.setBounds(390, 240, 120, 110);
			lock12Accold.setToolTipText(accolades.getDescription(11));

		}

		JLabel lock13Accold = new JLabel("");
		lock13Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock13Accold.setBounds(500, 240, 382, 72);
		lock13Accold.setToolTipText(accolades.getTitle(12));
		panelAccolades.add(lock13Accold);
		if (accolades.getCheck(12) == true)
		{
			lock13Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge12.png"));
			lock13Accold.setBounds(500, 240, 120, 110);
			lock13Accold.setToolTipText(accolades.getDescription(12));

		}

		JLabel lock14Accold = new JLabel("");
		lock14Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock14Accold.setBounds(610, 240, 382, 72);
		lock14Accold.setToolTipText(accolades.getTitle(13));
		panelAccolades.add(lock14Accold);
		if (accolades.getCheck(13) == true)
		{
			lock14Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge13.png"));
			lock14Accold.setBounds(610, 240, 120, 110);
			lock14Accold.setToolTipText(accolades.getDescription(13));

		}

		JLabel lock15Accold = new JLabel("");
		lock15Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock15Accold.setBounds(720, 240, 382, 72);
		lock15Accold.setToolTipText(accolades.getTitle(14));
		panelAccolades.add(lock15Accold);
		if (accolades.getCheck(14) == true)
		{
			lock15Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge14.png"));
			lock15Accold.setBounds(720, 240, 120, 110);
			lock15Accold.setToolTipText(accolades.getDescription(14));

		}

		JLabel lock16Accold = new JLabel("");
		lock16Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock16Accold.setBounds(830, 240, 382, 72);
		lock16Accold.setToolTipText(accolades.getTitle(15));
		panelAccolades.add(lock16Accold);
		if (accolades.getCheck(15) == true)
		{
			lock16Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge15.png"));
			lock16Accold.setBounds(830, 240, 120, 110);
			lock16Accold.setToolTipText(accolades.getDescription(15));

		}

		JLabel lock17Accold = new JLabel("");
		lock17Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock17Accold.setBounds(30, 400, 382, 72);
		lock17Accold.setToolTipText(accolades.getTitle(16));
		panelAccolades.add(lock17Accold);
		if (accolades.getCheck(16) == true)
		{
			lock17Accold.setIcon(new ImageIcon("src/main/resources/Badges/Accolades/rsz_badge16.png"));
			lock17Accold.setBounds(30, 410, 120, 110);
			lock17Accold.setToolTipText(accolades.getDescription(16));

		}

		JLabel lock18Accold = new JLabel("");
		lock18Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock18Accold.setBounds(150, 400, 382, 72);
		lock18Accold.setToolTipText(accolades.getTitle(17));
		panelAccolades.add(lock18Accold);
		if (accolades.getCheck(17) == true)
		{
			lock18Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge17.png"));
			lock18Accold.setBounds(150, 410, 382, 110);
			lock18Accold.setToolTipText(accolades.getDescription(17));

		}

		JLabel lock19Accold = new JLabel("");
		lock19Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock19Accold.setBounds(270, 400, 382, 72);
		lock19Accold.setToolTipText(accolades.getTitle(18));
		panelAccolades.add(lock19Accold);
		if (accolades.getCheck(18) == true)
		{
			lock19Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge18.png"));
			lock19Accold.setBounds(270, 410, 120, 110);

			lock19Accold.setToolTipText(accolades.getDescription(18));

		}

		JLabel lock20Accold = new JLabel("");
		lock20Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_lock.png"));
		lock20Accold.setBounds(390, 400, 382, 72);
		lock20Accold.setToolTipText(accolades.getTitle(19));
		panelAccolades.add(lock20Accold);
		if (accolades.getCheck(19) == true)
		{
			lock20Accold.setIcon(new ImageIcon("src/main/resources/Accolades/rsz_badge19.png"));
			lock20Accold.setBounds(390, 410, 120, 110);
			lock20Accold.setToolTipText(accolades.getDescription(19));

		}

		// panel where the buttons are added
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(35, 35, 35)));
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setForeground(new Color(40, 40, 40));
		panel_1.setBounds(6, 6, 118, 583);
		panel3.add(panel_1, BorderLayout.CENTER);
		panel3.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Panel of the Map button
        final JPanel panelMap = new JPanel();
        panelMap.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(35, 35, 35)));
        panelMap.setBackground(new Color(40, 40, 40));
        panelMap.setForeground(new Color(40, 40, 40));
        panelMap.setBounds(150, 6, 1000, 639);
        panel3.add(panelMap, BorderLayout.CENTER);
        panelMap.setLayout(new BorderLayout());


        JLabel lblMap= new JLabel("Scale of Lifetime Distances");
        lblMap.setForeground(SystemColor.inactiveCaption);
        lblMap.setFont(new Font("Lucida Grande", Font.PLAIN, 49));
        lblMap.setBounds(44, 6, 382, 72);
        panelMap.add(lblMap, BorderLayout.PAGE_START);

        // Create map object

        this.map = new Map(fitbit);
        this.map.calculateDistances();


        final JPanel mapButtonPanel = new JPanel();
        final JPanel mapPanel = new JPanel();
        final JLabel worldMap = new JLabel();
        final JTextArea locations = new JTextArea(40,20);
        worldMap.setForeground(Color.WHITE);
        locations.setForeground(Color.WHITE);
        locations.setBackground(new Color(40,40,40));
        worldMap.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
        locations.setFont(new Font("Lucida Grande", Font.PLAIN, 9));


        final JButton zoomOut = new JButton("Zoom -");
        final JButton zoomIn = new JButton("Zoom +");
        final JButton setLocation = new JButton("Set Location"); 
        final JButton refreshLocation = new JButton("Refresh"); 

        mapButtonPanel.setLayout(new BoxLayout(mapButtonPanel,BoxLayout.Y_AXIS));
        mapButtonPanel.add(zoomOut);
        mapButtonPanel.add(zoomIn); 
        mapButtonPanel.add(setLocation);
        mapButtonPanel.add(refreshLocation); 
        mapButtonPanel.setPreferredSize(new Dimension(115,400));
        mapButtonPanel.setBackground(new Color(40, 40, 40));

        mapPanel.setLayout(new BorderLayout());
        mapPanel.setPreferredSize(new Dimension(600,400));
        mapPanel.setMaximumSize(new Dimension(600,400));
        mapPanel.setBackground(new Color(40, 40, 40));
        mapImage = displayMap(mapZoomLevel,map);
        if(mapImage != null) worldMap.setIcon(displayMap(mapZoomLevel, map));
        else worldMap.setText("Sorry, the map could not be displayed due to an error."); 
        mapPanel.add(worldMap, BorderLayout.NORTH);




        final JPanel mapListPanel = new JPanel();
        mapListPanel.setBackground(new Color(40, 40, 40));
        locations.setText(map.getAchievedLocations());
        locationList = new JScrollPane(locations); 
        locationList.setBackground(new Color(40,40,40));
        mapListPanel.add(locationList);

        panelMap.add(mapListPanel, BorderLayout.LINE_START);  
        panelMap.add(mapPanel, BorderLayout.CENTER);
        panelMap.add(mapButtonPanel, BorderLayout.LINE_END);


        // Listen for map button events
        zoomOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(mapZoomLevel > 1) {
                    mapZoomLevel--;
                    mapImage = displayMap(mapZoomLevel,map);
                    if(mapImage != null) worldMap.setIcon(displayMap(mapZoomLevel, map));
                    else worldMap.setText("Sorry, the map could not be displayed due to an error."); 
                    mapPanel.add(worldMap, BorderLayout.NORTH);
                    panelMap.add(mapPanel, BorderLayout.CENTER);
                    locations.setText(map.getAchievedLocations());
                    mapListPanel.add(locationList);
                    panelMap.add(mapListPanel, BorderLayout.LINE_START);  
                } 
            }
        });
        zoomIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(mapZoomLevel < 8) { 
                    mapZoomLevel++;
                    mapImage = displayMap(mapZoomLevel,map);
                    if(mapImage != null) worldMap.setIcon(displayMap(mapZoomLevel, map));
                    else worldMap.setText("Sorry, the map could not be displayed due to an error."); 
                    mapPanel.add(worldMap, BorderLayout.NORTH);
                    panelMap.add(mapPanel, BorderLayout.CENTER);
                    locations.setText(map.getAchievedLocations());
                    mapListPanel.add(locationList);
                    panelMap.add(mapListPanel, BorderLayout.LINE_START);  
                } 
            }
        });
        setLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override

                    public void run () {
                        MapLocationSetWindow newLocation = new MapLocationSetWindow(map);
                        newLocation.setVisible(true);
                    }
                });
                mapImage = displayMap(mapZoomLevel,map);
                try {
                    if(mapImage != null) worldMap.setIcon(displayMap(mapZoomLevel, map));
                    else worldMap.setText("Sorry, the map could not be displayed due to an error.");
                    map.writeToJSONFile(); 
                } catch (Exception ex) {
                    worldMap.setText("Sorry, the map could not be displayed due to an error.");
                }
                mapPanel.add(worldMap, BorderLayout.NORTH);
                panelMap.add(mapPanel, BorderLayout.CENTER);
                locations.setText(map.getAchievedLocations());
                mapListPanel.add(locationList);
                panelMap.add(mapListPanel, BorderLayout.LINE_START);
            }
        });
        refreshLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mapImage = displayMap(mapZoomLevel,map);
                if(mapImage != null) worldMap.setIcon(displayMap(mapZoomLevel, map));
                else worldMap.setText("Sorry, the map could not be displayed due to an error."); 
                mapPanel.add(worldMap, BorderLayout.NORTH);
                panelMap.add(mapPanel, BorderLayout.CENTER);
                locations.setText(map.getAchievedLocations());
                mapListPanel.add(locationList);
                panelMap.add(mapListPanel, BorderLayout.LINE_START);   
            }
        });
		
        // add button lifetime toals
		JToggleButton tglbtnNewToggleButton;
		JToggleButton tglbtnNewToggleButton_1;
		JToggleButton tglbtnAccolades;
        JToggleButton tglbtnMap;
		final ButtonGroup buttonGroupobj = new ButtonGroup();
		tglbtnNewToggleButton = new JToggleButton("Lifetime Totals   ");
		tglbtnNewToggleButton.setSelected(true);
		// show the lifetime total panel and hide the rest
		tglbtnNewToggleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				panelBestDays.setVisible(false);
				panelAccolades.setVisible(false);
				panelLifeTime.setVisible(true);
                panelMap.setVisible(false);

			}
		});
		panel_1.add(tglbtnNewToggleButton);
		tglbtnNewToggleButton.setBackground(new Color(55, 55, 55));
		tglbtnNewToggleButton.setOpaque(true);
		buttonGroupobj.add(tglbtnNewToggleButton);

		// add button Best days
		tglbtnNewToggleButton_1 = new JToggleButton("Best Days           ");
		tglbtnNewToggleButton_1.addActionListener(new ActionListener()
		{
			// show the best days panel and hide the rest
			public void actionPerformed(ActionEvent e)
			{

				panelBestDays.setVisible(true);
				panelAccolades.setVisible(true);
				panelLifeTime.setVisible(false);
                panelMap.setVisible(false);

			}
		});
		tglbtnNewToggleButton_1.setBackground(new Color(55, 55, 55));
		tglbtnNewToggleButton_1.setOpaque(true);
		panel_1.add(tglbtnNewToggleButton_1);
		buttonGroupobj.add(tglbtnNewToggleButton_1);

		// add button Accolades
		tglbtnAccolades = new JToggleButton("Accolades          ");
		panel_1.add(tglbtnAccolades);
		tglbtnAccolades.setBackground(new Color(55, 55, 55));
		// show the Accolades panel and hide the rest
		tglbtnAccolades.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				panelBestDays.setVisible(false);
				panelAccolades.setVisible(true);
				panelLifeTime.setVisible(false);
                panelMap.setVisible(false);

			}
		});
		tglbtnAccolades.setOpaque(true);
		buttonGroupobj.add(tglbtnAccolades);

        // add button Map
        tglbtnMap = new JToggleButton("Map                     ");
        panel_1.add(tglbtnMap);
        tglbtnMap.setBackground(new Color(55, 55, 55));
        // show the Accolades panel and hide the rest
        tglbtnMap.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {

                        panelBestDays.setVisible(false);
                        panelAccolades.setVisible(false);
                        panelLifeTime.setVisible(false);
                        panelMap.setVisible(true);

                    }
                });
        tglbtnMap.setOpaque(true);
        buttonGroupobj.add(tglbtnMap);

		
		// PLace the tab to the left
		tabbedPane.setTabPlacement(tabbedPane.LEFT);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		// Set the Attributes
		tabbedPane.setOpaque(true);

		// tabbedPane.setForeground(Color.WHITE); Discuss the colors we want with the tabs
		tabbedPane.setBackground(new Color(70, 70, 70));

		// Add the tabbed pane to this panel.
		this.add(tabbedPane);	

		tmp = true;


		refreshbutn.addActionListener(new ActionListener()
		{
			@SuppressWarnings({ "deprecation", "static-access" })
			public void actionPerformed(ActionEvent e)
			{
				//SwingUtilities.updateComponentTreeUI(heartRateFrame);
					 try {
						heartrate = fitbit.getHeartActivity(userDate[0], userDate[1], userDate[2]);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			
					 outOfRange = heartrate.getOutOfRange();
					 fatBurn = heartrate.getFatBurn();
					 cardio = heartrate.getCardio();
					 peak = heartrate.getPeak();
					 restHeartRate = heartrate.getRestHeartRate();

					// try {
						try {
							daily = fitbit.getDailyActivity(userDate[0], userDate[1], userDate[2]);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
					 
					 floors = daily.getFloors();
					 steps = daily.getSteps();
					 distance = daily.getDistance();
					 calories = daily.getCalories();
					 sedentaryMins = daily.getSedentaryMins();
					 lightActiveMins = daily.getLightActiveMins();
					 fairlyActiveMins = daily.getFairlyActiveMins();
					 veryActiveMins = daily.getVeryActiveMins();
					 activeMinGoals = daily.getActiveMinGoals();
					 caloriesOutGoals = daily.getCaloriesOutGoals();
					 distanceGoals = daily.getDistanceGoals();
					 floorGoals = daily.getFloorGoals();
					 stepGoals = daily.getStepGoals();

				

						JComponent panel1 = new JPanel();
						panel1.setLayout(new BorderLayout());

						// A top menu bar that appears when the user first uses the Dashboard Menu
						final JMenuBar desktopMenuBar = new JMenuBar();
						desktopMenuBar.setBackground(new Color(100, 100, 100));
						desktopMenuBar.setBorderPainted(false);

						// Add the menu bar
						panel1.add(desktopMenuBar, BorderLayout.NORTH);

						// Adding the JDesktopPane into the "Dashboard" Panel
						final JDesktopPane desktop = new JDesktopPane();
						desktop.setPreferredSize(new java.awt.Dimension(600, 400));
						/*
						 * //add a panel that we the elements are going to be added on final JPanel panelback1 = new JPanel();
						 * panelback1.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(35, 35, 35))); panelback1.setBackground(new
						 * Color(40, 40, 40)); panelback1.setForeground(new Color(40, 40, 40)); panelback1.setBounds(0, 0, 1128, 644);
						 * panel1.add(panelback1, BorderLayout.WEST); panelback1.setLayout(null);
						 */

						desktop.setBackground(new Color(40, 40, 40));

						/*
						 * Elements needed: Map HeartRate Zone Calories Burned Daily Activity Records // Sedentary Minutes //
						 */
						// Add the mapFrame one with Metric distance and one with imperial distance and set the imperial one to false

						/*
						 * final JInternalFrame mapFrameImperial = makeInternalFrame("Interactive Map", 400, 0, 200, 200, true, true, true);
						 * MapFrame mapContent2 = new MapFrame(bestDistanceImperialnum, bestDistanceDate, lifeDistanceImperial,"mile");
						 * mapFrameImperial.add( mapContent2); mapFrameImperial.setVisible(false); desktop.add( mapFrameImperial );
						 */

						// The Heart Rate Zone element
						final JInternalFrame heartRateFrame = makeInternalFrame("Heart Rate Zone", 720, 200, 485, 355, true, true,
								true);
						HeartRateZoneFrame heartRateContent = new HeartRateZoneFrame(outOfRange, fatBurn, cardio, peak, restHeartRate);
						heartRateFrame.add(heartRateContent);
						desktop.add(heartRateFrame);

						// The Calories Burne Element
						final JInternalFrame calBurnFrame = makeInternalFrame("Calories Burned", 493, 0, 243, 520, true, true, true);
						CaloriesBurnedFrame calBurnContent = new CaloriesBurnedFrame(calories, caloriesOutGoals);
						calBurnFrame.add(calBurnContent);
						desktop.add(calBurnFrame);

						// The Active Minutes element
						final JInternalFrame activeMinFrame = makeInternalFrame("Daily Goals", 0, 0, 510, 520, true, true, true);

						ActiveMinutesFrame activeMinContent = new ActiveMinutesFrame(lightActiveMins, fairlyActiveMins, veryActiveMins,
								activeMinGoals, floors, steps, distance, floorGoals, stepGoals, distanceGoals);

						activeMinFrame.add(activeMinContent);
						desktop.add(activeMinFrame);

						// Create the UserInput Text Box
					
					

						// The Sedentary Minutes element
						final JInternalFrame sedMinFrame = makeInternalFrame("Sedentary Minutes", 720, 0, 475, 210, true, true, true);
						SedentaryMinutesFrame sedMinContent = new SedentaryMinutesFrame(sedentaryMins);
						sedMinFrame.add(sedMinContent);
						desktop.add(sedMinFrame);

						panel1.add(desktop);

						// add the the panel to the tabbed pane
						tabbedPane.addTab(getUserDateString(), null, panel1, "tmp1"); // Add the desktop pane to the tabbedPane
						tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
						tabbedPane.setBackgroundAt(0, Color.WHITE);
						time = new JLabel(" " + new Date());

//						System.out.println(getUserDateString());
			

						
					 
				
				final Date date = new Date();
				int day = date.getDay();
				int hours = date.getHours();
				if (hours > 12 && tmp == true)
				{
					date.setHours(hours - 12);
				}
				time.setText("" + date);

				
				desktopMenuBar.add(time);

			}
		});
	}
    
    /**
     * This method saves user settings when the parent frame (MainWindow) is cloed
     * @throws Exception as this method performs IO operations
     */    
	public void onCloseAction() throws Exception{
		//When the parent frame (MainWindow) is closed, this method will be executed to save user setting
		this.updatePointArray(this.heartRateFrame, this.calBurnFrame, this.activeMinFrame, this.sedMinFrame);
		
		userSettings.setPointArray(this.getPointArray());
		System.out.println(this.getPointArray()[0] + " close point 0");
		objSerial.storeUserSettings(userSettings);
		//hopefully this works
	}
    
    /**
     * This method returns the user settings
     * @return UserSettings object
     */
	public UserSettings getUserSettings() {
		return userSettings;
	}
    
    /**
     * This method sets the user settings object to this class
     * @param userSettings UserSettings object
     */
	public void setUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}
    
    /**
     * This method returns an ObjectSerialization object
     * @return ObjectSerialization object
     */
	public ObjectSerialization getObjSerial() {
		return objSerial;
	}
    
    /**
     * This method sets an ObjectSerialization object to this class
     * @param objSerial ObjectSerialization object
     */
	public void setObjSerial(ObjectSerialization objSerial) {
		this.objSerial = objSerial;
	}

	/**
	 * a method that will create panel
	 * @param text String that contains panel content
	 * @return JPanel panel
	 */
	protected JComponent makeTextPanel(String text)
	{
		JPanel panel = new JPanel(false);
		panel.setBackground(new Color(55, 55, 55));
		panel.setLayout(null);
		return panel;
	}

	/**
	 * A Mutator method for the DateSetting instance Variable. This will be used by the JComboBox under settings for the user
	 * to change their preferred date format. Might add more date formats in the future
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
	 * Set the user date from the JFormattedTextField.
	 * This sets the String array's 3 respective elements
	 * 
	 * @param userInput JFormattedTextField object
	 */
	public void setUserDate(JFormattedTextField userInput) {
		String in = userInput.getText();
		if (in == null) {
            Date date = new Date();
            this.userDate[0] = String.valueOf(date.getYear());
            this.userDate[1] = String.valueOf(date.getMonth());
            this.userDate[2] = String.valueOf(date.getDay());
        }
		else {
			this.userDate[0] = in.substring(0, 3);
			this.userDate[1] = in.substring(6, 7);
			this.userDate[2] = in.substring(8, 9);
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
     * This methods sets the date from a correctly formatted string
     * @param dateFormat String containg the formatted date ("dd/mm/yyyy")
     */
	private void setDateSetting(String dateFormat)
	{

		if (dateFormat.equals("dd/mm/yyyy"))
		{
			this.dateSetting = 0;
		}
		else
		{
			this.dateSetting = 1;
		}
	}
    
    /**
     * This method creates a formatter from a string and returns a MaskFormatter object
     * @param s String
     * @return MaskFormatter object
     */
	protected MaskFormatter createFormatter(String s)
	{
		MaskFormatter formatter = null;
		try
		{
			formatter = new MaskFormatter(s);
		}
		catch (java.text.ParseException exc)
		{
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}
    
    /**
     * This method sets an array of point objects
     * @param heartPoint Point object
     * @param calPoint Point object
     * @param activePoint Point object
     * @param sedPoint Point object
     */
	private void setPointArray(Point heartPoint, Point calPoint, Point activePoint, Point sedPoint)
	{
		this.pointArray[1] = heartPoint;
		this.pointArray[2] = calPoint;
		this.pointArray[3] = activePoint;
		this.pointArray[4] = sedPoint;
		
	}
    
    /**
     * This method returns a Point array
     * @return Array of point objects
     */
	private Point[] getPointArray()
	{
		return this.pointArray; // This should probably be a copy, to maintain security (?)
	}
	
    /**
     * This method updates the point array
     * @param heart JInternalFrame
     * @param cal JInternalFrame
     * @param min JInternalFrame
     * @param sed JInternalFrame
     */
	private void updatePointArray(
			JInternalFrame heart, JInternalFrame cal, JInternalFrame min, JInternalFrame sed)
	{
		
		Point heartRateFramePoint = heart.getLocation();
		Point calBurnFramePoint = cal.getLocation();
		Point activeMinFramePoint = min.getLocation();
		Point sedMinFramePoint = sed.getLocation();
		
		this.setPointArray(heartRateFramePoint, calBurnFramePoint, activeMinFramePoint, sedMinFramePoint);
		
	}
	/**
	 * A Method that will make the JInternalFrames (ie- the elements in the Dashboard) The parameters will dictate that
	 * frames initial values, who will change as the navigates the app.
	 * @param title String that contains frame title
	 * @param locationX integer of x coordinate of panel on screen
	 * @param locationY integer of y coordinate of panel on screen
	 * @param sizeX integer of panel width
	 * @param sizeY integer of panel height
	 * @param boolVisible boolean that makes panel visible if true
	 * @param boolResize boolean that resizes panel if true
	 * @param boolIcon Whether or not it can be set to the bottom of the screen
	 * @return JInternalFrame iFrame
	 */
	private JInternalFrame makeInternalFrame(String title, int locationX, int locationY, int sizeX, int sizeY,
			boolean boolVisible, boolean boolResize, boolean boolIcon)
	{
		JInternalFrame iFrame = new JInternalFrame(title);
		iFrame.setLocation(locationX, locationY);
		iFrame.setSize(sizeX, sizeY);
		iFrame.setVisible(boolVisible);
		iFrame.setResizable(boolResize);
		iFrame.setIconifiable(boolIcon);

		return iFrame;
	}

    /**
     * Method returns a map image with a given zoom level. 
     * @param mapZoomLevel integer that ranges from 1-8, containing the zoom level
     * @param map Map object that is passed into method
     * @return ImageIcon containing the map image
     */
    private ImageIcon displayMap(int mapZoomLevel, Map map) {
        try {
            ImageIcon mapImage = new ImageIcon((new ImageIcon(map.getMap(mapZoomLevel))).getImage().getScaledInstance(700,450,java.awt.Image.SCALE_SMOOTH));
            return mapImage; 

        } catch(Exception e) {
            return null; 
        }
    }
}
