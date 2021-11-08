package UseForTest;

public class 单例模型测试 {
    public static void main(String[] args) {
        System.out.println(Single.SINGLE.hashCode() == Single.SINGLE.hashCode());
    }
}


enum Single{
    SINGLE("默认",18);
    private final String name;
    private final int age;
    Single(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Single{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
