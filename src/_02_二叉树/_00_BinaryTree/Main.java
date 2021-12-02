package _02_二叉树._00_BinaryTree;

import _02_二叉树._01_BinarySearchTree.BinarySearchTree;
import _02_二叉树._0_TraverseTree._01_TraverseTree.TraverseTree;
import _02_二叉树._0_TraverseTree._01_TraverseTree.TraverseTree1;
import _0_Tools.TreePrinter.BinaryTrees;

/**
 * @ClassName Main
 * @Description  演示二叉树中遍历方法的封装
 * @Author StarLee
 * @Date 2021/11/24
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            tree.add((int)(Math.random()*300));
        }
        BinaryTrees.println(tree);
        tree.traverseTree(new BinaryTree.TraverseMode() {
            @Override
            public void myTraverse(BinaryTree tree) {
                TraverseTree1<Integer> traverseTree1 = new TraverseTree1<>(tree);
                System.out.println("迭代实现: ");
                System.out.print("前序遍历: ");
                traverseTree1.preorderTraversal();
                System.out.print("\n中序遍历: ");
                traverseTree1.inorderTraversal();
                System.out.print("\n后序遍历: ");
                traverseTree1.postorderTraversal();

                System.out.println("\n=====================");
                TraverseTree<Integer> traverseTree = new TraverseTree<>(tree);
                System.out.println("递归实现: ");
                System.out.print("前序遍历: ");
                traverseTree.preorderTraversal();
                System.out.print("\n中序遍历: ");
                traverseTree.inorderTraversal();
                System.out.print("\n后序遍历: ");
                traverseTree.postorderTraversal();
            }
        });
    }
}
