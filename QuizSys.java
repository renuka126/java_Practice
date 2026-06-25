import java.util.Scanner;

public class QuizSys {
    static User liveUser = new User("Shinchan", "Cho_chip", "Nobita");
    static Scanner sc = new Scanner(System.in);
    static boolean LoggedIn = false;

    public static void main(String[] args) {
        boolean continueProgram = true;

        while (continueProgram) {
            if (!LoggedIn) {
                login();
            } else {
                showMenu();
            }

            if (!LoggedIn) {
                System.out.print("\nDo you want to reattempt the quiz? (yes/no): ");
                String answer = sc.nextLine().trim().toLowerCase();
                if (!answer.equals("yes")) {
                    System.out.println("\nThanks for attempting the quiz... Hope you enjoyed!");
                    continueProgram = false;
                }
            }
        }
    }

    public static void login() {
        System.out.println("\n--- Quiz System ---");
        System.out.print("Enter Username: ");
        String inputUser = sc.nextLine();
        System.out.print("Enter Password: ");
        String inputPass = sc.nextLine();

        if (inputUser.equals(liveUser.username) && inputPass.equals(liveUser.password)) {
            LoggedIn = true;
            System.out.println("\nLogin Successful! Welcome, " + liveUser.name + "!");
        } else {
            System.out.println("\nInvalid credentials. Try again.");
        }
    }

    public static void showMenu() {
        System.out.println("\n--- Welcome ---");
        System.out.println("User: " + liveUser.name);
        System.out.println("1. Attempt Quiz");
        System.out.println("2. Update Profile");
        System.out.println("3. Change Password");
        System.out.println("4. Logout");
        System.out.print("Enter choice (1-4): ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return;
        }

        switch (choice) {
            case 1 -> startQuiz();
            case 2 -> updateProfile();
            case 3 -> updatePassword();
            case 4 -> logout();
            default -> System.out.println("Invalid choice! Please try again.");
        }
    }

    public static void updateProfile() {
        System.out.print("Enter new name: ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) {
            liveUser.name = newName;
            System.out.println("Profile updated successfully!");
        } else {
            System.out.println("Name can't be empty.");
        }
    }

    public static void updatePassword() {
        System.out.print("Enter old password: ");
        String oldPass = sc.nextLine();

        if (!oldPass.equals(liveUser.password)) {
            System.out.println("Incorrect password!");
            return;
        }

        System.out.print("Enter new password: ");
        String newPass = sc.nextLine().trim();
        if (!newPass.isEmpty()) {
            liveUser.password = newPass;
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("New password can't be empty.");
        }
    }

    public static void startQuiz() {
        String[] questions = {
            "1. What is the capital of India?\n a) Mumbai  b) Delhi  c) Kolkata",
            "2. Who is Father of computer?\n a) Charles Babbage  b) Alan Turing  c) Tim Berners-Lee",
            "3. Which is the largest ocean on Earth?\n a) Atlantic Ocean  b) Pacific Ocean  c) Indian Ocean",
            "4. What is the chemical symbol for water?\n a) CO2  b) H2O  c) O2",
            "5. What is the national bird of India?\n a) Peacock  b) Sparrow  c) Eagle",
            "6. Which is a programming language?\n a) Java  b) Apple  c) Windows",
            "7. Who is known as Father of Java?\n a) James Gosling  b) Dennis Ritchie  c) Bill Gates",
            "8. Which planet is known as the Red Planet?\n a) Venus  b) Mars  c) Jupiter",
            "9. Which company made the first iPhone?\n a) Apple  b) Samsung  c) Nokia"
        };

        char[] answers = {'b', 'a', 'b', 'b', 'a', 'a', 'a', 'b', 'a'};
        char[] userAnswers = new char[questions.length];
        int timeLimit = 70; // seconds
        long startTime = System.currentTimeMillis();

        System.out.println("\nQuiz started! You have " + timeLimit + " seconds.\n");

        for (int i = 0; i < questions.length; i++) {
            long elapsed = (System.currentTimeMillis() - startTime) / 1000;
            if (elapsed >= timeLimit) {
                System.out.println(" Time's up!");
                break;
            }

            System.out.println(questions[i]);
            System.out.print("Your answer (a/b/c): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.isEmpty() || input.length() > 1 || "abc".indexOf(input.charAt(0)) == -1) {
                System.out.println("Invalid input. Moving to next question.");
                userAnswers[i] = ' ';
            } else {
                userAnswers[i] = input.charAt(0);
            }
        }

        int score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (userAnswers[i] == answers[i]) {
                score++;
            }
        }

        System.out.println("\n Quiz Submitted! Your Score: " + score + "/" + answers.length);
    }

    public static void logout() {
        System.out.println("\nYou have been logged out, " + liveUser.name + ". See you again!");
        LoggedIn = false;
    }
}

class User {
    String username;
    String password;
    String name;

    User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}