package exercises;

@FunctionalInterface
interface MyInterface {
	void m1();
}

public class FunctionalInterfaceIntro {

	public static void main(String[] args) {

		MyInterface obj = () -> {
			System.out.println("Hello I am a Functional Interface");
		};
		obj.m1();
	}

}