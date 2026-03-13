package exercises;

abstract class Animal {
	abstract void eat();
}

public class AnonymousClassWAbC {
	Animal anonymousInnerClass = new Animal() {
		void eat() {
			System.out.println("Animal eats grass");
		}
	};

	public static void main(String[] args) {
		AnonymousClassWAbC demoObject = new AnonymousClassWAbC();
		demoObject.anonymousInnerClass.eat();
	}
}
