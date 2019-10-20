
import java.util.Scanner;
import java.util.ArrayList;

abstract class Media{
	private String name;
	private int length; 
	
	// Paste get and set functions from the song class
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name; // name from the input to the name of the output
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) { // length to be in seconds
		if(length < 0) {
			this.length = 0;
		} else {
			this.length = length;
		}
	}
	public void setLength(int minutes, int seconds) {
		int len = 60 * minutes + seconds;
		setLength(len); // call other set function now that the units are correct
	}
	
	public abstract String getType(); // what kind of media this is
	public String toString() {
		return String.format("Type: %s\nName: %s\n Length: %d seconds", getType(), name, length);
	}
	public Media(String name, int seconds) {
		setName(name);
		setLength(seconds);
	}
	public Media(String name, int minutes, int seconds) {
		this(name, minutes*60+seconds);
	}
	public abstract double calculateFileSize();	
}

class Artist{
	private String name;
	private boolean isBand;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsBand() {
		return isBand;
	}
	public void setIsBand(boolean isBand) {
		this.isBand = isBand;
	}
	public void setIsBand(String yn) {
		yn = yn.trim().toLowerCase();
		if(yn.equals("y")) {
			isBand = true;
		} else {
			isBand = false;
		}
	}
	public Artist() { // default costructor, take control with no inputs
		name = "Unknown";
		isBand = true;
	}
	public Artist(String name, boolean isBand) {
		setName(name);
		setIsBand(isBand);
	}
	public Artist(String name, String yn) {
		setName(name);
		setIsBand(yn);
	}
	public String getBandString() {
		if (isBand == true) {
			return "Band";
		} else {
			return "Solo Artist";
		}
	}
	public String toString() {
		return String.format("Name: %s\n%s", name, getBandString());
	}
}

class Song extends Media{ // doesnt work because the class Song already exists in the src
	private String name;
	private int length; // store the length in seconds 
	private double samplingRate; // stores the sampling frequency in kHz
	private int bitDepth; // stores the bitDepth in bits
	private Artist artist; // ownership: composition (see constructor)

	public double getSampleingRate() {
		return samplingRate;
	}
	public void setSamplingRate(double rate) {
		if(rate < 0) {
			samplingRate = 0;  // to make sure that the data fits the model
		}else {
			samplingRate = rate;
		}
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist a) {
		artist = a;
	}
	public void setArtist(String name, boolean isBand) {
		artist = new Artist(name, isBand);
	}
	public void setArtist(String name, String yn) {
		artist = new Artist(name, yn);
	}
	public void setArtistName(String name) {
		artist.setName(name); // delegation 
	}
	public int getBitDepth() {
		return bitDepth;
	}
	public void setBitDepth(int bDepth) {
		if (bDepth < 0) {
			bitDepth = 0;
		}else {
			bitDepth = bDepth;
		}
	}
	public String getType() {
		return "song"; // allows class to not be abstract
	}
	public Song(String title, int seconds, double frequency, int bDepth, String artistName, String artistIsBand) { // constructor 1  BOTH NON-DEFAULT CONSTRUCTORS
		super(title, seconds); // uses Media constructor
		setSamplingRate(frequency);
		setBitDepth(bDepth);
		artist = new Artist(artistName, artistIsBand); // composition ownership
	}
	public Song(String title, int minutes, int seconds, double frequency, int bDepth, String artistName, String artistIsBand) { // constructor 2
		this(title, minutes*60+seconds, frequency, bDepth, artistName, artistIsBand);
	}
	public String toString() {
		return String.format("%s\n Sampling Rate: %.1f kHz\nBitDepth: %d bits\nArtist: %s", super.toString(), samplingRate, bitDepth, artist.toString()); // delegation in artist.toString
	}
	public double calculateFileSize() {
		return samplingRate * 1000 * bitDepth/4 * getLength()/1024/1024; // use get length to access data from super class
	}
}

class Movie extends Media{
	private String director;
	public String getDirector() {
		return director;
	}
	public void setDirector(String dir) {
		director = dir;
	}
	public String getType() {
		return "movie";
	}
	public double calculateFileSize() {
		return 1.2 * getLength();
	}
	//constructors
	public Movie(String title, int seconds, String director) {
		super(title,seconds);
		setDirector(director);
	}
	public Movie(String title, int minutes, int seconds, String director) {
		super(title, minutes, seconds);
		setDirector(director);
	}
	public String toString() {
		return String.format("%s\n%s", super.toString(), director);
	}
		
}

class Playlist{
	private ArrayList<Media> media; // generic
	public String toString() {
		String result = "";
		for(Media m:media) {
			result = result + m.toString() + "\n"; // example of polymorphism
		}
		return result;
	}
	public void addMedia(Media m) {
		media.add(m);
	}
	public Playlist() {
		media = new ArrayList<Media>();
	}
}

public class MusicRefactored {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String doAgain = "y";
		String songName;
		int minutes, seconds, bitDepth;
		double frequency, fileSize;
		String movieName, director;
		String artistName, isBandYN;
		Song mySong;
		Movie myMovie;
		Playlist list = new Playlist();
		String choice; // whether they want a song or a movie
		while(doAgain.equals("y")) {
			System.out.print("[m]ovie or [s]ong? ");
			choice = sc.nextLine().trim().toLowerCase();
			if(choice.equals("m")) {
				System.out.print("Enter the name of the moive: ");
				movieName = sc.nextLine();
				System.out.print("Enter the length in minutes and seconds: ");
				minutes = sc.nextInt();
				seconds = sc.nextInt();
				sc.nextLine(); // throwing out end of line
				System.out.print("Enter the name of the director ");
				director = sc.nextLine();
				myMovie = new Movie(movieName, minutes, seconds, director);
				list.addMedia(myMovie);
			}else if (choice.equals("s")) {
				System.out.print("Enter the name of the song: ");
				songName = sc.nextLine();
				System.out.print("Enter the length of the song in minutes and seconds: ");
				minutes = sc.nextInt();
				seconds = sc.nextInt();
				System.out.print("Enter the sampling frequency in kHz: ");
				frequency = sc.nextDouble();
				System.out.print("Enter the bit depth in bits: ");
				bitDepth = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter the name of the Artist: ");
				artistName = sc.nextLine();
				System.out.print("Is the artist a band (y or n)?");
				isBandYN = sc.nextLine().trim().toLowerCase();
				sc.nextLine();
				mySong = new Song(songName, minutes, seconds, frequency, bitDepth, artistName, isBandYN);
				sc.nextLine(); // throws out the enter key
				list.addMedia(mySong);
			}
			System.out.print("Another? ");
			doAgain = sc.nextLine().trim().toLowerCase();
		}
		System.out.println("Here is your playlist: ");
		System.out.println(list);

	}
}
