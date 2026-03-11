package exercises;

public class BankApp {
    public static void main(String[] args) {
        try {
            BankService.withdrawMoney(5000, 4500);
        } catch (MinimumBalanceException e) {

            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }
}
