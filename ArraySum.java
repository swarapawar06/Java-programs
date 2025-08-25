public class SumArray {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 7, 9};
        int sum = 0;
        
        for (int num : arr) {
            sum += num;
        }
        
        System.out.println("Sum of array elements: " + sum);
    }
}
