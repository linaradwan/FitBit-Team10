package ca.uwo.csd.cs2212.team10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

/**
	 * A new class used to paint the progress bar. 
	 * This simply takes an angle needed, and a color to paint.
	 * 
	 * @author Gustavo
	 */
	public class ProgressBar extends JComponent {
		Ellipse2D.Double fill;
		Area inside;
		int x;
		int y;
		int width = 100;
		int height = 100;
		int startAngle = 0; 
		int arcAngle = 270;
		Color colour;

		/**
		 * ProgressBar constructor. Declare the starting (x,y) coordinates and the color
		 * Default width and height for the ProgressBars are 100x100
		 * 
		 * @param startX Integer
		 * @param startY Integer
		 * @param colourIn Color object
		 */
		public ProgressBar(int startX, int startY, Color colourIn) {
			//When initializing the progress bar, we will also initialize the bars position and color
			this.x = startX;
			this.y = startY;
			this.colour = colourIn;
		}

		/**
		 * Set the Arc Angle of the ProgressBar.
		 * @param angle Integer that contains arc angle
		 */
		public void setArcAngle(int angle) {
			this.arcAngle = angle;
		}

		@Override
        /**
         * This method paints the progress bar
         * @param g Graphics object
         */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//Graphics2D g2 = (Graphics2D) g;
			g.setColor(colour);

			//Fill the progress bar
			g.fillArc(this.x, this.y, width, height, startAngle+270, arcAngle);
			g.setColor(this.getBackground());
			g.fillArc(x+10, y+10, width-20, height-20, startAngle, 360);
		}
	}
