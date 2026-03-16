package exercises;

public class MTUsingThreadClass extends Thread{
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Run Method "+i);
		}
	}
	public static void main(String[] args) {
		MTUsingThreadClass demoObject = new MTUsingThreadClass();
		demoObject.start();
		for (int i = 0; i < 5; i++) {
			System.out.println("Main Method "+i);
		}
	}
}
