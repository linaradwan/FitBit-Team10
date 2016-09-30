package ca.uwo.csd.cs2212.team10;

import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter; 
import java.lang.StringBuffer; 
import java.lang.Math.*;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList; 

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 


import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class implements the backend of the map functionality. 
 *
 * @author Conor
 */
public class Map {

    public Location[] locations; 
    private Location currentLocation;
    private boolean locationSet; 
    private Fitbit fitbit;
    private double totalDistance;  

    /** 
     * Main constructor reads from locations.json into an array of location objects
     * @param fitbit Fibit object
     * @throws IOException can be thrown by opening locations.json
     * @throws JSONException Method uses JSON objects which can throw this error
     */
    public Map(Fitbit fitbit) throws IOException, JSONException {
        this.fitbit = fitbit;

        // get JSONObject from locations.json
        JSONObject object = readFromJSONFile();


        // Get current location
        JSONObject currentLoc = object.getJSONObject("currentLocation");
        this.currentLocation = new Location(currentLoc.getString("name"),currentLoc.getString("coordinates"));
        if(this.currentLocation.getName().equals("")) this.locationSet = false;
        else this.locationSet = false;

        // Get all countries
        JSONArray cities  = object.getJSONArray("locations");

        List<Location> places = new ArrayList<Location>();
        JSONObject city;
        for(int i = 0; i < cities.length(); i++) {
            city = cities.getJSONObject(i);
            if(this.locationSet) places.add(new Location(city.getString("name"),city.getString("coordinates"), city.getDouble("distance"),city.getBoolean("achieved")));
            else places.add(new Location(city.getString("name"), city.getString("coordinates")));
        }

        this.locations = places.toArray(new Location[places.size()]);
    }

    /**
     * Method reads from locations.json and returns a JSONObject of the file
     * @return JSONOObject containing content from locations.json
     * @throws IOException method performs IO operations
     * @throws JSONException method uses JSON objects
     */
    private JSONObject readFromJSONFile() throws IOException, JSONException { 
        // read from locations.json into json string
        String jsonString = ""; 
        BufferedReader file = new BufferedReader(new FileReader("src/main/resources/locations.json"));
        String line;
        while((line = file.readLine())!=null) jsonString = jsonString + line;
        file.close();
        return new JSONObject(jsonString);
    }

    /**
     * Method writes array of location objects into locations.json in the JSON format
     * @throws IOException method performs IO operations
     */
    public void writeToJSONFile() throws IOException {
        String jsonString = ""; 
        jsonString = jsonString + "{\n\t\"currentLocation\" : { \n\t\t\t\"name\" : \"" + this.currentLocation.getName() + "\",\"coordinates\" : \"" + this.currentLocation.getCoordinates() + "\" \n\t},\n\t\"locations\" : [";
        for(int i = 0; i < this.locations.length - 1; i++) {
            jsonString = jsonString + "\n\t\t{\n\t\t\t\"name\" : \"" + this.locations[i].getName() + "\",\"coordinates\" : \"" + this.locations[i].getCoordinates() +
                "\",\"distance\" : \"" + this.locations[i].getDistance() + "\",\"achieved\" : \"false\"\n\t\t},";
        }
        jsonString = jsonString + "\n\t\t{\n\t\t\t\"name\" : \"" + this.locations[locations.length - 1].getName() + "\",\"coordinates\" : \"" + this.locations[locations.length - 1].getCoordinates() +
            "\",\"distance\" : \"" + this.locations[locations.length - 1].getDistance() + "\",\"achieved\" : \"false\"\n\t\t}\n\t]\n}";

        BufferedWriter file = new BufferedWriter(new FileWriter("src/main/resources/locations.json"));
        file.write(jsonString);
        file.close(); 
    }

    /**
     * Method simply converts degrees into radians
     * @param deg double radian value
     * @return double value in radians
     */
    private double degToRad(double deg) {
        return deg*(Math.PI/180.0);
    }


