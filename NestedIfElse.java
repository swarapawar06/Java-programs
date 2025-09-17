import java.util.Scanner;

public class NestedIfElse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the student's score (0-100): ");
        int score = scanner.nextInt();
        
        if (score >= 90) {
            System.out.println("Grade: A");
        } else {
            if (score >= 75) {
                System.out.println("Grade: B");
            } else {
                if (score >= 50) {
                    System.out.println("Grade: C");
                } else {
                    System.out.println("Grade: F");
                }
            }
        }
        
        scanner.close();
    }
}

