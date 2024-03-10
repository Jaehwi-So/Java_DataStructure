package j01_Recursive;

public class C02_Fib {

    //3 -> 1, 1, 2
    //4 -> 1, 1, 2, 3
    //5 -> 1, 1, 2, 3, 5
    int recursiveFib(int n){
        if(n <= 1){
            return n;
        }
        else{
            return recursiveFib(n - 1) + recursiveFib(n - 2);
        }
    }

    int iterativeFib(int n){

        int prev = 1;
        int current = 1;

        for(int i = 3; i <= n; i++){
            int tmp = prev + current;
            prev = current;
            current = tmp;
        }

        return current;
    }

    public static void main(String[] args) {
        C02_Fib cls = new C02_Fib();
        System.out.println(cls.recursiveFib(5));
        System.out.println(cls.iterativeFib(5));
    }
}