    /**
     * Method uses the current location coordinates and coordinates from locations to calculate the distances between the coordinates.
     */
    public void calculateDistances() {

        double currentLat = Double.parseDouble(currentLocation.getCoordinates().substring(0,currentLocation.getCoordinates().indexOf(',')));
        double currentLng = Double.parseDouble(currentLocation.getCoordinates().substring(currentLocation.getCoordinates().indexOf(',')+1,currentLocation.getCoordinates().length()));

        double lat;
        double lng;
        double dLat;
        double dLng;
        double R = 6371; // radius of the Earth in km
        double a;
        double c;
        double d;
        for (int i = 0; i < locations.length; i++) {

            lat = Double.parseDouble(locations[i].getCoordinates().substring(0,locations[i].getCoordinates().indexOf(',')));
            lng = Double.parseDouble(locations[i].getCoordinates().substring(locations[i].getCoordinates().indexOf(',')+1,locations[i].getCoordinates().length()));
            dLat = degToRad(lat-currentLat);
            dLng = degToRad(lng-currentLng);

            a = Math.sin(dLat/2.0) * Math.sin(dLat/2.0) +  Math.cos(degToRad(currentLat)) * Math.cos(degToRad(lat)) * Math.sin(dLng/2.0) * Math.sin(dLng/2.0);
            c = Math.atan2(Math.sqrt(a),Math.sqrt(1.0-a));
            d = (R * c)*1.852;
            locations[i].setDistance(d);
        }
    }

