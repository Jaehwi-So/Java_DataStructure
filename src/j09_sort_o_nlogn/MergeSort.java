package j09_sort_o_nlogn;

import java.util.Arrays;

public class MergeSort {

    static void print(int arr[], int left, int right){
        for(int i = 0; i < arr.length; i++){
            if(i == left){
                System.out.print("[");
            }
            System.out.print(arr[i]);
            if(i == right){
                System.out.print("] ");
            }
            else{
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    static void merge(int arr[], int left, int mid, int right){

        int[] left_arr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] right_arr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0;  //왼쪽 배열 검사 인덱스
        int j = 0;  //오른쪽 배열 검사 인덱스
        int k = left;  //합병중인 배열의 현재 인덱스


        while (i < left_arr.length && j < right_arr.length) {
            if (left_arr[i] <= right_arr[j]) {
                arr[k++] = left_arr[i++];
            }
            else {
                arr[k++] = right_arr[j++];
            }
        }

        while (i < left_arr.length) {
            arr[k++] = left_arr[i++];
        }

        while (j < right_arr.length) {
            arr[k++] = right_arr[j++];
        }

        print(arr, left, right);
    }

    static void mergeSort(int[] arr, int left, int right){
        //left와 right로 분할 가능하다면
        if(left < right){
            int mid = (right + left) / 2;   //mid
            //mid를 기준으로 두 파트로 분할
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);   //분할한 결과 합병 (분할 정복)
        }
    }
    static void sort(int arr[]){
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {6, 5, 3, 1, 8, 7, 2, 4};
        print(array, -1, -1);
        sort(array);

    }
}
