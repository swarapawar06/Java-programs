import java.util.Scanner;

public class StudentInfo {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name= sc.nextLine();

        System.out.println("Enter your age: ");
        int age = sc.nextInt();

        System.out.println("Enter your CPI: ");
        double cpi = sc.nextDouble();

        System.out.println("Hello " + name +" , age: " +age+ ", CPI: " +cpi);

        sc.close();
    }
    
}
