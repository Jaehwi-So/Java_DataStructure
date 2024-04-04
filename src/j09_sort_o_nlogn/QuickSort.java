package j09_sort_o_nlogn;

public class QuickSort {

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
    static int partition(int[] arr, int left, int right) {
        int low = left + 1;
        int high = right;

        //배열의 첫번째(혹은 끝) 인덱스를 pivot으로 설정
        int pid = left;
        int pivot = arr[pid];


        //low 인덱스가 high 인덱스를 넘어가기 전까지 루프 진행
        while(low <= high){
            //Pivot의 왼쪽에는 작은 값이, 오른쪽에는 큰 값이 오도록 정렬하기 위해서 조건에 맞지않는 원소들을 찾아내서 교환할 것임

            while(low <= right && arr[low] < pivot){
                low++;  //pivot보다 큰 값이 나올 때까지 -> 방향으로 이동
            }
            while(high >= left && arr[high] > pivot){
                high--; //pivot보다 작은 값이 나올 때 까지 <- 방향으로 이동
            }


            //low와 high가 역전되지 않았고, 교환할 원소들을 찾았을 때
            if(low <= high){
                swap(arr, low, high);
                low++;
                high--;
            }

        }
        // low high 위치가 역전되면 high와 pivot의 위치를 교환 -> Pivot의 왼쪽에는 작은 값이, 오른쪽에는 큰 값이 오도록 정렬됨
        swap(arr, pid, high);
        return high; // 변경된 Pivot의 위치(right) 반환


    }

    static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            print(arr);
            int pivot = partition(arr, left, right);  // 퀵 정렬 Phase를 통해 정렬된 Pivot의 위치
            quickSort(arr, left, pivot - 1); // Pivot의 왼쪽을 다시 퀵 정렬 (Pivot보다 작은 값들임)
            quickSort(arr, pivot + 1, right); // Pivot의 오른쪽을 다시 퀵 정렬 (Pivot보다 큰 값들임)
        }
    }

    static void sort(int arr[]){
        quickSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {3, 2, 1 ,5, 7, 9, 6, 4};
        sort(array);
        print(array);


    }
}
