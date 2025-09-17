import java.util.*;

public class AreaOfCircle {
    public static void main(String[] args) {
        final float PI = 3.14f; // constant
        float Area, radius; // variables
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the radius of the circle");
        radius = sc.nextInt();

        Area = PI * radius * radius;

        System.out.println("Radius" + radius);
        System.out.println("Area of the circle is" + Area);

        sc.close();
    }

}