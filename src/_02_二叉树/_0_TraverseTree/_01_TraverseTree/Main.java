package _02_二叉树._0_TraverseTree._01_TraverseTree;

import _02_二叉树._01_BinarySearchTree.BinarySearchTree;
import _0_Tools.TreePrinter.BinaryTrees;

/**
 * @ClassName Main
 * @Description 演示二叉树遍历
 * @Author StarLee
 * @Date 2021/11/19
 */

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            tree.add((int)(Math.random()*30));
        }
        BinaryTrees.print(tree);
        System.out.println("\n" + "===============================");
        f1(tree);
        System.out.println();
        f2(tree);
    }

    public static void f1(BinarySearchTree<Integer> tree){
        TraverseTree<Integer> traverseTree = new TraverseTree<>(tree);
        System.out.println("递归实现: ");
        System.out.print("前序遍历: ");
        traverseTree.preorderTraversal();
        System.out.print("\n中序遍历: ");
        traverseTree.inorderTraversal();
        System.out.print("\n后序遍历: ");
        traverseTree.postorderTraversal();
    }

    public static void f2(BinarySearchTree<Integer> tree){
        TraverseTree1<Integer> traverseTree1 = new TraverseTree1<>(tree);
        System.out.println("迭代实现: ");
        System.out.print("前序遍历: ");
        traverseTree1.preorderTraversal();
        System.out.print("\n中序遍历: ");
        traverseTree1.inorderTraversal();
        System.out.print("\n后序遍历: ");
        traverseTree1.postorderTraversal();
    }
}
