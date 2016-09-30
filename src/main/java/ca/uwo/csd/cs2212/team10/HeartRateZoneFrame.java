package ca.uwo.csd.cs2212.team10;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Font;
import javax.swing.JToolTip;


/**
 * This class represents the information for the Dashboard element 'Heart Rate Zone'
 * This will house all the data (as parameters in the constructor) and the graphical elements.
 * As of Stage 2, only data is being supplied
 * 
 * @author Gustavo Murcia
 */
public class HeartRateZoneFrame extends JPanel {

	/**
	 * The constructor for the Heart Rate Zone Frame. 
	 * The parameters represent information coming from the API
	 * 
	 * @param outOfRange integer that specifies the index of out of range
     * @param fatBurn integer that contains fat burn heart rate
	 * @param cardio integer that contains cardio heart rate
	 * @param peak integer that contains peak heart raate
	 * @param restHeartRate integer that contains the resting heart rate
	 */
	public HeartRateZoneFrame(int outOfRange,int fatBurn, int cardio, int peak, int restHeartRate) {
		setLayout(null);

		// JLabels to print the text for the testFitBitAPI
		String stringLabel = "<html>" +  
				"<br>Fat Burn: " + fatBurn + 
				"<br>Cardio: " + cardio +
				"<br>Peak: " + peak + 
				"<br>OutOfRange: " + outOfRange + 
				"</html>";
		//JFreeChart PieChart;
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("Fat Burn", fatBurn);
		dataSet.setValue("Cardio", cardio);
		dataSet.setValue("Peak", peak);
		dataSet.setValue("OutOfRange", outOfRange);
		JFreeChart PieChart= ChartFactory.createPieChart("", dataSet,true, true, false);
		JLabel lblName = new JLabel(stringLabel, JLabel.CENTER);
		ChartPanel CP= new ChartPanel(PieChart);
		CP.setBounds(41, 33, 391, 192);
		lblName.setBounds(34, 225, 131, 89);
		
		lblName.setOpaque(false);	
		lblName.setToolTipText("tmp");
		this.setBackground(new Color(155, 155, 155));
		//this.add(content);
		this.add(lblName);
		this.add(CP);
		
		JLabel lblRestingHeartRate = new JLabel("Resting Heart Rate");
		lblRestingHeartRate.setFont(new Font("SimSun", Font.PLAIN, 15));
		lblRestingHeartRate.setBounds(43, 3, 192, 33);
		add(lblRestingHeartRate);
		
		JLabel label = new JLabel(" "+restHeartRate+"bpm");
		label.setFont(new Font("SimSun", Font.PLAIN, 16));
		label.setBounds(391, 2, 111, 33);
		add(label);
				
		JLabel lblPeakZone = new JLabel("Peak Zone Description");
		lblPeakZone.setBounds(237, 278, 161, 15);
		add(lblPeakZone);
		lblPeakZone.setToolTipText("<html>Peak zone, which means your heart rate is greater than 85% of maximum,<br> is the high-intensity exercise zone. The peak zone is for short intense sessions<br> that improve performance and speed. </html>");
		
		JLabel lblCardioZone = new JLabel("Cardio Zone Description");
		lblCardioZone.setBounds(237, 261, 161, 15);
		add(lblCardioZone);
		lblCardioZone.setToolTipText("<html>Cardio zone, which means your heart rate is 70 to 84% of maximum, <br>is the medium-to-high intensity exercise zone. In this zone, you're pushing yourself<br> but not straining. For most people, this is the exercise zone to target. </html>");
		
		JLabel lblFatBurnZone = new JLabel("Fat Burn Zone Description");
		lblFatBurnZone.setBounds(237, 242, 183, 15);
		add(lblFatBurnZone);
		lblFatBurnZone.setToolTipText("<html>Fat burn zone, which means your heart rate is 50 to 69% of maximum, <br>is the low-to-medium intensity exercise zone and may be a good place to start for those new to exercise.<br> It's called the fat burn zone because a higher percentage of calories <br>are burned from fat but the total calorie burn rate is lower. </html>");
		
		JLabel lblOutOfZone = new JLabel("Out Of Zone Description");
		lblOutOfZone.setBounds(237, 294, 161, 15);
		add(lblOutOfZone);
		lblOutOfZone.setToolTipText("<html>When you're out of zone, which means your heart rate is below 50% of maximum,<br> your heart rate may still be elevated but not enough to be considered exercise.</html>");
		
		
	}
}
