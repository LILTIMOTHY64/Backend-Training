package exercises;

//Method level synchronization
class BankAccount {
	int balance = 1000;

	synchronized void withdraw(int amount) {
		System.out.println(Thread.currentThread().getName() + " is withdrawing money");

		if (balance >= amount) {
			System.out.println("Balance before withdrawal: " + balance);
			balance = balance - amount;
			System.out.println("Balance after withdrawal: " + balance);
		} else {
			System.out.println("Insufficient balance");
		}
	}
}

class Customer extends Thread {
	BankAccount account;

	Customer(BankAccount account) {
		this.account = account;
	}

	public void run() {
		account.withdraw(500);
	}
}

public class MethodSynchronization {
	public static void main(String args[]) {
		BankAccount obj = new BankAccount();
		Customer c1 = new Customer(obj);
		Customer c2 = new Customer(obj);
		c1.setName("Person A");
		c2.setName("Person B");
		c1.start();
		c2.start();
	}
}
