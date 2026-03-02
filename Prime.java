public static boolean isPrime(int num) {
    if (num <= 1) {
        return false; // 0 and 1 are not prime numbers
    }
    if(num == 2) {
        return true; // 2 is the only even prime number
    }
    for (int i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
            return false; // num is divisible by a number other than 1 and itself
        }
    }
    return true; // num is prime
}

public static void main(String[] args) {
    int number = 7; // Example number to check
    if (isPrime(number)) {
        System.out.println(number + " is a prime number.");
    } else {
        System.out.println(number + " is not a prime number.");
    }
}