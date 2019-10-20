
import java.util.Scanner;
import java.util.ArrayList;

//-------------------------------Media Class----------------------------------

abstract class Media{
	private String name;
	private int length; 
	private String rating; // add rating to all media types.
	
	// -------------Get and Set functions--------------
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
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	// What makes this class abstract, need to declare this in later classes
	public abstract String getType(); // what kind of media this is
	public abstract double calculateFileSize();	
	
	// ---------------Media toString-------------------------
	public String toString() {
		return String.format("Type: %s\nName: %s\nLength: %d seconds\nRating: %s", getType(), name, length, rating);
	}
	
	// ---------------Media Constructor---------------------
	public Media(String name, int seconds, String rating) {
		setName(name);
		setLength(seconds);
		setRating(rating);
	}
	public Media(String name, int minutes, int seconds, String rating) {
		this(name, minutes*60+seconds, rating);
	}
}

//-------------------------------Artist Class------------------------------
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
	public Artist() { // default constructor, take control with no inputs
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
		if(isBand == true) {
			return String.format("Name: %s\nBand or Solo: %s", name, getBandString());
		}else{
			return String.format("Artist: %s\nBand or Solo: %s", name, getBandString());
		}
	}
}

//----------------------------Feature Class---------------------------
// Tells if there is a feature in a song
class Feature{
	private String feature;
	private boolean featYN;
	
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public boolean getfeatYN() {
		return featYN;
	}
	public void setFeatYN(boolean featYN) {
		this.featYN = featYN;
	}
	public void setFeatYN(String yn) {
		yn = yn.trim().toLowerCase();
		if(yn.equals("y")) {
			featYN = true;
		} else {
			featYN = false;
		}
	}
	public Feature() { // default constructor, take control with no inputs
		feature = "Unknown";
		featYN = true;
	}
	public Feature(String feature, boolean featYN) {
		setFeature(feature);
		setFeatYN(featYN);
	}
	public Feature(String feature, String yn) {
		setFeature(feature);
		setFeatYN(yn);
	}
	public String toString() {
		if(featYN == true) {
			return String.format("Feature: %s\n", feature);
		}else{
			return String.format("No Features");
		}
	}
}

//------------------------------Song class--------------------------------------

class Song extends Media{ // doesnt work because the class Song already exists in the src
	private double samplingRate; // stores the sampling frequency in kHz
	private int bitDepth; // stores the bitDepth in bits
	private Artist artist; // ownership: composition (see constructor)
	private Feature feature;

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
		return "Song"; // allows class to not be abstract
	}
	
	
	public Song(String title, int seconds, String rating, double frequency, int bDepth, String artistName, String artistIsBand, String featureName, String featYN) { // constructor 1  BOTH NON-DEFAULT CONSTRUCTORS
		super(title, seconds, rating); // uses Media constructor
		setSamplingRate(frequency);
		setBitDepth(bDepth);
		artist = new Artist(artistName, artistIsBand); // composition ownership
		feature = new Feature(featureName, featYN);
	}
	public Song(String title, int minutes, int seconds, String rating, double frequency, int bDepth, String artistName, String artistIsBand, String featureName, String featYN) { // constructor 2
		this(title, minutes*60+seconds, rating, frequency, bDepth, artistName, artistIsBand, featureName, featYN);
	}
	public String toString() {
		return String.format("%s\nSampling Rate: %.1f kHz\nBitDepth: %d bits\n%s\n%s\n", super.toString(), samplingRate, bitDepth, artist.toString(), feature.toString()); // delegation in artist.toString and feature.toString
		
	}
	public double calculateFileSize() {
		return samplingRate * 1000 * bitDepth/4 * getLength()/1024/1024; // use get length to access data from super class
	}
}

//-----------------------------Movie Class------------------------------------

class Movie extends Media{
	private String director;
	private boolean inTheaters; // says if the movie is still in theaters
	//----------Get and set functions-------
	public String getDirector() {
		return director;
	}
	public void setDirector(String dir) {
		director = dir;
	}
	public String getType() {
		return "Movie";
	}
	public boolean getInTheaters() {
		return inTheaters;
	}
	public void setInTheaters(boolean inTheaters) {
		this.inTheaters = inTheaters;
	}
	public void setInTheaters(String yn) {
		yn = yn.trim().toLowerCase();
		if(yn.equals("y")) {
			inTheaters = true;
		} else {
			inTheaters = false;
		}
	}
	
	
	public double calculateFileSize() {
		return 1.2 * getLength();
	}
	//constructors
	public Movie(String title, int seconds, String rating, String director, String inTheatersYN) {
		super(title,seconds, rating);
		setDirector(director);
		setInTheaters(inTheatersYN);
	}
	public Movie(String title, int minutes, int seconds, String rating, String director, String inTheatersYN) {
		super(title, minutes, seconds, rating);
		setDirector(director);
		setInTheaters(inTheatersYN);
	}
	public String getInTheatersString() {
		if (inTheaters == true) {
			return "Still Playing!";
		} else {
			return "Try Netflix";
		}
	}
	public String toString() {
		return String.format("%s\nDirector: %s\nIn Theaters: %s\n", super.toString(), director, getInTheatersString());
	}	
}

