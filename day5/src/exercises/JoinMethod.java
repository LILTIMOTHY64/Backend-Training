package exercises;

class Boilwater extends Thread {
	public void run() {
		for (int i = 0; i <= 3; i++) {
			System.out.println("Water is Boiling... Step " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		System.out.println("Water Boiling Finished");
	}
}
public class JoinMethod {
	public static void main(String[] args) {
		Boilwater demoThread = new Boilwater();
		demoThread.start();

		try {
			demoThread.join();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Now add tea powder to the Boiling water");
	}
}
