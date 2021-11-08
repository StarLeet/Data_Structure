package UseForTest;

public class 原型模式测试 {
    public static void main(String[] args) throws CloneNotSupportedException {
            Citation c1 = new Citation();
            c1.setName("张三");

            //复制奖状
            Citation c2 = c1.clone();
            //将奖状的名字修改李四
            c2.setName("李四");
        Student student = new Student("张三");
        Student student1 = new Student("张三");
        student1.name = student.name;
        student1.setName("李四");
        System.out.println(student.name);
        System.out.println(student1.name);
    }
}


class Citation implements Cloneable {
    public String name;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return (this.name);
    }
    public void show() {
        System.out.println(name + "同学：在2020学年第一学期中表现优秀，被评为三好学生。特发此状！");
    }

    @Override
    public Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }
}

class Student{
    public String name;

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
