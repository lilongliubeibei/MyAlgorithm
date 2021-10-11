package class03;


//O(m+n)
public class Code01_KMP {


//	KMP算法解决的问题
//	字符串str1和str2，str1是否包含str2，如果包含返回str2在str1中开始的位置。
//	如何做到时间复杂度O(N)完成？
	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = m.toCharArray();
		int i1 = 0;
		int i2 = 0;
		int[] next = getNextArray(str2);
		while (i1 < str1.length && i2 < str2.length) {
			if (str1[i1] == str2[i2]) {
				i1++;
				i2++;
			} else if (next[i2] == -1) {
				i1++;
			} else {
				i2 = next[i2];
			}
		}
		return i2 == str2.length ? i1 - i2 : -1;
	}


    // next[i]的求解方法是，找到从t[0]~t[i-1]的公共最长匹配前缀和后缀的长度
    public static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str.length];
        //定义
        next[0] = -1;
        //定义
        next[1] = 0;
        int i = 2;
        int tmp = 0;
        //从i=2开始求，
        while (i < next.length) {
            if (str[i - 1] == str[tmp]) {
                next[i++] = ++tmp;
            } else if (tmp > 0) {
                tmp = next[tmp];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));

    }

    
    //暴力解法
    /**

     * 暴力破解法

     * @param ts 主串

     * @param ps 模式串

     * @return 如果找到，返回在主串中第一个字符出现的下标，否则为-1

     */

    public static int bf(String ts, String ps) {

        char[] t = ts.toCharArray();

        char[] p = ps.toCharArray();

        int i = 0; // 主串的位置

        int j = 0; // 模式串的位置

        while (i < t.length && j < p.length) {

            if (t[i] == p[j]) { // 当两个字符相同，就比较下一个

                i++;

                j++;

            } else {

                i = i - j + 1; // 一旦不匹配，i后退

                j = 0; // j归0

            }

        }

        if (j == p.length) {

            return i - j;

        } else {

            return -1;

        }

    }
}
