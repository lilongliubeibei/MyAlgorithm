package class03;

//
//Manacher算法解决的问题
//		字符串str中，最长回文子串的长度如何求解？
//		如何做到时间复杂度O(N)完成？
public class Code02_Manacher {


/*	首先，Manacher算法提供了一种巧妙地办法，
	将长度为奇数的回文串和长度为偶数的回文串一起考虑，具体做法是，在
	原字符串的每个相邻两个字符中间插入一个分隔符，同
	时在首尾也要添加一个分隔符，分隔符的要求是不在原串中出现，一般情况下可以用#号。*/
	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}   

	public static int maxLcpsLength(String str) {
		if (str == null || str.length() == 0) {
			return 0; 
		}
		char[] charArr = manacherString(str);
		//记录每个位置的最大回文半径
		int[] pArr = new int[charArr.length];
		//最有回文中心
		int C = -1;
		/*回文范围=回文直径=2*回文半径=从一个位置为中心出发扩出来的回文范围
		最右回文边界：从左往右扩，用一个变量记录最右的边界R，忽略是由哪个字符开始扩的
		在此需要记录一个使得右边界更新到更右的中心C。该变量与左右回文边界是伴生的。
		每一个位置扩出来的回文半径记录在一个数组里*/ 
		
		//最右回文边界
		int R = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i != charArr.length; i++) {

			//1、i在最右回文边界外，直接暴力括
			//2、i在最右回文边界内，i'的回文在最左回文边界内， 则i的回文半径和i'一样
			//2、i在最右回文边界内，i'的回文在最左回文边界外， 则i的回文半径从R-i开始
			// Math.min(pArr[2 * C - i], R - i)返回的是最大加速区域
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max - 1;
	}

	public static void main(String[] args) {
		String str1 = "abc1234321ab";
		System.out.println(maxLcpsLength(str1));
	}

}
