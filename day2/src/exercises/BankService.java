package exercises;

@SuppressWarnings("serial")
class MinimumBalanceException extends Exception {

    MinimumBalanceException(String message) {
        super(message);
    }

}
public class BankService {

    static void withdrawMoney(int balance, int withdrawAmount) 
    		throws MinimumBalanceException {

        int remainingBalance = balance - withdrawAmount;

        if (remainingBalance < 1000) {
            throw new MinimumBalanceException("Minimum balance of 1000 must be maintained");
        }

        System.out.println("Withdrawal successful");
        System.out.println("Remaining balance: " + remainingBalance);
    }

}