package exercises;

interface Vehicle {
	void start();

	default void m1() {
		System.out.println("Default method");
	}
}

class Car implements Vehicle {
	public void start() {
		System.out.println("Car starts");
	}
}

public class DefaultMethod {
	public static void main(String[] args) {
		Car demoObject = new Car();
		demoObject.start();
		demoObject.m1();
	}

}