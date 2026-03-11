package exercises;

public class BankApp {
    public static void main(String[] args) {
        try {
            BankServices.withdrawMoney(5000, 500);
        } catch (MinimumBalanceException e) {

            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }
}
