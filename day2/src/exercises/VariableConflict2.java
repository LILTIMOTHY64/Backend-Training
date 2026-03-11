package exercises;

public class VariableConflict2 {
	int a;
	int b;

	void m1(int a, int b) {
		this.a = a;
		this.b = b;
	}

	void m2() {
		System.out.println(a + b);
	}

	public static void main(String[] args) {
		VariableConflict2 gg = new VariableConflict2();
		gg.m1(2, 3);
		gg.m2();
	}
}
