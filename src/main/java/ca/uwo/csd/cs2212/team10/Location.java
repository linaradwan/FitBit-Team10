package ca.uwo.csd.cs2212.team10;

import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class implements the location object, containing the name, coordinates, distance, and whether the location has been reached. It also contains the location of the image file of the location. 
 * @author Conor 
 */
public class Location {

    private String name;
    private String coordinates;
    private double distance;
    private boolean achieved = false;
    private String imageFile;
    
    /**
     * First constructor sets the name and location
     * @param name String that contains the name of the location
     * @param coordinates String that contains the coordinates of the location
     */
    public Location(String name, String coordinates) {
        this.name = name; 
        this.coordinates = coordinates;
    }

    /**
     * Second constructor sets the name and location, distance and whether the location had been achieved
     * @param name String that contains the name of the location
     * @param coordinates String that contains the coordinates of the location
     * @param distance double value that contains the distance
     * @param achieved boolean true or false that contains whether the location has been reached
     */
    public Location(String name, String coordinates, double distance, boolean achieved) {
        this.name = name;
        this.coordinates = coordinates;
        this.distance = distance; 
        this.achieved = achieved;
    }
    
    /**
     * Method that retrieves the path of the location image
     * @return String of the path of the image
     * @throws IOException Method performs IO operations
     */
    public String getLocationImage() throws IOException {

        this.imageFile = "src/main/resources/locationImages/" + this.coordinates + "_mapSearch.png";
        if(!(new File(imageFile).exists())) {
            BufferedImage image = null;
                String imageURL = "https://maps.googleapis.com/maps/api/staticmap?size=300x300&center=" + this.name.replaceAll("\\s","%20") + "&key=AIzaSyA3qYxpHJKnTbHfW1oRcCSpycKqKUvwvV0";
                URL url = new URL(imageURL);
                image = ImageIO.read(url);
                ImageIO.write(image,"png",new File(imageFile));
        }
        return imageFile;
    }
    
    /**
     * Method returns the name of the location
     * @return String that contains location name
     */
    public String getName() {
        return this.name;
    }
    /**
     * Method returns the location coordinates
     * @return String that contains the location coordinates
     */
    public String getCoordinates() {
        return this.coordinates;
    }
    /**
     * Method returns the distance
     * @return double value that contains the distance to the location
     */
    public double getDistance() {
        return this.distance;
    }
    /**
     * Method returns boolean value of whether the location has been reached
     * @return true or false if the location has been reached
     */
    public boolean getAchieved() {
        return this.achieved;
    }
    
    /**
     * Method sets the name of the location
     * @param name String that contains the location name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method sets the location coordinates
     * @param coordinates String that contains the location coordinates
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    /**
     * Method sets distance to location
     * @param distance double value that contains distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
    /**
     * Method sets true or false if the location has been achieved
     * @param achieved boolean true or false
     */
    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }
}
