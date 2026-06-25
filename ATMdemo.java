import java.util.*;

public class ATMdemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        ATMOperations atmOps = new ATMOperations();

        while (true) {
            System.out.println("\n==== ATM welcomes you! ====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter User_ID: ");
                String userId = sc.next();
                System.out.print("Enter PIN: ");
                String pin = sc.next();

                User user = bank.getUser(userId); 

                if (user != null && user.validate(pin)) {
                    System.out.println("Login Successful!");
                    atmOps.showMenu(user, bank);
                } else {
                    System.out.println("Invalid User ID or PIN!");
                }

            } else if (choice == 2) {
                System.out.print("Choose a User ID: ");
                String newId = sc.next();
                System.out.print("Set a PIN: ");
                String newPin = sc.next();

                if (bank.registerUser(newId, newPin)) {
                    System.out.println("Account created successfully! You can now login.");
                } else {
                    System.out.println("User ID already exists. Try another one.");
                }

            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}

class Transaction {
    String type;
    double amount;
    Date date;
    Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    public String toString() {
        return date + " - " + type + " : " + amount;
    }
}

class User {
    String userId;
    String pin;
    double balance;
    ArrayList<Transaction> history = new ArrayList<>();

    User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    boolean validate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }
}

class Bank {
    HashMap<String, User> users = new HashMap<>();

    User getUser(String userId) {
        return users.get(userId);
    }

    boolean registerUser(String userId, String pin) {
        if (users.containsKey(userId)) {
            return false;
        }
        users.put(userId, new User(userId, pin, 0));
        return true;
    }
}

class ATMOperations {
    Scanner sc = new Scanner(System.in);

    void showMenu(User user, Bank bank) {
        while (true) {
            System.out.println("\n==== ATM Menu ====");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showHistory(user);
                    break;
                case 2:
                    withdraw(user);
                    break;
                case 3:
                    deposit(user);
                    break;
                case 4:
                    transfer(user, bank);
                    break;
                case 5:
                    System.out.println("Your balance: " + user.balance);
                    break;
                case 6:
                    System.out.println("Thank you for using ATM!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void showHistory(User user) {
        if (user.history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : user.history) {
                System.out.println(t);
            }
        }
    }

    void withdraw(User user) {
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();
        if (amt <= user.balance) {
            user.balance -= amt;
            user.history.add(new Transaction("Withdraw", amt));
            System.out.println("Withdrawal successful. New balance: " + user.balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    void deposit(User user) {
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();
        user.balance += amt;
        user.history.add(new Transaction("Deposit", amt));
        System.out.println("Deposit successful. New balance: " + user.balance);
    }

    void transfer(User user, Bank bank) {
        System.out.print("Enter recipient userId: ");
        String receiverId = sc.next();
        User receiver = bank.getUser(receiverId);

        if (receiver == null) {
            System.out.println("Receiver not found!");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amt = sc.nextDouble();

        if (amt <= user.balance) {
            user.balance -= amt;
            receiver.balance += amt;
            user.history.add(new Transaction("Transfer to " + receiverId, amt));
            receiver.history.add(new Transaction("Transfer from " + user.userId, amt));
            System.out.println("Transfer successful. New balance: " + user.balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}
