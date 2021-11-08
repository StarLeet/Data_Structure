package 线性表._01_DynamicArray;

public class Person {
    private String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {  //对象回收前发出的遗讯
        System.out.println("对象回收");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
