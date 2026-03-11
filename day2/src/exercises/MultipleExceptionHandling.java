package exercises;

public class MultipleExceptionHandling {
	public static void main(String[] args) {
		System.out.println("Hello");
		try {
			// Modify to divide by 0 {ArithmeticException Occurs}
			System.out.println(10 / 2);

			int[] a = { 10, 20, 30 };
			System.out.println(a[0]);
			System.out.println(a[1]);
			System.out.println(a[2]);
			// If this line is executed {ArrayindexOutOfRangeException Occurs}
			// System.out.println(a[3]);

		} catch (ArithmeticException e) {
			System.out.println("Exception Raised " + e);

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception Raised " + e);

		}
		// Handles all Exceptions that do not have a corresponding catch block
		// Having this at the top makes other catch blocks unreachable/useless
		catch (Exception e) {
			System.out.println("MainException Raised " + e);
		}
		finally {
			System.out.println("All vulnerable code executed");
		}
		System.out.println("Bye");
	}
}