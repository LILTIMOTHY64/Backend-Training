package exercises;

class MyThread extends Thread {
	public void run() {
		for (int i = 0; i <= 5; i++) {
			System.out.println("Thread is alive " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Error occured " + e);
			}
		}

	}
}

public class IsAliveMethod {
	public static void main(String[] args) {
		MyThread threadObject = new MyThread();
		System.out.println("Thread is alive ? " + threadObject.isAlive());
		threadObject.start();
		System.out.println("Thread is alive ? " + threadObject.isAlive());

		try {
			threadObject.join();
		} catch (InterruptedException e) {
			System.out.println("Error Occured " + e);
		}
		System.out.println("Thread is alive ? " + threadObject.isAlive());
	}
}
