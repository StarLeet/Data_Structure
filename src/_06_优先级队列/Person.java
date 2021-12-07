package _06_优先级队列;

/**
 * @ClassName Person
 * @Description  测试类
 * @Author StarLee
 * @Date 2021/12/6
 */
@SuppressWarnings("unused")
public class Person implements Comparable<Person>{
    private final String name;
    private final int boneBreak;  //优先级
    public Person(String name, int boneBreak) {
        this.name = name;
        this.boneBreak = boneBreak;
    }

    @Override
    public int compareTo(Person person) {
        return this.boneBreak - person.boneBreak;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", boneBreak=" + boneBreak + "]";
    }
}
