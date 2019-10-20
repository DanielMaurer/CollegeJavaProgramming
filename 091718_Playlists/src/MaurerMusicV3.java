
import java.util.Scanner;
import java.util.ArrayList;

class Songg{ // doesnt work because the class Song already exists in the src
	private String name;
	private int length; // store the length in seconds 
	private double samplingRate; // stores teh sampling freq in kHz
	private int bitDepth; // stores the bitDepth in bits
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name; // name from the input to the name of the output
	}
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
	public Songg(String title, int seconds, double frequency, int bDepth) { // constructor 1
		setName(title);
		setLength(seconds);
		setSamplingRate(frequency);
		setBitDepth(bDepth);
	}
	public Songg(String title, int minutes, int seconds, double frequency, int bDepth) { // constructor 2
		this(title, minutes*60+seconds, frequency, bDepth);
	}
	public String toString() {
		return String.format("%s\n%d seconds \n%.2f kHz\t%d bits\n%.2f MB", name, length, samplingRate, bitDepth,calculateFileSize());
	}
	public double calculateFileSize() {
		return samplingRate * 1000 * bitDepth/4 * length/1024/1024;
	}
}

// 
class Playlist{
	private ArrayList<Songg> songs;
	public String toString() {
		String result = "";
		for(Songg s:songs) {
			result = result + s.toString() + "\n";
		}
		return result;
	}
	public void addSong(Songg s) {
		songs.add(s);
	}
	public Playlist() {
		songs = new ArrayList<Songg>();
	}
}

public class MaurerMusicV3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String doAgain = "y";
		String songName;
		int minutes, seconds, bitDepth;
		double frequency, fileSize;
		Songg mySong;
		Playlist list = new Playlist();
		while(doAgain.equals("y")) {
			System.out.print("Enter the name of the song: ");
			songName = sc.nextLine();
			System.out.print("Enter the length of the song in minutes and seconds: ");
			minutes = sc.nextInt();
			seconds = sc.nextInt();
			System.out.print("Enter the sampling frequency in kHz: ");
			frequency = sc.nextDouble();
			System.out.print("Enter the bit depth in bits: ");
			bitDepth = sc.nextInt();
			// create song object
			mySong = new Songg(songName, minutes, seconds, frequency, bitDepth);
			list.addSong(mySong);
			System.out.println("Here's the songs information");
			System.out.println(mySong);
			System.out.print("Another? ");
			sc.nextLine(); // throws out the enter key
			doAgain = sc.nextLine().trim().toLowerCase();
		}
		System.out.println("Here is your playlist: ");
		System.out.println(list);

	}
}
