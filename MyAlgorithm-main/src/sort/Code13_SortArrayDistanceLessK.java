package sort;

import java.util.PriorityQueue;

public class Code13_SortArrayDistanceLessK {


	//已知一个几乎有序的数组,如果排好序,每个元素的移动距离不超过k,并且k相对于数组长度是比较小的
	public void sortedArrDistanceLessK(int[] arr, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();

		int index = 0;
		for (; index < Math.min(arr.length, k); index++) {
			heap.add(arr[index]);
		}
		int i = 0;
		for (; index < arr.length; i++, index++) {
			heap.add(arr[index]);
			arr[i] = heap.poll();
		}
		while (!heap.isEmpty()) {
			arr[i++] = heap.poll();
		}
	}
}
