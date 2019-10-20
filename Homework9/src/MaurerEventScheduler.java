// Danny Maurer Homework 9
// Event Scheduler

/* This version will be able to organize various events based on the date input.
 * If the user inputs an invalid month, it is autoset to January. If the user
 * inputs an invalid day, it is autoset to 1. The maximum amount of inputs is 10. 
 */

import java.util.Scanner;

public class MaurerEventScheduler {
	
	private static String[] month_names = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November","December"};
	private static int[] month_lengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
	
	public static void space() { // so I dont have to type System.out.print
		System.out.println();
	}
	
	public static void printHeader() { // this function will print the header 
		String seperator = "";
		for (int i = 0; i < 50; i++) {
			seperator = seperator + "*";
		}
		System.out.println(seperator);
		System.out.println("           Welcome to Event Scheduler!");
		System.out.println(seperator);
		space();
	} // end of print header
	
	public static void printClosing() { // this function will print the closer
		String seperator = "";
		for (int i = 0; i < 50; i++) {
			seperator = seperator + "*";
		}
		System.out.println(seperator);
	} // end of print closer
	
	public static void printEvents(String[] events, int[] dates, int[] months, int[] years, int count, int month, int[] mark) {
		if (month == 0) {
			for(int i = 0; i < count; i++) {
				space();
				System.out.println("  " + events[i]);
				System.out.println("        " + month_names[months[i]-1] + " " + dates[i] + ", " + years[i]); 
				space();
			}
		} else {
			for(int i = 0; i < count; i++) {
				if(mark[i] != 0) {
					space();
					System.out.println("  " + events[i]);
					System.out.println("        " + month_names[months[i]-1] + " " + dates[i] + ", " + years[i]); 
					space();
				}
			}
			
			
		}
	}
	
	public static int fixDate(int date, int month) {
		if (date < 0) {
			date = 1;
		} else if (date > month_lengths[month - 1]) {
			date = 1;
		}
		
		return date;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] dates = new int[10];
		int[] months = new int[10];
		int[] years = new int[10];
		String[] events = new String[10];
		int[] mark = new int[10]; // going to help in printing last section
		int count = 0;
		String doAgain = "y";
		int date, month, year;
		String event;
		printHeader();
		do { // while loop to gather the information
			System.out.println("What are the details or your event?");
			if (count == 0) {
				System.out.println("             Press enter to begin!");
				space();
			}
			sc.nextLine();
			System.out.print("Event:  ");
			event = sc.nextLine(); 
			System.out.print("Month: ");
			month = sc.nextInt();
			if (month < 1 || month > 12) {
				month = 1;
			} // end of the change months
			
			System.out.print("Day:   ");
			date = sc.nextInt();
			date = fixDate(date, month);
			
			System.out.print("Year:  ");
			// time to add the data into 
			year = sc.nextInt();
			events[count] = event;
			months[count] = month;
			dates[count] = date;
			years[count] = year;
			count = count + 1;
			space();
			System.out.print("Add another event? ");
			doAgain = sc.next();
			space();
		} while(doAgain.equalsIgnoreCase("y") && count < 10); // end of do loop 
		System.out.println("Here are your events: ");
		month = 0;
		printEvents(events, dates, months, years, count, month, mark);
		space();
		System.out.print("What month would you like to see: ");
		month = sc.nextInt();
		space();
		for(int i = 0; i < count; i++) {
			if(months[i] == month) {
				mark[i] = month;
			} else {
				mark[i] = 0;
			}
		}
		System.out.println("Here are your events for " + month_names[month - 1] + ":");
		printEvents(events, dates, months, years, count, month, mark);
		printClosing();
		sc.close();
		
	} // end of main
} // end of EventScheduler
