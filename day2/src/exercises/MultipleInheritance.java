package exercises;

interface InterfaceFirst {
	void m1();
}

interface InterfaceSecond {
	void m2();
}

public class MultipleInheritance implements InterfaceFirst, InterfaceSecond {
	public void m2() {
		System.out.println("I am method m2 ");
	}

	public void m1() {
		System.out.println("I am method m1 ");
	}

	public static void main(String[] args) {

		MultipleInheritance ff = new MultipleInheritance();
		ff.m1();
		ff.m2();
	}
}
