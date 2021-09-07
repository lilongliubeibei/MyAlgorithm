package mytest.sort;

public class BSNearLeft {

    // 在arr上，找满足>=value的最左位置
    public static int nearestIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        int temp = 0;
        // 0  1   2    2
        while (left < right) {
            int mid = left + (right - left) >> 1;
            if (arr[mid] >= value) {
                temp = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (arr[left] == value) {
            temp = left;
        }
        return temp;
    }

    public static void main(String[] args) {
        int []arr=new int[]{1,2,3};
        int i = nearestIndex(arr, 3);
        System.out.println(i);
    }
}