    /**
     * Method gets lifetime distance from fitbit API
     * @return double that contains the life time distance
     */
    private double getLifeDistance() {
        try {
            this.totalDistance = fitbit.getBestLifeActivity().getLifeDistance();
            return totalDistance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * Method returns data gathered from URL
     * @param requestURL String containing the URL
     * @return String that contains data from the URL
     * @throws IOException as this method performs IO operations
     */
    private String readURL(String requestURL) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(requestURL);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    /**
     * Method gets Location object using given place name using the google geocoding API
     * @param locationName String that contains the place name
     * @return Location object that contains the place name and coordinates
     * @throws JSONException Method uses JSON objects that can throw this error
     * @throws IOException Method uses location.getLocationImage, which performs IO operations
     */
    public Location getNewLocation(String locationName) throws JSONException, IOException {
        String formattedAddress = "";
        String coords = "";
        String requestURL = "https://maps.googleapis.com/maps/api/geocode/json?address=" + locationName.replaceAll("\\s","%20") + "&key=AIzaSyA3qYxpHJKnTbHfW1oRcCSpycKqKUvwvV0";
            
        JSONObject object = new JSONObject(readURL(requestURL));

            JSONArray resultsArray = object.getJSONArray("results");
            JSONObject results = resultsArray.getJSONObject(0); 
            formattedAddress = results.getString("formatted_address");

            JSONObject geometry = results.getJSONObject("geometry");
            JSONObject locationCoordinates = geometry.getJSONObject("location");
            coords = locationCoordinates.getString("lat") + "," + locationCoordinates.getString("lng");

        return new Location(formattedAddress,coords); 
    }
    
    /**
     * Method sets the given location as the current locations
     * @param currentLocation Location object containing the given current location
     * @throws IOException from writeToJSONFile method that uses IO
     */
    public void setCurrentLocation(Location currentLocation) throws IOException {
        this.currentLocation = currentLocation;
        this.locationSet = true;
        this.currentLocation.getLocationImage(); 
        calculateDistances();
        for(int i = 0; i < this.locations.length; i++) {
            this.locations[i].setAchieved(false);
        }
        writeToJSONFile(); 
    }
    
    /**
     * Method retrieves a map containing markers of all the cities that have been reached
     * @param zoom Integer value that will be set from 1-8. 
     * @return String that contains the file path of the map
     * @throws IOException Method uses IO to save image
     */
    public String getMap(int zoom) throws IOException { 
        // Get large map from API call, return path to image

        String imageFile = "src/main/resources/locationImages/map.png";
        String markers = "";

        refreshMap();

        int lat;
        int lng;

        for(int i =0; i < this.locations.length; i++) {
            if(this.locations[i].getAchieved()) {


                /*lat = (int)Double.parseDouble(locations[i].getCoordinates().substring(0,locations[i].getCoordinates().indexOf(',')));
                lng = (int)Double.parseDouble(locations[i].getCoordinates().substring(locations[i].getCoordinates().indexOf(',')+1,locations[i].getCoordinates().length()));
                markers = markers + lat + "," + lng + "%7C";*/
                markers = markers + this.locations[i].getCoordinates() + "%7C";
            }
        }

        String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=" + this.currentLocation.getName().replaceAll("\\s","%20")  + "&zoom=" + zoom + "&size=600x400&scale=2&markers=size:mid%7Ccolor:red%7C" + markers + "&key=AIzaSyA3qYxpHJKnTbHfW1oRcCSpycKqKUvwvV0";

        //System.out.println(imageURL.length());
        //System.out.println(imageURL.substring(0,imageURL.length()-44));

        BufferedImage image = null;
        URL url = new URL(imageURL);
        image = ImageIO.read(url);                
        ImageIO.write(image,"png",new File(imageFile));

        return imageFile;
    }
    
    /**
     * Method checks each location compared to the life distance and sets the location as achieved if the life distance
     * is greater than the distance to the location.
     */
    public void refreshMap() {
        double lifeDistance = getLifeDistance();

        for(int i = 0; i < this.locations.length; i++) {
            if(lifeDistance >= this.locations[i].getDistance() || this.locations[i].getAchieved()) {
                this.locations[i].setAchieved(true); 
            }
        }
    }

    /**
     * Method returf (dateFormat.equals("dd/mm/yyyy"))
     * 1450         {
     * 1451             this.dateSetting = 0;
     * 1452         }
     * 1453         else
     * 1454         {
     * 1455             this.dateSetting = 1;
     * 1456         }
     * 1457     }
     * 1458 
     * 1459     /**
     * 1460      * This method creates a formatter from a string
     * 1461      * @param s 
     * 1462     protected MaskFormatter createFormatter(String s)
     * 1463     {
     * 1464         MaskFormatter formatter = null;
     * 1465         try
     * 1466         {
     * 1467             formatter = new MaskFormatter(s);
     * 1468         }
     * 1469         catch (java.text.ParseException exc)
     * 1470         {
     * 1471             System.err.println("formatter is bad: " + exc.getMessage());
     * 1472             System.exit(-1);
     * 1473         }
     * 1474         return formatter;
     * 1475     }
     * ns an string of locations that have been achieved
     * @return String of achieved Location object sorted by increasing distance
     */
    public String getAchievedLocations() {
        List<Location> places = new ArrayList<Location>();


        for(int i = 0; i < this.locations.length; i++) {
            if(this.locations[i].getAchieved()) {
                places.add(this.locations[i]); 
            }
        }
        Location[] result = places.toArray(new Location[places.size()]);
        Location temp; 
        for (int i = 0; i < result.length-1; i ++) {
            if(result[i].getDistance() > result[i+1].getDistance()) {
                temp = result[i];
                result[i] = result[i+1];
                result[i+1] = temp;
                i = -1;
            }
        }
        String list = "Current Location: \n " + this.currentLocation.getName() + "\n\nLifetime Distance: " + (int)this.totalDistance + " km\n\nPlaces marked on the map:\n\n--------------------------------";
        for (int i = 0; i < result.length; i++) {
                list = list + "\n\n " + result[i].getName() + "\n  Distance: " + (int)result[i].getDistance() + " km";
        }
        return list;
    }

    /**
     * Method returns true or false if the current location has been set
     * @return boolean true or false if current location is set
     */
    public boolean getLocationSet() {
        return this.locationSet; 
    }

    /**
     * Method returns current Location object
     * @return current Location object
     */
    public Location getCurrentLocation() {
        return this.currentLocation;
    }
}
