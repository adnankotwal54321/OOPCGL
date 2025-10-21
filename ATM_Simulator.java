import java.util.Scanner;

public class ATM_Simulator {

    double balance;

    public ATM_Simulator(double balance) {
        this.balance = balance;
    }

    public void checkBalance() {
        System.out.printf("Your current balance is:"+ balance);
    }

    public void deposit(Scanner sc) {
        try {
            System.out.print("Enter amount to deposit: ");
            String input = sc.nextLine();
            double depositAmount = Double.parseDouble(input);

            if (depositAmount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be greater than zero.");
            }

            balance += depositAmount;
            System.out.println( + depositAmount + " deposited successfully.");
            checkBalance();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("\n Deposit transaction complete.");
        }
    }

    public void withdraw(Scanner sc) {
        try {
            System.out.print("Enter amount to withdraw: ");
            String input = sc.nextLine();
            double withdrawAmount = Double.parseDouble(input);

            if (withdrawAmount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
            }

            if (withdrawAmount > balance) {
                throw new ArithmeticException("Insufficient funds.");
            }

            balance -= withdrawAmount;
            System.out.println( + withdrawAmount + " withdrawn successfully.");
            checkBalance();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("\n Withdrawal transaction complete.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM_Simulator atm = new ATM_Simulator(5000);

        System.out.println("Welcome to ATM Simulator!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        atm.deposit(sc);
                        break;
                    case 3:
                        atm.withdraw(sc);
                        break;
                    case 4:
                        System.out.println("Thank you for using ATM.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for choice.");
            }
        }
    }
}
