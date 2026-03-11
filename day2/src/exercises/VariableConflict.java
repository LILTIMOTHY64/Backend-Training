package exercises;

public class VariableConflict {

		int a = 10;
		int b = 20;

		void m1(int a, int b) {
			System.out.println(a + b);
			System.out.println(this.a + this.b);
		}

		public static void main(String[] args) {
			VariableConflict gg = new VariableConflict();
			gg.m1(2, 3);
		}
	}


