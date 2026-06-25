import java.util.Scanner;
import java.util.Random;
import java.lang.Class;
public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.println("Number Guess game");
        System.out.println(" ");

        int totalScore = 0;
        int round = 1;

        System.out.println(" Ready to Start  Guessing the Numbers !");
        System.out.println("You have 7 attempts to guess a number between 1 and 100.");

        boolean playmore = true;

        while (playmore) {
            int Guessing_num = rand.nextInt(100) + 1;
            int attempts = 0;
            boolean hasGuessed = false;

            System.out.println("\n Round " + round);

            while (attempts < 7) {
                System.out.print("Enter your guess (1 to 100): ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == Guessing_num) {
                    System.out.println("Correct! gussed the number in " + attempts+ "attempt");
                    int score = 100 - (attempts - 1) * 10;
                    totalScore += score;
                    System.out.println(" You earned " + score + " points this round.");
                    hasGuessed = true;
                    break;
                } else if (userGuess < Guessing_num) {
                    System.out.println("Too low!");
                } else {
                    System.out.println(" Too high!");
                }
            }

            if (!hasGuessed) {
                System.out.println("All atempts finished. The correct number was " + Guessing_num);
            }

            System.out.println("Total Score: " + totalScore);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = sc.next().toLowerCase();

            if (!response.equals("yes")) {
                playmore = false;
                System.out.println(" Thanks for playing! Final Score: " + totalScore);
            } else {
                round++;
            }
        }

        sc.close();
    }     
}        

