public class ATMwork {
    
    public static void main(String[] arg){
        System.out.println("ATM Work in progress...");
        
    }
}
class Transaction {
    String type;
    double amount;
    Date date;
    int ID;

    Transaction(String type, double amount, int ID) {
        this.type = type;
        this.ID = ID;
        this.amount = amount;
        this.date = new Date();
    }

    public String RecString() {
        return date + " - " + type + " : " + amount;
    }
}
class User {
    
    String pin;
    double balance;
    String userId;
    List<Transaction> transactions;

    User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    boolean validate(String pin) {
        return this.pin.equals(pin);
    }

    void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount, transactions.size() + 1));
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount, transactions.size() + 1));
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    double getBalance() {
        return balance;
    }
}
class ATMOperations {
    Scanner sc = new Scanner(System.in);

    void showMenu(User user) {
        while (true) {
            System.out.println("\n     ATM Menu    ");
            System.out.println("1. Transation_History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Check_Balance");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showTransactions(user);
                    break;
                case 2:
                    withdraw(user);
                    break;
                case 3:
                    deposit(user);
                    break;
                case 4:
                    checkBalance(user);
                    break;
                case 5:
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
            for (Transaction t : user.transactions) {
                System.out.println(t.RecString());
            }
        }
    }

    void withdraw(User user) {
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        user.withdraw(amount);
    }

    void deposit(User user) {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        user.deposit(amount);
    }

    void checkBalance(User user) {
        System.out.println("Current balance: " + user.getBalance());
    }


}