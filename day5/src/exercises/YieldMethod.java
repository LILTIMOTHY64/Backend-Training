package exercises;

//yield
class MyThreadForYield extends Thread {
	public void run() {
		for (int i = 0; i <= 5; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			Thread.yield();
		}
	}
}

public class YieldMethod {
	public static void main(String[] args) {
		MyThreadForYield threadObject1 = new MyThreadForYield();
		MyThreadForYield threadObject2 = new MyThreadForYield();

		threadObject1.setName("Thread - 1");
		threadObject2.setName("Thread - 2");

		threadObject1.start();
		threadObject2.start();
	}
}
