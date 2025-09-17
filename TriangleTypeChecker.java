import java.util.Scanner;

public class TriangleTypeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter side A: ");
        int a = scanner.nextInt();

        System.out.print("Enter side B: ");
        int b = scanner.nextInt();

        System.out.print("Enter side C: ");
        int c = scanner.nextInt();

        
        if (a + b > c && a + c > b && b + c > a) {
            
            if (a == b && b == c) {
                System.out.println("The triangle is Equilateral.");
            } else if (a == b || b == c || a == c) {
                System.out.println("The triangle is Isosceles.");
            } else {
                System.out.println("The triangle is Scalene.");
            }
        } else {
            System.out.println("The given sides do not form a valid triangle.");
        }

        scanner.close();
    }
}
