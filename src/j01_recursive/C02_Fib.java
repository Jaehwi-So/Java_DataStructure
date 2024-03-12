package j01_recursive;

import java.util.HashMap;
import java.util.Map;

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

    Map mem = new HashMap<Integer, Integer>();

    int recursiveFibWithMemorize(int n){
        if(n <= 1){
            return n;
        }
        else{
            if(mem.containsKey(n)){
                return (int)mem.get(n);
            }
            int res = recursiveFib(n - 1) + recursiveFib(n - 2);
            mem.put(n, res);
            return res;
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

        long startTime = System.nanoTime();
        System.out.println(cls.recursiveFib(10));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("재귀 : 걸린 시간 " + duration);

        startTime = System.nanoTime();
        System.out.println(cls.iterativeFib(10));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("반복 : 걸린 시간 " + duration);

        startTime = System.nanoTime();
        System.out.println(cls.recursiveFibWithMemorize(10));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("재귀 리팩토링 : 걸린 시간 " + duration);

    }
}
