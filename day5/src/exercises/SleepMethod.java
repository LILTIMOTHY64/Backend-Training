package exercises;

class Trafficsignal extends Thread {
	public void run() {
		try {
			System.out.println("Red Light - Stop");
			Thread.sleep(5000);
			System.out.println("Yellow Light - Ready");
			Thread.sleep(2000);
			System.out.println("Green light - Go");
		} catch (InterruptedException e) {
			System.out.println("I am getting an error" + e);
		}

	}
}
public class SleepMethod {
public static void main(String[] args) {
	
	Trafficsignal  demoObject = new Trafficsignal();
	demoObject.start();
}
}
