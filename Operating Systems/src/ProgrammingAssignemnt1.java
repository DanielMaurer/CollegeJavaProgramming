
public class ProgrammingAssignemnt1 extends Thread {
	
	public void run() {
		for (;;) {
			try {
				System.out.println("I'm a thread ...");
				Thread.currentThread().sleep(60*1000);
			} catch(InterruptedException e) {}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread th1 = new ProgrammingAssignemnt1();
		th1.start();
		Thread th2 = new IntThread();
		th2.start();
		Thread th3 = new DoubleThread();
		th3.start();
		

	}

}
