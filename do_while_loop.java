import java.util.Scanner;

public class do_while_loop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int secretNumber = 7;
        int guess;

        do {
            System.out.print("Guess the number: ");
            guess = scanner.nextInt();

            if (guess != secretNumber) {
                System.out.println("Wrong! Try again.");
            }
        } while (guess != secretNumber);

        System.out.println("Congratulations! You guessed the number.");
        scanner.close();
    }
}

