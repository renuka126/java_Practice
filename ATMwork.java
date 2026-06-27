import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ATMwork {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATMOperations atm = new ATMOperations(sc);

        System.out.println("╔══════════════════════════╗");
        System.out.println("║     Welcome to ATM       ║");
        System.out.println("╚══════════════════════════╝");

        // Demo user
        User user = new User("renuka126", "1234");
        user.deposit(5000); // initial balance

        System.out.print("Enter User ID: ");
        String inputId = sc.nextLine().trim();
        System.out.print("Enter PIN   : ");
        String inputPin = sc.nextLine().trim();

        if (inputId.equals(user.userId) && user.validate(inputPin)) {
            System.out.println("Login Successful! Welcome, " + user.userId);
            atm.showMenu(user);
        } else {
            System.out.println("Invalid User ID or PIN!");
        }

        sc.close();
    }
}

class Transaction {
    String type;
    double amount;
    String date;
    int ID;

    Transaction(String type, double amount, int ID) {
        this.type   = type;
        this.amount = amount;
        this.ID     = ID;
        this.date   = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public String RecString() {
        return "TXN#" + ID + " | " + date + " | " + type + " : Rs." + String.format("%.2f", amount);
    }
}

class User {
    String userId;
    String pin;
    double balance;
    List<Transaction> transactions;

    User(String userId, String pin) {
        this.userId       = userId;
        this.pin          = pin;
        this.balance      = 0.0;
        this.transactions = new ArrayList<>();
    }

    boolean validate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    void deposit(double amount) {
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        balance += amount;
        transactions.add(new Transaction("Deposit", amount, transactions.size() + 1));
        System.out.println("Deposited Rs." + String.format("%.2f", amount));
    }

    void withdraw(double amount) {
        if (amount <= 0)          { System.out.println("Amount must be positive!"); return; }
        if (amount > balance)     { System.out.println("Insufficient funds!"); return; }
        balance -= amount;
        transactions.add(new Transaction("Withdraw", amount, transactions.size() + 1));
        System.out.println("Withdrawn Rs." + String.format("%.2f", amount));
    }

    double getBalance() {
        return balance;
    }
}

class ATMOperations {
    Scanner sc;

    ATMOperations(Scanner sc) {
        this.sc = sc;
    }

    void showMenu(User user) {
        while (true) {
            System.out.println("\n══════════════════════════");
            System.out.println("         ATM Menu         ");
            System.out.println("══════════════════════════");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a number 1-5.");
                continue;
            }

            switch (choice) {
                case 1: showTransactions(user); break;
                case 2: withdraw(user);         break;
                case 3: deposit(user);          break;
                case 4: checkBalance(user);     break;
                case 5:
                    System.out.println("Thank you for using ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    void showTransactions(User user) {
        if (user.transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("\n--- Transaction History ---");
            for (Transaction t : user.transactions) {
                System.out.println(t.RecString());
            }
        }
    }

    void withdraw(User user) {
        System.out.print("Enter amount to withdraw: ");
        try {
            double amount = Double.parseDouble(sc.nextLine().trim());
            user.withdraw(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
        }
    }

    void deposit(User user) {
        System.out.print("Enter amount to deposit: ");
        try {
            double amount = Double.parseDouble(sc.nextLine().trim());
            user.deposit(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
        }
    }

    void checkBalance(User user) {
        System.out.println("Current Balance: Rs." + String.format("%.2f", user.getBalance()));
    }
}