//----------------------------Audiobook Class-------------------------------
class Audiobook extends Media{
	private String author; // added the author, reader, book number and total books in series
	private String reader;
	private int bookNo;
	private int totalBooks;
	//----------Get and set functions-----------
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getReader() {
		return reader;
	}
	public void setReader(String reader) {
		this.reader = reader;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bNo) {
		if(bNo < 1) {
			bookNo = 1;
		}else {
			bookNo = bNo;
		}
	}
	public int getTotalBooks() {
		return totalBooks;
	}
	public void setTotalBooks(int tNo) {
		if(tNo < 1) {
			totalBooks = 1;
		}else {
			totalBooks = tNo;
		}
	}
	public String getType() {
		return "Audiobook";
	}
	public double calculateFileSize() {
		return getLength()*0.5;
	}
	public Audiobook(String title, int seconds, String rating, String author, String reader, int bookNo, int totalBooks) { // constructor
		super(title, seconds, rating);
		setAuthor(author);
		setReader(reader);
		setBookNo(bookNo);
		setTotalBooks(totalBooks);
	}
	public Audiobook(String title, int seconds, int minutes, String rating, String author, String reader, int bookNo, int totalBooks) { // constructor
		this(title, minutes * 60 + seconds, rating, author, reader, bookNo, totalBooks);
	}
	public String toString() {
		return String.format("%s\nAuthor: %s\nReader: %s\nBook Number %d of %d\n", super.toString(), author, reader, bookNo, totalBooks);
	}
}

//--------------------------Playlist Class----------------------------------

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
		System.out.println();
	}
	public Playlist() {
		media = new ArrayList<Media>();
	}
}

public class PlayListMaurer {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String doAgain = "y";
		// Variables for Media class
		int minutes, seconds;
		String rating;
		// Variables for the song class
		String songName, artistName, isBandYN, featYN, feature;
		int bitDepth;
		double frequency, fileSize;
		// Variables for the movie class
		String movieName, director, inTheatersYN;
		//Variables for audiobook
		String bookName, author, reader;
		int bookNo, totalBooks;
		
		//creating the variable for the object
		Song mySong;
		Movie myMovie;
		Audiobook myAudiobook;
		
		Playlist list = new Playlist();
		String choice; // whether they want a song or a movie
		while(doAgain.equals("y")) {
			System.out.print("[m]ovie, [s]ong or [a]udiobook? ");
			choice = sc.nextLine().trim().toLowerCase();
			if(choice.equals("m")) {
				System.out.print("Enter the name of the moive: ");
				movieName = sc.nextLine();
				System.out.print("Enter the length in minutes and seconds: ");
				minutes = sc.nextInt();
				seconds = sc.nextInt();
				sc.nextLine(); // throwing out end of line because minutes and seconds didnt take line
				System.out.print("Enter the rating of the movie (1-5): ");
				rating = sc.nextLine();
				System.out.print("Enter the name of the director: ");
				director = sc.nextLine();
				System.out.print("Is this movie still in Theaters (y or n): ");
				inTheatersYN = sc.nextLine().trim().toLowerCase();
				//creating the object 
				myMovie = new Movie(movieName, minutes, seconds, rating, director, inTheatersYN);
				list.addMedia(myMovie);
				System.out.println("");
			}else if (choice.equals("s")) {
				System.out.print("Enter the name of the song: ");
				songName = sc.nextLine();
				System.out.print("Enter the length of the song in minutes and seconds: ");
				minutes = sc.nextInt();
				seconds = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter the rating of the song (1-5): ");
				rating = sc.nextLine();
				System.out.print("Enter the sampling frequency in kHz: ");
				frequency = sc.nextDouble();
				System.out.print("Enter the bit depth in bits: ");
				bitDepth = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter the name of the Artist: ");
				artistName = sc.nextLine();
				System.out.print("Is the artist a band (y or n)?");
				isBandYN = sc.nextLine().trim().toLowerCase();
				System.out.print("Does this song have a feature (y or n)? ");
				featYN = sc.nextLine().trim().toLowerCase();
				if(featYN.equals("y")) {
					System.out.print("What band is featured? ");
					feature = sc.nextLine();
				}else {
					feature = "";
				}
				// creating the object
				mySong = new Song(songName, minutes, seconds, rating, frequency, bitDepth, artistName, isBandYN, feature, featYN);
				list.addMedia(mySong);
			}else if (choice.equals("a")) {
				System.out.print("Enter the name of the Audiobook: ");
				bookName = sc.nextLine();
				System.out.print("Enter the length of the audiobook in minutes and seconds: ");
				minutes = sc.nextInt();
				seconds = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter the rating of the audiobook (1-5): ");
				rating = sc.nextLine();
				System.out.print("Enter the name of the author: ");
				author = sc.nextLine();
				System.out.print("Who read the book? ");
				reader = sc.nextLine();
				System.out.print("How many audiobooks are in this series? ");
				totalBooks = sc.nextInt();
				System.out.print("What is the number of this book in reguards to the series? ");
				bookNo = sc.nextInt();
				// creating the object
				myAudiobook = new Audiobook(bookName, minutes, seconds, rating, author, reader, totalBooks, bookNo);
				list.addMedia(myAudiobook);
			}
			else{
				System.out.println("Read the instructions you nincompoop!!\n");
			}
			System.out.print("Another? \n");
			doAgain = sc.nextLine().trim().toLowerCase();
		}
		System.out.println("Here is your playlist: ");
		System.out.println(list);

	}
}
