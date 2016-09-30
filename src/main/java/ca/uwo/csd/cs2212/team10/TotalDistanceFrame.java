package ca.uwo.csd.cs2212.team10;

import javax.swing.*;

import java.awt.*;

/**
 * This class represents the information for the Dashboard element 'Total Distance Count Frame'
 * This will house all the data (as parameters in the constructor) and the graphical elements.
 * As of Stage 2, only data is being supplied
 * 
 * @author UI Team
 */
public class TotalDistanceFrame extends JPanel {

    /**
     * TotalDistanceFrame constructor
     * @param floors integer that contains total floor count
     * @param steps integer that contains total step count
     * @param distance double float that contains total distance
     */
	public TotalDistanceFrame(int floors, int steps, double distance) {
		// Change GridLayout to better organize the panel
		super(new GridLayout(1, 1));
		
		// JLabels to print the text for the testFitBitAPI
		JLabel lblName = new JLabel("<html><i>Floors:</i> " + floors + 
				".<br><i>Steps:</i> " + steps + 
				"<br><i>Distance:</i> " + distance + "</html>",
				JLabel.CENTER);
		
		lblName.setOpaque(false);	
		lblName.setToolTipText("tmp");
		this.setBackground(new Color(150, 150, 150));
		
		
		//this.add(content);
		this.add(lblName);
	}

}
