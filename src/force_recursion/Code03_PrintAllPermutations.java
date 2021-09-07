package force_recursion;

import java.util.ArrayList;


//打印全排列


public class Code03_PrintAllPermutations {

	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process(chs, 0, res);
		res.sort(null);
		return res;
	}
	/**
	 * 限制为26个英文字母
	 *第i步考虑第i+1步，所有结果存到list中，每个结果都是一个string
	 */
	public static void process(char[] chs, int i, ArrayList<String> res) {
		//下表越界，则添加当前结果到list中
		if (i == chs.length) {
			res.add(String.valueOf(chs));
		}
		//用26个bool值表示哪些字符出现过，0~25
		boolean[] visit = new boolean[26];
		//将i与i到length-1分别交换，如果有些字符出现过，则忽略，交完后继续处理i+1的逻辑，完后恢复交换前的数据
		for (int j = i; j < chs.length; j++) {
			if (!visit[chs[j] - 'a']) {
				visit[chs[j] - 'a'] = true;
				swap(chs, i, j);
				process(chs, i + 1, res);
				swap(chs, i, j);
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	

}
