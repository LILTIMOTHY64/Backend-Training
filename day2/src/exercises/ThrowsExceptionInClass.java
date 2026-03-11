package exercises;

public class ThrowsExceptionInClass {

	// Method 1: where exception occurs
	static void checkBalance(int balance, int amount) throws Exception {
		if (amount > balance) {
			throw new Exception("Insufficient Balance");
		}
		System.out.println("Balance is sufficient");
	}

	// Method 2: calling method
	static void withdrawMoney(int balance, int amount) throws Exception {
		checkBalance(balance, amount);
		System.out.println("Withdrawal successful");
	}

	// Method 3: main method handles exception
	public static void main(String[] args) {
		try {
			withdrawMoney(2000, 500);
		} catch (Exception e) {
			System.out.println("Transaction Failed: " + e.getMessage());
		}

	}
}
