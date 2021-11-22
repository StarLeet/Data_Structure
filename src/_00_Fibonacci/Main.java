package _00_Fibonacci;

import _0_Tools.CountTime;

public class Main {
    public static void main(String[] args) {
        CountTime.test("递归实现斐波那契数列的时间开销",new CountTime.Task(){
           public void execute(){
               method_1(45);
            }
        });
        CountTime.test("迭代实现斐波那契数列的时间开销",new CountTime.Task(){
            public void execute(){
                method_2(45);
            }
        });
    }


    public static void method_1(int num){
        System.out.println("fibonacci(" + num + ") 's result is " + Fibonacci.fibonacci_1(num));
    }

    public static void method_2(int num){
        System.out.println("fibonacci(" + num + ") 's result is " + Fibonacci.fibonacci_2(num));
    }


}
