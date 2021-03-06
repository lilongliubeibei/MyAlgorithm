package force_recursion;


//纸牌游戏，只能从数组的两端取一张  先手后手
public class Code02_CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    
    //先拿一张牌的最大方案
    public static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    //后拿一张牌的最大方案
    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        //前一个人会选 Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));、
        //假设前者大，arr[i]被选走，剩下 i+1到j   则为f(arr, i + 1, j)  
        //假设后者大，arr[j]被选走，剩下 i到j+1   则为f(arr, i , j+ 1)
        //
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }


    public static void main(String[] args) {
        int[] arr = {1, 9, 5,6,8,4,1};
        System.out.println(win1(arr));
         System.out.println(win2(arr));

    }
}
