// Danny Maurer
/* This program will allow the user to input a start time/location
 * and an end time/location and compute the total distance and the 
 * users pace for the run.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

class Time{
	// time variables
	private int seconds, minutes, hours;
	
	// get and set functions 
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int sec) {
		if(sec < 0 || sec > 60) {
			sec = 0;
		} 
		this.seconds = sec;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int min) {
		if(min < 0 || min > 60) {
			min = 0;
		}
		this.minutes = min;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hrs) {
		if(hrs < 0 || hrs > 24) {
			hrs = 0;
		}
		this.hours = hrs;
	}
	
	// to String function 
	public String toString() {
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
	
	// time constructor
	public Time(int hrs, int min, int sec) {
		setHours(hrs);
		setMinutes(min);
		setSeconds(sec);
	}
	
	public int secondsSinceMidnight() { // already in the time class so you don't need to shoot in new variables
		int totTime = hours * 60 * 60 + minutes * 60 + seconds;
		return totTime;
	}
}

class Location{
	// location variables
	private double latitude, longitude;
	private String ns, ew;
	
	// get and set functions
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double lat) {
		if(lat < -90) {
			lat = -90;
		}else if(lat > 90) {
			lat = 90;
			ns = "N";
		}
		this.latitude = lat;  // tell if the location is in the north or the south
		if(lat > 0) {
			ns = "N";
		}else {
			ns = "S";
		}
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longi) {
		if(longi < -180) {
			longi = -180;
		}else if(longi > 180) {
			longi = 180;
		}
		this.longitude = longi; // tell if the location is in the east or the west
		if(longi > 0) {
			ew = "E";
		}else {
			ew = "W";
		}
	}
	
	// to String function
	public String toString() {
		return String.format("Longitude: %.4f deg %s Latitude: %.4f deg %s", longitude, ew, latitude, ns);
	}
	
	// Location constructor
	public Location(double lon, double lat) {
		setLongitude(lon);
		setLatitude(lat);
	}
	
}

class Run{
	// Run variables
	private Time startTime;
	private Time endTime;
	private Location startLocation;
	private Location endLocation;
	private double d;
	
	// Get and set functions for time variables
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(int sHrs, int sMins, int sSecs) {
		startTime = new Time(sHrs, sMins, sSecs);
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(int eHrs, int eMins, int eSecs) {
		endTime = new Time(eHrs, eMins, eSecs);
	}
	
	// get and set functions for location variables
	public Location getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(double sLong, double sLat) {
		startLocation = new Location(sLong, sLat);
	}
	public Location getEndLocation() {
		return endLocation;
	}
	public void setEndLocation(double eLong, double eLat) {
		endLocation = new Location(eLong, eLat);
	}
	
	public Run(int startHours, int startMinutes, int startSeconds, int endHours, int endMinutes, int endSeconds, double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
		setStartTime(startHours, startMinutes, startSeconds);  // set functions for the Run constructor
		setEndTime(endHours, endMinutes, endSeconds);
		setStartLocation(startLongitude, startLatitude);
		setEndLocation(endLongitude, endLatitude);
		
	}
	
	public double deg2rad(double deg) { // converts degrees into radians
		  return deg * (Math.PI/180);
		}
		
	public double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {  // calculate the distance from inputs of longitude and latitude 
		  int R = 6371; // Radius of the earth in km
		  double dLat = deg2rad(lat2-lat1);  // deg2rad below
		  double dLon = deg2rad(lon2-lon1); 
		  double a = 
		    Math.sin(dLat/2) * Math.sin(dLat/2) +
		    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
		    Math.sin(dLon/2) * Math.sin(dLon/2)
		    ; 
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		  d = R * c; // Distance in km
		  return d;
		}
	
	public double calculatePace() {
		int elapsedTime = (endTime.secondsSinceMidnight() - startTime.secondsSinceMidnight()) / 60;
		double pace = (elapsedTime / d);
		return pace;
		
	}
	
	public String toString() {
		return String.format("Start: %s at %s\nEnd:   %s at %s\nDistance %.2f km\nPace: %.2f min/km", startLocation.toString(), startTime.toString(), endLocation.toString(), endTime.toString(), getDistanceFromLatLonInKm(startLocation.getLatitude(), startLocation.getLongitude(), endLocation.getLatitude(), endLocation.getLongitude()), calculatePace()); // delegate to time and location toString as well as use get functions for location objects
	}

}

public class MaurerDannyRun {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// variables for time
		int startHour, startMinute, startSecond, endHour, endMinute, endSecond;
		// variables for location
		double startLongitude, startLatitude, endLongitude, endLatitude;
		
		// variables for the objects
		Run myRun;
		
		System.out.print("Enter your start time as Hour, Minute, and Second: ");
		startHour = sc.nextInt();
		startMinute = sc.nextInt();
		startSecond = sc.nextInt();
		sc.nextLine(); // throw out end of line marker
		System.out.print("Enter your end time as Hour, Minute, and Second: ");
		endHour = sc.nextInt();
		endMinute = sc.nextInt();
		endSecond = sc.nextInt();
		sc.nextLine(); // toss EOL
		System.out.print("Enter your start location as Latitude and Longitude: ");
		startLatitude = sc.nextDouble();
		startLongitude = sc.nextDouble();
		sc.nextLine(); // toss EOL
		System.out.print("Enter your end location as Latitude and Longitude: ");
		endLatitude = sc.nextDouble();
		endLongitude = sc.nextDouble();
		sc.nextLine(); // toss EOL
		
		// Create the run object
		myRun = new Run(startHour, startMinute, startSecond, endHour, endMinute, endSecond, startLatitude, startLongitude, endLatitude, endLongitude); // call the run constructor
		System.out.println("Here is the info on your run:");
		System.out.println(myRun); // take advantage of the toSring function
		

	}

}
