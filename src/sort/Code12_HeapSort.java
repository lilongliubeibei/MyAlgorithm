package sort;

import java.util.Arrays;

public class Code12_HeapSort {



	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) { // 边界条件
			return;
		}

		for (int i = 0; i < arr.length; i++) { // 初始化heap
			heapInsert(arr, i);
		}
		int size = arr.length;
		// swap(arr, 0, --size);
		while (size > 0) {
			swap(arr, 0, --size);
			heapify(arr, 0, size);
		}

	}

	private static void heapInsert(int[] arr, int i) {
		while (arr[(i - 1) / 2] < arr[i]) {
			swap(arr, i, (i - 1) / 2);
			i = (i - 1) / 2;
		}
	}

	public static void heapify(int[] arr, int i, int size) {
		int largest = i;
		while ((2 * i + 1) < size) {
			largest = arr[i] < arr[2 * i + 1] ? 2 * i + 1 : i;
			// System.out.println("first " + largest);
			largest = (2 * i + 2) < size && arr[2 * i + 2] > arr[largest] ? 2 * i + 2 : largest;
			// System.out.println(largest);
			if(largest == i) {			// 注意在发现当前节点就是子节点中最大时，退出循环
				break;
			}
			swap(arr, i, largest);
			i = largest;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		if (i == j) {
			return;
		}

		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			heapSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		heapSort(arr);
		printArray(arr);
	}

}
