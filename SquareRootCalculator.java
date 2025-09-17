import java.util.Scanner;

public class SquareRootCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter a number to find its square root: ");
        double number = scanner.nextDouble();

        if (number < 0) {
            System.out.println("Square root of a negative number is not a real number.");
        } else {
            
            double squareRoot = Math.sqrt(number);
            System.out.println("Square root of " + number + " is: " + String.format("%.4f", squareRoot));
        }

        scanner.close();
    }
}
