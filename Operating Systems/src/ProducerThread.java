import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ProducerThread extends Thread {
	
	int[] numbers = new int[0];
	Semaphore s1;
	Semaphore s2;
	
	public ProducerThread(int[] numbers, Semaphore s1, Semaphore s2) {
		super();
		this.numbers = numbers;
		this.s1 = s1;
		this.s2 = s2;
	}
	
	Scanner sc = new Scanner(System.in);  
	public void run() {
		for(;;) {
			try {
				Thread.sleep(1000);
				s2.acquire();
			} catch (Exception ex) {
				
			}
			
			synchronized(numbers) {
				numbers[0] += 1;
			}
			s1.release();
		}
	}
}
