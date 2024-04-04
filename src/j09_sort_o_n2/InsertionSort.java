package j09_sort_o_n2;

public class InsertionSort {

    static void print(int arr[]){
        for(int k : arr){
            System.out.print(k + " ");
        }
        System.out.println();
    }

    static void swap(int arr[], int i, int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    static void sort(int arr[]){
        for(int i = 1; i < arr.length; i++){
            print(arr);
            int target = i;    // 현재 사이클에서 정렬할 대상
            for(int j = i - 1; j >= 0; j--){
                if(arr[target] < arr[j]){
                    swap(arr, target, j);
                    target = j; // 타겟을 이동시킨 지점으로 타겟위치 지정
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {6, 5, 3, 1, 8, 7, 2, 4};
        sort(array);
        print(array);


    }
}
