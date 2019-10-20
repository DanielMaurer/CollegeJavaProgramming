import java.util.Scanner;

public class IntThread extends Thread {
	Scanner sc = new Scanner(System.in); 
	int x;
	public void run() {
		for(;;) {
			System.out.println("Enter an Integer");
			x = sc.nextInt();
			System.out.println(x);
		}
	}

}
