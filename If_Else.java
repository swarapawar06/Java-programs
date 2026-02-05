import java.util.Scanner;

public class If_Else {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first subject marks: ");
        double a = sc.nextDouble();

        System.out.println("Enter second sunject marks: ");
        double b = sc.nextDouble();

        System.out.println("Enter third subject marks: ");
        double c = sc.nextDouble();

        System.out.println("Enter fourth subject marks: ");
        double d = sc.nextDouble();

        System.out.println("Enter fifth subject marks: ");
        double e = sc.nextDouble();

        double total = a + b + c + d + e;
        double percentage = total / 5;

        System.out.println("Total Marks = " +total);
        System.out.println("Total Percentage = " +percentage + "%");

        if (percentage >=90) {
            System.out.println("Grade: A");
        } else if (percentage >=80) {
            System.out.println("Grade: B");
        } else if (percentage >=70) {
            System.out.println("Grade: C");
        } else if (percentage >=35) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: Fail");
        }

        sc.close();

    }
}
