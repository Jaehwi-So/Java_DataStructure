package j01_Recursive;

public class C04_HanoiTower {


    void hanoi(int n, String source, String dest, String temp){
        if(n == 1){
            System.out.println("N : " + n + " , Move : " + source + "->" + dest);
            return;
        }
        hanoi(n - 1, source, temp, dest);  // Source -> Temp
        System.out.println("N : " + n + " , Move : " + source + "->" + dest);
        hanoi(n - 1, temp, dest, source); // Temp -> Dest
    }



    public static void main(String[] args) {
        C04_HanoiTower cls = new C04_HanoiTower();
        cls.hanoi(3, "A", "C", "B");

    }
    
}
