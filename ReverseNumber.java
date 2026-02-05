import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 5 digit number: ");
        int number = sc.nextInt();

        int reversed = reverseNumber(number);
        
        System.out.println("Original: " + number);
        System.out.println("Reversed: " + reversed);
    }

    public static int reverseNumber(int num) {
        int reservedNum = 0;

        while (num != 0) {
            reservedNum = reservedNum * 10 + num % 10;
            num /= 10;
        }

        return reservedNum;
    }
}
