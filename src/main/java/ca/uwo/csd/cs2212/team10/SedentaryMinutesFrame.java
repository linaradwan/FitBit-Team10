package ca.uwo.csd.cs2212.team10;

import javax.swing.*;

import java.awt.*;

/**
 * This class represents the information for the Dashboard element 'Sedentary Minutes Frame'
 * This will house all the data (as parameters in the constructor) and the graphical elements.
 * As of Stage 2, only data is being supplied
 * 
 * @author Gustavo Murcia
 */
public class SedentaryMinutesFrame extends JPanel {

	/**
	 * The constructor for the Sedentary Minutes Frame. 
	 * The parameters represent information coming from the API
	 * 
	 * @param sedentaryMins integer that contains sedentary minutes
	 */
	public SedentaryMinutesFrame(int sedentaryMins) {
		// Change GridLayout to better organize the panel
		super(new GridLayout(1, 1));
		this.setLayout(null);

		// JLabels to print the text for the testFitBitAPI
		String stringLabel = "<html>" +
				"<br>Sedentary Minutes: " + sedentaryMins +  " min"+
				"</html>";
		JLabel lblName = new JLabel(stringLabel);
		lblName.setBounds(135, 60, 210, 160);

		
		lblName.setOpaque(false);	
		lblName.setToolTipText("tmp");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/main/resources/rsz_sedmin.png"));
		lblNewLabel.setBounds(135,0, 198, 160);
		
		
		this.setBackground(new Color(155, 155, 155));
		this.add(lblNewLabel);
		
		//this.add(content);
		this.add(lblName);
	}
}
