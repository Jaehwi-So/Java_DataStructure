package j09_sort_o_n2;

public class BubbleSort {

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


    // 끝부터 차례로 정렬된다.
    static void sort(int arr[]){
        for(int i = 0; i < arr.length - 1; i++){
            print(arr);
            for(int j = 0; j < arr.length -i - 1; j++){
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j+ 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {7, 4, 5, 1, 3};
        sort(array);
        print(array);

    }
}
