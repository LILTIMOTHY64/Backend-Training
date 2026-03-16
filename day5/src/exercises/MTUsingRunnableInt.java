package exercises;

public class MTUsingRunnableInt implements Runnable {

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Run Method " + i);
		}
	}

	public static void main(String[] args) {
		MTUsingRunnableInt demoObject = new MTUsingRunnableInt();
		Thread threadObject = new Thread(demoObject);
		threadObject.start();
		for (int i = 0; i < 5; i++) {
			System.out.println("Main Method " + i);
		}
	}
}
