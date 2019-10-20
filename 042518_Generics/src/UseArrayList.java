// This program demonstrates using Array Lists

import java.util.ArrayList;
import java.util.Collections;

public class UseArrayList {

	public static void printCars(ArrayList<String> cars) {
		for (String car:cars) {
			System.out.println(car);
		}
	}
	
	public static void main(String[] args) {
		ArrayList<String> cars = new ArrayList<String>();
		cars.add("Chevy Cruze");
		cars.add("Mazda CX9");
		cars.add("Chevy Spark");
		cars.add(1, "Tesla S");
		printCars(cars);
		System.out.println("Sort the list: ");
		Collections.sort(cars);
		printCars(cars);

	}

}
