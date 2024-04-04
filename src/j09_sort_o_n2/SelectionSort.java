package j09_sort_o_n2;

public class SelectionSort {

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
        for(int i = 0; i < arr.length - 1; i++){
            print(arr);
            int min = i;    // 현재 사이클에서 최소값
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    public static void main(String[] args) {
        int[] array = {6, 5, 3, 1, 8, 7, 2, 4};
        sort(array);
        print(array);


    }
}
