package _02_二叉树.TraverseTree._02_TraversePlus;

import _02_二叉树.BinarySearchTree.BinarySearchTree;
import _0_Tools.TreePrinter.BinaryTrees;

/**
 * @ClassName Main
 * @Description 演示遍历二叉树增强
 * @Author StarLee
 * @Date 2021/11/19
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        // 访问器遍历
        Integer date[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BinarySearchTree<Integer> mytree = new BinarySearchTree<>();
        TraversePlus<Integer> traversePlus = new TraversePlus<>(mytree);
        for (int i = 0; i < date.length; i++) {
            mytree.add(date[i]);
        }
        BinaryTrees.println(mytree);

        System.out.print("层次遍历：");
        traversePlus.levelOrder(new TraversePlus.Visitor<Integer>() {
            @Override       //重写遍历的逻辑,可以是所有节点相加(元素是int)也可以是同时扩大
            public void visit(Integer element) {
                System.out.print("_" + element + "_ ");
            }
        });
        System.out.println();
        System.out.print("前序遍历：");
        traversePlus.preOrderTraversal(new TraversePlus.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.print("_" + element + "_ ");
            }
        });
        System.out.println();
        System.out.print("中序遍历：");
        traversePlus.inorderTraversal(new TraversePlus.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.print("_" + element + "_ ");
            }
        });
        System.out.println();
        System.out.print("后序遍历：");
        traversePlus.postorderTraversal(new TraversePlus.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.print("_" + element + "_ ");
            }
        });
    }
}
