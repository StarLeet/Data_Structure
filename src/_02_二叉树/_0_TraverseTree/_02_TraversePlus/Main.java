package _02_二叉树._0_TraverseTree._02_TraversePlus;

import _02_二叉树._01_BinarySearchTree.BinarySearchTree;
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
        traversePlus.levelOrderTraversal(new TraversePlus.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print("_" + element + "_ ");
                return element == 11 ? true : false;
            }
        });
        System.out.println();
        System.out.print("前序遍历：");
        traversePlus.preorderTraversal(new TraversePlus.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print("_" + element + "_ ");
                return element == 9 ? true : false;
            }
        });
        System.out.println();
        System.out.print("中序遍历：");
        traversePlus.inorderTraversal(new TraversePlus.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print("_" + element + "_ ");
                return element == 9 ? true : false;
            }
        });
        System.out.println();
        System.out.print("后序遍历：");
        traversePlus.postorderTraversal(new TraversePlus.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print("_" + element + "_ ");
                return element == 9 ? true : false;
            }
        });
    }
}
