package _02_二叉树._02_BBST._01_AVLTree;

import _02_二叉树._00_BinaryTree.BinaryTree;
import _02_二叉树._0_TraverseTree._02_TraversePlus.TraversePlus;
import _0_Tools.TreePrinter.BinaryTrees;

/**
 * @ClassName Main
 * @Description  演示AVL树的功能
 * @Author StarLee
 * @Date 2021/12/2
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        test1();
//        test2();
    }

    /**
     * 演示AVL树添加再平衡
     */
    public static void test1(){
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < 20; i++) {
            tree.add(100 + i);
            BinaryTrees.println(tree);
            System.out.println("--------------------------");
        }

        tree.traverseTree(new BinaryTree.TraverseMode() {
            @Override
            public void myTraverse(BinaryTree tree) {
                TraversePlus<Integer> traversePlus = new TraversePlus<Integer>(tree);
                traversePlus.inorderTraversal(new TraversePlus.Visitor<Integer>() {
                    @Override
                    public boolean visit(Integer element) {
                        System.out.print(element + " ");
                        if (element == 116) return true;
                        return false;
                    }
                });
            }
        });
    }
    /**
     * 演示AVL树删除再平衡
     */
    public static void test2(){
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < 20; i++) {
            tree.add(100 + i);
        }
        for (int i = 0; i < 20; i++) {
            tree.remove(100 + i);
            BinaryTrees.println(tree);
            System.out.println("------------------------");
        }
    }
}
