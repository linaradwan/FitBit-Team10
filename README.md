# Fitbit App
## Developed by the CS2212 Team 10 at Western University in London, Ontario, Canada

The Fitbit app is a desktop application that pulls and displays data from an authenticated user from Fitbit. The program has several features including: 
* **Custom Daily Dashboard** - the dashboard displays your distance, steps taken, floors climbed, and calories burned on a daily basis, along with a goal set by the user from Fitbit. The data is displayed in a series of windows that can be added/removed, moved and resized.
* **Lifetime Totals** - on a seperate page your lifetime distance, steps and floors are displayed.
* **Best Days** - this page shows the days the user achieved their maximum distance, steps and floors with the maximum value achieved.
* **Accolades** - this page has a table of 20 potential accomplishments with some that can be achieved daily or just once.
* **Map** - the map puts your Lifetime distance to scale, with a current location set and the lifetime distance is compared to the distance from the location to other locations in the local database. Cities within the lifetime distance are marked on the map, which is zoomable.

The program is written in Java, and makes use of three APIs:
* [Fitbit API](https://dev.fitbit.com/)
* [Google Static Maps API](https://developers.google.com/maps/documentation/static-maps/)
* [Google Geocoding API](https://developers.google.com/maps/documentation/geocoding/intro)

Various libraries are used, such as the Java Swing libraries and JSON libraries. Maven is used to package the program into a jar file.


# Install/Build

To build the program for the first time, an internet connection may be required. Here are a list of depedencies that are required to build the program: 
* [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) - Java is available on all main platforms including Linux, Mac OS X and Windows. The installation method depends on the operating system. 
* [Maven](https://maven.apache.org/) - Maven is used to build the program, using the `mvn` command.

First, clone the repository onto your local system and `cd` into the `team10` directory:
```
git clone ssh://git@repo.gaul.csd.uwo.ca:7999/cs2212_w2016/team10.git
```

Use the following maven command to compile the program: 
```
mvn package
```

The jar can be found in `target/team10-FitBitApp-1.0-SNAPSHOT-jar-with-dependencies.jar`


# Getting Started

The program has two modes; an online mode that interfaces with the FitBit API and a test mode that works offline with locally stored API data to test the program features without worring about being connected to the API. The normal online mode can be run with the following command from the `team10` directory: 

```
java -jar target/team10-FitBitApp-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Test mode can be invoked with the same command, but with a `-test` argument: 

```
java -jar target/team10-FitBitApp-1.0-SNAPSHOT-jar-with-dependencies.jar -test
```
The command will run and open a new window displaying the FitBit App.

NOTE: The Fitbit App was designed and best displayed on Mac OS X. 


# Usage Example

You can view our video demonstration here: [https://youtu.be/AOTz6X3l6Qc](https://youtu.be/AOTz6X3l6Qc)


# Documentation

The Java Documentation for the program can be found in the `doc` directory.


# Developers
* Neeraja Dharan
* Conor Dunne
* Gustavo Murcia
* Pearson Radu
* Lina Radwan
* Vincent Xie
* Patrick Zalewski
* John Zhang
