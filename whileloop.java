import java.util.Scanner;
public class whileloop {
    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Variable to store the sum of positive integers
        int sum = 0;

        // Variable to store the user input
        int number;

        // Loop until the user enters a non-positive number
        while (true) {
            System.out.print("Enter a number: ");
            number = scanner.nextInt();

            // If the number is less than or equal to 0, break the loop
            if (number <= 0) {
                break;
            }

            // Add the number to the sum if it's positive
            sum += number;
        }

        // Display the total sum of positive integers entered
        System.out.println("Sum of positive integers: " + sum);

        // Close the scanner to prevent resource leak
        scanner.close();
    }
}

