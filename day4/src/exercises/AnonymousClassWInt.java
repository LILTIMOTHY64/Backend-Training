package exercises;

interface Tests {
	public abstract void m1();
}

class OuterClass {
	void m2() {
		System.out.println("M2 Method");
	}

	Tests anonymousInnerClass = new Tests() {
		public void m1() {
			System.out.println("M1 Method");
		}
	};
}

public class AnonymousClassWInt {
	public static void main(String[] args) {
		OuterClass demoObject = new OuterClass();
		 demoObject.m2();
		 demoObject.anonymousInnerClass.m1();
	}
}
