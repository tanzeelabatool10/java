public static void decToBin(int n) {
    int pow = 0;
    int binaryNum = 0;
    while (n > 0) {
        int remainder = n % 2;
        binaryNum += remainder * Math.pow(10, pow);
        pow++;

        n /= 2;
    }
    System.out.println(binaryNum);
}
public static void main(String[] args) {
    int n = 10;
    decToBin(n);
}