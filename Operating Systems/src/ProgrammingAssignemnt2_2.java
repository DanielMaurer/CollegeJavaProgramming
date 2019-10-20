import java.util.concurrent.Semaphore;

public class ProgrammingAssignemnt2_2 {
	
	public static void main(String[] args) {
		Semaphore s1 = new Semaphore(1); // the first acquire will be successful
		Semaphore s2 = new Semaphore(0); //
		int[] numbers = new int[1];
		numbers[0] = 0;
		Thread pTh1 = new ProducerThread(numbers, s1, s2);
		pTh1.start();
		Thread cTh1 = new ConsumerThread(numbers, s1, s2);
		cTh1.start();
		Thread pTh2 = new ProducerThread(numbers, s1, s2);
		pTh2.start();
		Thread cTh2 = new ConsumerThread(numbers, s1, s2);
		cTh2.start();

	}

}
