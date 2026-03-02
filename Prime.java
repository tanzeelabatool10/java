// 

//Optimized code to check if a number is prime or not
public  static boolean IsPrime(int){
    for(int i=2; i<=Math.sqrt(n); i++){
        if(n%i==0){
            return false;
        }
    }
    return true;

    public static void main(String[] args) {
        int n = 29;
        if(IsPrime(n)){
            System.out.println(n + " is a prime number.");
        } else {
            System.out.println(n + " is not a prime number.");
        }
    }
}                  