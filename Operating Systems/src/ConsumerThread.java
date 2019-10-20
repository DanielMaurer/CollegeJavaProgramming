import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ConsumerThread extends Thread {
	
	int[] numbers = new int[0];
	Semaphore s1;
	Semaphore s2;
	
	public ConsumerThread(int[] numbers, Semaphore s1, Semaphore s2) {
		super();
		this.numbers = numbers;
		this.s1 = s1;
		this.s2 = s2;
	}
	
	Scanner sc = new Scanner(System.in); 
	public void run() {	
		for(;;) {
			try {
				s1.acquire();
			} catch (Exception ex) {
				
			}
			System.out.println(numbers[0]);
			s2.release();
		}
	}
}
