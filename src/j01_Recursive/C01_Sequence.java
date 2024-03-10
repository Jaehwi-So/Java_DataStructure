package j01_Recursive;

public class C01_Sequence {
    int recursiveSeq(int n, int v){
        if(n == 1){
            return 1;
        }
        else{
            return recursiveSeq(n - 1, v) + v;
        }
    }


    int iterativeSeq(int n, int v){
        int result = 1;

        for(int i = 2; i <= n; i++){
            result += v;
        }

        return result;
    }

    public static void main(String[] args) {
        C01_Sequence cls = new C01_Sequence();
        System.out.println(cls.recursiveSeq(5, 3));
        System.out.println(cls.iterativeSeq(5, 3));
    }
}
