package class03;


//O(m+n)
public class Code01_KMP {
    //求s中第一次出现m的位置
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
                //如果next数组退到0了且str1[i1] != str2[i2]，name i1++
            } else if (next[i2] == -1) {
                i1++;
            } else {
            	//从m字符串的  next[i2]位置开始比
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }


    // next[i]的求解方法是，找到从t[0]~t[i-1]的公共最长匹配前缀和后缀的长度
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        //定义
        next[0] = -1;
        //定义
        next[1] = 0;
        int i = 2;
        int cn = 0;
        //从i=2开始求，
        while (i < next.length) {
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
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

}
