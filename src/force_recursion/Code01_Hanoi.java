package force_recursion;


//汉诺塔问题
public class Code01_Hanoi {

	// public static void hanoi(int n) {
	// if (n > 0) {
	// func(n, n, "left", "mid", "right");
	// }
	// }

	// public static void func(int rest, int down, String from, String help, String
	// to) {
	// if (rest == 1) {
	// System.out.println("move " + down + " from " + from + " to " + to);
	// } else {
	// func(rest - 1, down - 1, from, to, help);
	// func(1, down, from, help, to);
	// func(rest - 1, down - 1, help, from, to);
	// }
	// }

	public static void hanoi(int n) {
		if (n == 0) {
			return;
		}
		process(n, "left", "right", "mid");
	}

	private static void process(int n, String from, String to, String other) {
		if (n <= 1) {
			System.out.println("move " + n + " from " + from + " to " + to);
			return;
		}
		//其余n-1从左到中，借助右
		process(n - 1, from, other, to);
		//第n个从左移动到右
		System.out.println("move " + n + " from " + from + " to " + to);
		//其余n-1个从中到右，借助左
		process(n - 1, other, to, from);

	}

	public static void main(String[] args) {
		int n = 3;
		hanoi(n);
	}

}
