package sort.mytest;

public class BubbleSort {


    //选择排序
    public static void bubbleSort(int arr[]) {

        for (int i = arr.length-1; i>0; i--) {
            for (int j = 0; j <i; j++) {
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private static  void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
        arr[i]=arr[i]^arr[j];
        arr[j]= arr[j]^arr[i];
        arr[i]= arr[j]^arr[i];
    }

    public static void main(String[] args) {
        int arr[] = new int[]{1, 8,5,2, 4, 3};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
