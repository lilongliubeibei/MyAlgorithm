package class06;

//求用户传入的相反数
public class Code03_AddMinusMultiDivideByBit {

    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;//无进位相加
            b = (a & b) << 1;//进位信息
            a = sum;
        }
        return sum;
    }


    //相反数,一个数取反加一    0010表示+2  1101+1=1110表示-2   1110的原码=1001+1=1010即-2，得出证明
    public static int negNum(int n) {
        return add(~n, 1);
    }

    //减一个数等于加上这个数的相反数
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    public static int multi(int a, int b) {
        int res = 0;
        //乘数不为0
        while (b != 0) {
        	//b的最低位是1
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            //高位的相乘
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    //是否为负
    public static boolean isNeg(int n) {
        return n < 0;
    }
    
	//除法递归实现,ab都为正数
	public static int division(int a,int b){
		int res;
		if(a<b){
			return 0;
		}else{
			res=division(minus(a, b), b)+1;
		}
		return res;
	}

    //除法，a/b
    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 31; i > -1; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("divisor is 0");
        }
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
            //如果b是最小值，答案为0
        } else if (b == Integer.MIN_VALUE) {
            return 0;
            //如果a是最小值, -2<sup>31</sup>.  b不是最小值  10000000  31个0
        } else if (a == Integer.MIN_VALUE) {
			//Java负数存储是以补码形式存储的（补码=反码+1）。所以反码=补码-1.即~n=-n-1=-(n+1)
        	//a加1 除以b 即1000001 /b
            int res = div(add(a, 1), b);
            //multi(res, b) 上述结果乘以b   minus(a, multi(res, b) 即 模
            return add(res, div(minus(a, multi(res, b)), b));
        } else {
            return div(a, b);
        }
    }

    public static void main(String[] args) {
//        int a = (int) (Math.random() * 100000) - 50000;
//        int b = (int) (Math.random() * 100000) - 50000;
//        System.out.println("a = " + a + ", b = " + b);
//        System.out.println(add(a, b));
//        System.out.println(a + b);
//        System.out.println("=========");
//        System.out.println(minus(a, b));
//        System.out.println(a - b);
//        System.out.println("=========");
//        System.out.println(multi(a, b));
//        System.out.println(a * b);
//        System.out.println("=========");
//        System.out.println(divide(a, b));
//        System.out.println(a / b);
//        System.out.println("=========");
//
//        a = Integer.MIN_VALUE;
//        b = 32;
//        System.out.println(divide(a, b));
//        System.out.println(a / b);
		int a = (int) (Math.random() * 100000) - 50000;
        int b = (int) (Math.random() * 100000) - 50000;
		System.out.println(a);
		System.out.println(b);
		System.out.println(division(a,b));
    }

}
