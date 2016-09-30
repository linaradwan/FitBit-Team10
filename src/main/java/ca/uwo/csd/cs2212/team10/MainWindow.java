package ca.uwo.csd.cs2212.team10;

import java.awt.event.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import org.json.JSONException;
/**
 * A class that creates the main UI windows 
 * It calls out the MainTabWindow that has all the panels 
 * CS 2212 FitBit
 * @author UI Team
 *
 */
public class MainWindow extends JFrame{

	/**
	 * MainWindow Constructor
     * @param fitbit Fitbit object
	 * @throws Exception handled by App
	 */
	public MainWindow(Fitbit fitbit) throws Exception {
		this.initUI(fitbit);
	}

	/**
	 * Method initialises the UI
     * @param fitbit Fitbit object
	 * @throws Exception handled by Constructor
	 */
	private void initUI(Fitbit fitbit ) throws Exception {
		// Create and set up the window with its initial attributes.
		this.setTitle("Fit Bit");
		this.setSize(1230, 685); // The screen size should be 16x9; We use a scale factor of 75
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(55, 55, 55));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Add/Create the Menu Bar using the createMenuBar Method
		//this.setJMenuBar(this.createMenubar(mainTabWindow));

		////////////////////////
		// Create the Main Tab Window. This JPanel will be used to navigate through the window
		final MainTabWindow mainTabWindow = new MainTabWindow(fitbit);
		getContentPane().add(mainTabWindow, BorderLayout.CENTER);
		
		/*
		System.out.println("TEST_TEST_TEST_TEST");
		System.out.println(fitbit.getError()      );
		System.out.println(fitbit.getRateError()  );
		System.out.println(fitbit.getTokensError());
		*/
		//// Create the UIErrror Handling Panel.
		UIErrorHandle uiError = new UIErrorHandle(fitbit.getError(), fitbit.getRateError(), fitbit.getTokensError());
		//Add it to the bottom to see how it works
		this.add(uiError, BorderLayout.SOUTH);
    
    	////////////////////////
		// Create the Top Menu Bar and set its attributes
		MyMenuBar topMenubar = new MyMenuBar();
		topMenubar.setBackground(new Color(87, 87, 87));
		topMenubar.setPreferredSize(new Dimension(1200, 70)); // height

		// Create the 'User Name' menu item. Here the user will be able to exit or logout (expand options in the future)
		JMenu mnuUserName = new JMenu("Window");
		mnuUserName.setForeground(Color.WHITE);
		mnuUserName.setBackground(Color.WHITE);
		mnuUserName.setMnemonic(KeyEvent.VK_F);
		/*
				// Under 'User Name': Logout
				//	this lets the user logout. Takes them to the Sign in page
				JMenuItem mniUserNameLogout = new JMenuItem("Log Out");
				mniUserNameLogout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Will be linked to the sign in page in the future
					}
				});
		 */
		//..
		// Also under 'User Name': Exit
		//	This lets the user exit the program entirely. (implement the exiting also singing them out and saving settings)
		JMenuItem mniUserNameExit = new JMenuItem("Exit");
		mniUserNameExit.setMnemonic(KeyEvent.VK_E);
		mniUserNameExit.setToolTipText("Exit & Save");
		mniUserNameExit.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent event) {
				//When the user exits from the 'window' menu item, we will save the elements of MainTabWindow
				try {
					mainTabWindow.onCloseAction();
				}
				catch (Exception e) {
					System.out.println("There was an error with onCloseAction()");
				}

				System.exit(0); }
		});

		// Add the sub-menu items to the dropdown menu
		//mnuUserName.add(mniUserNameLogout);	// implement in the future
		mnuUserName.add(mniUserNameExit);

		// Add all the created elements to their respective containers
		topMenubar.add(Box.createHorizontalGlue()); // This spaces out the menu item so it's to the left
		JLabel lblNewLabel = new JLabel("");
		ImageIcon img0=new ImageIcon("src/main/resources/fitbitlogo.png");
		lblNewLabel.setIcon(img0);
		topMenubar.add(lblNewLabel);
		topMenubar.add(Box.createHorizontalGlue()); // This spaces out the menu item so it's to the left
		topMenubar.add(mnuUserName);

		this.setJMenuBar(topMenubar);
		////////////////////////

		//Add the Main Tab Window to the MainWindow
		getContentPane().add(mainTabWindow, BorderLayout.CENTER);
	}

	/**
	 * Method creates menu bar displayed on top of the screen
	 * @return JMenuBar topMenuBar
	 */
	
	////////////////////////////////////////////////////////////
}
