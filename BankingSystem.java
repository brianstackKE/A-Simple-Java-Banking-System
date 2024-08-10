import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive.");
            }
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void withdraw(double amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            if (amount > balance) {
                throw new IllegalArgumentException("Insufficient funds.");
            }
            balance -= amount;
            System.out.println("Successfully withdrew: " + amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }

    public void transfer(BankAccount toAccount, double amount) {
        try {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Successfully transferred: " + amount + " to account " + toAccount.getAccountNumber());
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }
}

public class BankingSystem {
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("---------------WELCOME---------------\n ");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Display Account Information");
            System.out.println("5. Add Account");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("------------------------------\n ");
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    BankAccount account = findAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    } else {
                        System.out.println("Invalid account number.");
                    }
                    break;
                case 2:
                    System.out.print("------------------------------\n ");
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    account = findAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid account number.");
                    }
                    break;
                case 3:
                    System.out.print("------------------------------\n ");
                    System.out.print("Enter from account number: ");
                    String fromAccountNumber = scanner.next();
                    System.out.print("Enter to account number: ");
                    String toAccountNumber = scanner.next();
                    BankAccount fromAccount = findAccount(fromAccountNumber);
                    BankAccount toAccount = findAccount(toAccountNumber);
                    if (fromAccount != null && toAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        fromAccount.transfer(toAccount, transferAmount);
                    } else {
                        System.out.println("Invalid account number(s).");
                    }
                    break;
                case 4:
                    System.out.print("------------------------------\n ");
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    account = findAccount(accountNumber);
                    if (account != null) {
                        account.displayAccountInfo();
                    } else {
                        System.out.println("Invalid account number.");
                    }
                    break;
                case 5:
                    System.out.print("------------------------------\n ");
                    System.out.print("Enter new account number: ");
                    accountNumber = scanner.next();
                    System.out.print("Enter account holder name: ");
                    scanner.nextLine();
                    String accountHolderName = scanner.nextLine();
                    accounts.add(new BankAccount(accountNumber, accountHolderName, 0));
                    System.out.println("Account successfully created.");
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
