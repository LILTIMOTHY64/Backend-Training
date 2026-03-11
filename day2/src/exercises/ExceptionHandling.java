package exercises;

public class ExceptionHandling {
	public static void main(String[] args) {
		System.out.println("Hello");
		try {
			System.out.println(10 / 0);
		} catch (Exception e) {
			System.out.println("Exception Raised");
		}
		System.out.println("Hello last statment");
	}
}
