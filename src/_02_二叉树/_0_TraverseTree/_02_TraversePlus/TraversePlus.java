package _02_二叉树._0_TraverseTree._02_TraversePlus;


import _02_二叉树._00_BinaryTree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName TraversePlus
 * @Description 增强遍历二叉树的逻辑(加入访问器,可以自定义遍历二叉树的用途)
 * @Author StarLee
 * @Date 2021/11/19
 */
@SuppressWarnings("all")
public class TraversePlus<E> {
    private final BinaryTree<E> myTree;

    public TraversePlus(BinaryTree<E> myTree) {
        this.myTree = myTree;
    }

    /**
     * 访问器接口 ——> 访问器抽象类
     * 增强遍历接口,用来控制遍历访问元素时的行为与如何停止
     */
    public static abstract class Visitor<E> {
        boolean stop;  //停止标记,遍历到指定元素时,为true
        abstract boolean visit(E element); // 如果返回true，就代表停止遍历
        // visit用来书写遍历逻辑,可以是遍历元素值+1,总之不局限于打印元素了
    }

    /**
     * 先序遍历（递归）
     */
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal((BinaryTree.Node<E>)myTree.root(), visitor);
    }
    private void preorderTraversal(BinaryTree.Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历（递归）
     */
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal((BinaryTree.Node<E>)myTree.root(), visitor);
    }
    private void inorderTraversal(BinaryTree.Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        inorderTraversal(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历（递归）
     */
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal((BinaryTree.Node<E>)myTree.root(), visitor);
    }
    private void postorderTraversal(BinaryTree.Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层次遍历（队列实现）
     */
    public void levelOrderTraversal(Visitor<E> visitor) {
        if (myTree.root() == null || visitor == null) return;

        Queue<BinaryTree.Node<E>> queue = new LinkedList<>();
        queue.offer((BinaryTree.Node<E>)myTree.root());

        while (!queue.isEmpty()) {
            BinaryTree.Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
}
