package force_recursion;

/*
  数字转A~Z字母，A和1对应，Z和26对应
  
 */
public class Code5_ConvertToLetterString {

	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	//从第i步分0，1，2三种情况考虑
	public static int process(char[] chs, int i) {
		if (i == chs.length) {
			return 1;
		}
		
		if (chs[i] == '0') {
			return 0;
		}
		if (chs[i] == '1') {
			int res = process(chs, i + 1);
			if (i + 1 < chs.length) {
				res += process(chs, i + 2);
			}
			return res;
		}
		if (chs[i] == '2') {
			//B
			int res = process(chs, i + 1);
			//uvwxyz 21~26 一种
			if (i + 1 < chs.length && (chs[i + 1] >= '0' && chs[i + 1] <= '6')) {
				res += process(chs, i + 2);
			}
			return res;
		}
		return process(chs, i + 1);
	}

	
	public static void main(String[] args) {
		System.out.println(number("21"));
	}

}
