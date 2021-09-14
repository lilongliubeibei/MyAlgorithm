package class04;

import java.util.LinkedList;


//窗口只能右边界或左边界向右滑的情况下，维持窗口内部最大值或者最小值快速更新的结
//		构 单次O(1)
//		窗口内最大值与最小值更新结构的原理与实现
public class Code01_SlidingWindowMaxArray {

	
	//最小值为w
	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
				//弹出队列尾值所有小于当前值的 小标
				qmax.pollLast();
			}
			//将当前下标加到队列中
			qmax.addLast(i);
			//i-w+1表示窗口左边界
			if (qmax.peekFirst() == i - w) {
				//如果最大值是窗口外最左一个，则弹出最大值
				qmax.pollFirst();
			}
			//从第w个数开始，输出窗口最大值
			if (i >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 40, 30, 50, 40, 30, 30, 60, 70 };
		int w = 3;
		printArray(getMaxWindow(arr, w));

	}

}
