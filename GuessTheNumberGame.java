import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int totalScore = 0;
        int round = 1;
        final int MAX_ATTEMPTS = 7;
        final int MAX_NUMBER = 100;

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     NUMBER GUESSING GAME     ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("Guess a number between 1 and " + MAX_NUMBER);
        System.out.println("You have " + MAX_ATTEMPTS + " attempts per round.\n");

        boolean playMore = true;

        while (playMore) {
            int secretNumber = rand.nextInt(MAX_NUMBER) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("──────────────────────────────");
            System.out.println("  ROUND " + round);
            System.out.println("──────────────────────────────");

            while (attempts < MAX_ATTEMPTS) {
                int remaining = MAX_ATTEMPTS - attempts;
                System.out.print("Attempts left: " + remaining + " | Your guess: ");

                int userGuess;
                try {
                    userGuess = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("  Invalid input! Enter a number between 1 and " + MAX_NUMBER);
                    continue;
                }

                if (userGuess < 1 || userGuess > MAX_NUMBER) {
                    System.out.println("  Out of range! Enter a number between 1 and " + MAX_NUMBER);
                    continue;
                }

                attempts++;

                if (userGuess == secretNumber) {
                    System.out.println("\n  Correct! You guessed it in " + attempts + " attempt(s)!");
                    int score = Math.max(10, 100 - (attempts - 1) * 15);
                    totalScore += score;
                    System.out.println("  Points earned this round : " + score);
                    System.out.println("  Total Score              : " + totalScore);
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("  Too Low!  Try higher.");
                } else {
                    System.out.println("  Too High! Try lower.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\n  Out of attempts! The number was: " + secretNumber);
                System.out.println("  Total Score: " + totalScore);
            }

            System.out.print("\nPlay another round? (yes/no): ");
            String response = sc.nextLine().trim().toLowerCase();

            if (response.equals("yes") || response.equals("y")) {
                round++;
            } else {
                playMore = false;
                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║         GAME OVER!           ║");
                System.out.println("║  Rounds Played : " + String.format("%-12d", round) + "║");
                System.out.println("║  Final Score   : " + String.format("%-12d", totalScore) + "║");
                System.out.println("╚══════════════════════════════╝");
                System.out.println("Thanks for playing!");
            }
        }

        sc.close();
    }
}