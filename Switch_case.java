import java.util.Scanner;

public class Switch_case{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.println("Enter second number: ");
        double num2 = sc.nextDouble();

        System.out.println("Choose operation: ");
        System.out.println("1. Addition");
        System.out.println("2. Substraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Addition: " + (num1 + num2));
                break;

            case 2:
                System.out.println("Substraction: "+(num1 - num2));
                break;

            case 3:
                System.out.println("Multiplication: " + (num1 * num2));
                break;

            case 4:
                if (num2 != 0) {
                    System.out.println("Division: " + (num1 / num2));
                } else {
                    System.out.println("Error: Division by zero!"); 
                }
                break;
            default:
                System.out.println("Invalid choice");
            }

            sc.close();
    }
}