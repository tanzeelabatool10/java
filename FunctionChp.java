import java.util.Scanner;
public class FunctionChp {


    public static void printHello() {
        System.out.println("Hello, World!");
    }

    public static int calculateSum(int num1, int num2) {//parameter or formsl parameters
        
        int sum = num1 + num2;
        return sum;
    }
    public static void main(String[] args) {
        printHello();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int num1 = sc.nextInt();
        System.out.print("Enter second number: ");
        int num2 = sc.nextInt();
        int sum = calculateSum(num1, num2); //arguments or actual parameters
        System.out.println("sum is : " + sum);
        



    }

    
}