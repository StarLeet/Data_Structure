package _02_二叉树._01_BinarySearchTree;

import _0_Tools.TreePrinter.BinaryTrees;
import _0_Tools.TreePrinter.Files;

import java.util.Comparator;
@SuppressWarnings("all")
public class Main {
    public static class PersonComparator implements Comparator<Person> { // 比较器
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    public static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    // 测试添加Integer类型的数据
    public static void test1(){
        Integer date[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.println(bst);
    }

    // 测试添加Person类型的数据
    public static void test2(){
        // Java，匿名类
        BinarySearchTree<Person> bst = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person e1, Person e2) {
                return e1.getAge() - e2.getAge();
            }
        });
        Integer date[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        for (int i = 0; i < date.length; i++) {
            bst.add(new Person(date[i]));
        }
        BinaryTrees.println(bst);
    }

    // 打印结果写入指定文本
    public static void test3(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i = 0; i < 40; i++){
            bst.add((int)(Math.random()*100));
        }
        String string = BinaryTrees.printString(bst);
        string += "\n";
        Files.writeToFile("F:/1.txt", string);
    }

    // add() 时值相等的处理
    public static void test4(){
        BinarySearchTree<Person> bst = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        bst.add(new Person(15, "jack"));
        bst.add(new Person(16, "rose"));
        bst.add(new Person(10, "jerry"));
        bst.add(new Person(10, "kali")); // add()时值相等最好覆盖，否则则不会替换
        BinaryTrees.println(bst);
    }

    // 是否是完全二叉树
    public static void test6(){
        /*
         *    7
         *  4   8
         *1   5
         *
         */
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer date[] = new Integer[] { 7, 4, 8, 1, 5};
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

    // 是否包含某个结点
    public static void test7(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer date[] = new Integer[] { 7, 4, 8, 1, 5};
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.println(bst);
        System.out.println(bst.contains(6));
    }
    // 删除节点
    public static void test8(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer date[] = new Integer[] { 7, 4, 8, 1, 5};
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.println(bst);
        bst.remove(8);
        BinaryTrees.println(bst);
    }
    public static void main(String[] args) {
        test6();
    }
}
