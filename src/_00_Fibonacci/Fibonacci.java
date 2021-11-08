package _00_Fibonacci;

public class Fibonacci {
    /**
     *      //索引 0 1 2 3 4 5 6 7  8  9  10
     *      //数值 0 1 1 2 3 5 8 13 21 34 55
     * */
    public static int fibonacci_1(int num){  /* 递归实现,时间复杂度为O(2^n) */
        if(num<=1) return num;
        return fibonacci_1(num-2) + fibonacci_1(num-1);
    }

    public static int fibonacci_2(int num){  /*  迭代实现时间复杂度为O(n)  */
        if(num<=1) return num;
        int first = 0;
        int second = 1;
        //为何条件是num-1？
        /**   假设num为4:
           索引 0 1 2 3 4             我们所要进行的计算是fibo(2)=fibo(0)+fibo(1)
           数值 0 1 1 2 3             fibo(3) = fibo(1) + fibo(2)
                                     fibo(4) = fibo(2) + fibo(3)
                                一个fibo(x),一次加运算,也就是second += first
                                所以要想计算出fibo(num),除了fibo(0)与fibo(1)不需要加运算,其他的都需要,
                                那么[2,num]间有几个fibo(x)就有几个加运算, 从2,3,4,...,n共有n-1个
         */
        for (int i = 0; i < num-1; i++) {  // 0与1时for循环不进行,跟fibonacci_1中的if 有异曲同工之妙
            second += first;
            first = second - first;
        }
        return second;
    }
}
