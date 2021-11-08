package UseForTest;

public class StaticMethod {
    public static void main(String[] args) {
        A a = new A();
        a.a();
        System.out.println("a     的hashCode: "+ a);
    }
}

class A{
    private int a=10;
    public void a(){
        class B_{
            private int a = 1;
            private void b(){
                System.out.println("B内的b");
            }
        }
        B_ b = new B_();

        System.out.println("b.a = "+b.a);
        System.out.println("A.this.a = "+A.this.a);
        System.out.println("A.this的hashCode: "+A.this);//A.this与a的hashCode相同
    }
}