// This program asks the user about recording information and 
// calculates and reports file size

import java.util.Scanner;

public class MaurerMusic {
	
	public static double calculateFileSize(int bitDepth, double sampleRate, int minutes, int seconds) {
		double result = (minutes * 60 + seconds)* sampleRate * 1000 * bitDepth / 8 / 1024 / 1024; // Kilobytes to megabytes
		return result;
	}
	
	// precondition: filesize is in megabytes
	// postcondition: if the file size exceeds 10 MB, return true
	public static boolean shouldCompress(double fileSize) {
		if(fileSize > 10) { // 10 is in megabytes
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String doAgain = "y";
		String songName;
		int minutes, seconds, bitDepth;
		double frequency, fileSize;
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
			fileSize = calculateFileSize(bitDepth, frequency, minutes, seconds);
			System.out.printf("The file %s is %.2f MB. \n", songName, fileSize);
			if (shouldCompress(fileSize)) {
				System.out.println("You should compress that massive thing.");
			} else {
				System.out.println("The file doesn't need to be compressed.");
			}
			System.out.print("Another? ");
			sc.nextLine(); // throws out the enter key
			doAgain = sc.nextLine().trim().toLowerCase();
		}
	}
}
