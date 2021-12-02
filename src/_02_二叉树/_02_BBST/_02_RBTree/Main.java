package _02_二叉树._02_BBST._02_RBTree;

import _0_Tools.TreePrinter.BinaryTrees;

/**
 * @ClassName Main
 * @Description  测试红黑树代码
 * @Author StarLee
 * @Date 2021/11/28
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        testRemove();
    }

    /**
     *  @MethodName testAdd
     *  @Description  测试红黑树添加
     *  @Param []
     *  @return void
     */
    public static void testAdd(){
        Integer[] data = new Integer[] {
                55,87,56,74,96,22,62,20,70,68,90,50
        };
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.println(rbTree);
    }

    public static void testRemove(){
        Integer[] data = new Integer[] {
                55,87,56,74,96,22,62,20,70,68,90,50
        };
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.println(rbTree);
        System.out.println("-----------------------");
        for (int i = 0; i < data.length; i++) {
            rbTree.remove(data[i]);
            BinaryTrees.println(rbTree);
            System.out.println("-----------------------");
        }
    }
}
