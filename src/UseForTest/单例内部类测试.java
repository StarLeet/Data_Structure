package UseForTest;

/**
 * @ClassName 单例内部类测试
 * @Description  展示外部类的加载不会导致静态内部类一起加载,静态内部类加载亦不会导致外部类加载
 * @Author StarLee
 * @Date 2021/12/4
 */

public class 单例内部类测试 {
    public static void main(String[] args) {
        //new A_();   //演示外部类加载
        //A_.c();   //演示外部类加载
        //new A_.InnerA();  //演示静态内部类加载
    }
}

class A_ {
    static {
        System.out.println("外部类被加载");
    }
     public A_(){
        System.out.println("A_被创建");
    }

    static class InnerA{
        static {
            System.out.println("静态内部类被加载");
        }
    }

    public static void c(){
        System.out.println("C被调用");
    }
}