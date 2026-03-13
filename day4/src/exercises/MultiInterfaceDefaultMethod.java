package exercises;

interface Left {
	default void m1() {
		System.out.println("Left Default method");
	}
}
interface Right {
	default void m1() {
		System.out.println("Right Default method");
	}
}
public class MultiInterfaceDefaultMethod implements Left, Right {
	public void m1() {
		Left.super.m1();
		Right.super.m1();
	}
	public static void main(String[] args) {
		MultiInterfaceDefaultMethod t = new MultiInterfaceDefaultMethod();
		t.m1();
	}
}