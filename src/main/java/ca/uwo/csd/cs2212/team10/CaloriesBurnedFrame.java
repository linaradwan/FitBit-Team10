package ca.uwo.csd.cs2212.team10;

import javax.swing.*;

import java.awt.*;

/**
 * This class represents the information for the Dashboard element 'Calories Burned'
 * This will house all the data (as parameters in the constructor) and the graphical elements.
 * As of Stage 2, only data is being supplied
 * 
 * @author Gustavo Murcia
 */
public class CaloriesBurnedFrame extends JPanel {

    /**
     * The constructor for the Calories Burned Frame. 
     * The parameters represent information coming from the API
     * 
     * @param calories integer containing calorie usage count
     * @param caloriesOutGoals integer containing calorie usage goal
     */
    public CaloriesBurnedFrame(int calories, int caloriesOutGoals) {
        this.setLayout(null);
        String stringLabel;

        double colriesArc = (calories)/(double)caloriesOutGoals;
        int angleProgressCalories = (int)((colriesArc)*360);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("src/main/resources/Flame.png"));
        lblNewLabel.setBounds(57, 36, 82, 85);
        add(lblNewLabel);
        // Create the progress bar
        ProgressBar progBar1 = new ProgressBar(0, 0,  Color.RED);
        progBar1.setArcAngle(angleProgressCalories);
        progBar1.setBounds(47,28, 130, 104);

        this.add(progBar1);


        int caloriesLeft= caloriesOutGoals-calories;
        if(caloriesLeft<0){
            // JLabels to print the text for the testFitBitAPI
            stringLabel = "<html>" +  "<br> You are above your goal by "+ -caloriesLeft+
                "<br>Calories: " + calories + " cal"+
                "<br>Calories (Goals): " + caloriesOutGoals + " cal"+
                "</html>";
        }
        else if(caloriesLeft>0){
            stringLabel = "<html>" +  "<br> You are below your goal by "+ caloriesLeft+
                "<br> Calories: " + calories + 
                "<br> Calories (Goals): " + caloriesOutGoals + 
                "</html>";

        }
        else{ 
            stringLabel = "<html>" +  "<br> You have met your goal"+
                "<br>Calories: " + calories+ "cal"+ 
                "<br>Calories (Goals): " + caloriesOutGoals + "cal"+
                "</html>";

        }
        JLabel lblName = new JLabel(stringLabel);
        lblName.setBounds(7, 96, 273, 114);


        lblName.setOpaque(false);	
        lblName.setToolTipText("Calories Burned");


        this.setBackground(new Color(155, 155, 155));

        this.add(lblName);
    }
}
