public class SwapNumbers {
    public static void main(String[] args) {
        int a = 2, b = 5;
        System.out.println("Before Swap: a = " + a + ", b = " + b);

        // Swapping using a temporary variable
        int temp = a;
        a = b;
        b = temp;

        System.out.println("After Swap: a = " + a + ", b = " + b);
    }
}
