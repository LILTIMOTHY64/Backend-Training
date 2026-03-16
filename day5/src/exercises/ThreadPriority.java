package exercises;

class MyThreadForPriority extends Thread {
	public void run() {
		System.out.println(Thread.currentThread().getName() +
				" Is running with " + Thread.currentThread().getPriority());
	}
}

public class ThreadPriority {
	public static void main(String[] args) {
		MyThreadForPriority t1 = new MyThreadForPriority();
		MyThreadForPriority t2 = new MyThreadForPriority();
		MyThreadForPriority t3 = new MyThreadForPriority();

		t1.setName("Low Priority");
		t2.setName("Normal Priority");
		t3.setName("High Priority");

		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);

		t1.start();
		t2.start();
		t3.start();

	}
}
