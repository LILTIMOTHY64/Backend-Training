package exercises;

interface Left2 {
	default void m1() {
		System.out.println("Left Default method");
	}
}
interface Right2 {
	default void m1() {
		System.out.println("Right Default method");
	}
}
public class DefaultMethodOverride implements Left, Right {
	public void m1() {
//		Right.super.m1();
		System.out.println("Override Method");
	}
	public static void main(String[] args) {
		DefaultMethodOverride t = new DefaultMethodOverride();
		t.m1();
	}
}
