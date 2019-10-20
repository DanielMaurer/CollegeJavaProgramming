import java.util.Scanner;

public class DoubleThread extends Thread {
	Scanner sc = new Scanner(System.in); 
	double y;
	public void run() {
		for(;;) {
			System.out.println("Enter an Double");
			y = sc.nextDouble();
			System.out.println(y);
		}
	}

}
