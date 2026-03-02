import java.util.*;
public static void binToDec(int n) {
    int decimal = 0;
    int power = 1; // 2^0

    while (n > 0) {
        int lastDigit = n % 10; // Get the last digit
        decimal = decimal + (lastDigit * (int)Math.pow(2,power));// Add to the decimal value
        power++;
        n /= 10; // Remove the last digit
    }

    System.out.println("Decimal value: " + decimal);
}

public static void main(String[] args) {
    int binaryNumber = 1011; // Example binary number
    binToDec(binaryNumber);
}