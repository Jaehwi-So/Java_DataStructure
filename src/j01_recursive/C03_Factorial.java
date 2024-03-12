package j01_recursive;

public class C03_Factorial {


    // 3 -> 3 * 2 * 1
    // 4 -> 4 * 3 * 2 * 1
    int recursiveFactorial(int n){
        if(n == 1){
            return n;
        }
        return n * recursiveFactorial(n - 1);
    }

    int iterativeFactorial(int n){

        int current = 1;

        for(int i = 2; i <= n; i++){
            current *= i;
        }

        return current;
    }

    public static void main(String[] args) {
        C03_Factorial cls = new C03_Factorial();
        System.out.println(cls.recursiveFactorial(5));
        System.out.println(cls.iterativeFactorial(5));
    }

}
