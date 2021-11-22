package _02_二叉树.TraverseTree._02_TraversePlus;


import _02_二叉树.BinaryTree;

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
     * 增强遍历接口
     */
    public static interface Visitor<E>{
        void visit(E element);
    }

    /**
     * 访问器的遍历
     */
    // 层次遍历
    public void levelOrder(Visitor<E> visitor){
        if(myTree.root() == null || visitor == null) return;
        Queue<BinaryTree.Node<E>> queue = new LinkedList<>(); // 队列
        queue.offer((BinaryTree.Node<E>) myTree.root());

        while(!queue.isEmpty()){
            BinaryTree.Node<E> node = queue.poll();
            visitor.visit(node.element);
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }
    // 前序遍历
    public void preOrderTraversal(BinaryTree.Node<E> node, Visitor<E> visitor){
        if(node == null || visitor == null) return;
        visitor.visit(node.element);
        preOrderTraversal(node.left, visitor);
        preOrderTraversal(node.right, visitor);
    }
    public void preOrderTraversal(Visitor<E> visitor){
        preOrderTraversal((BinaryTree.Node<E>) myTree.root(), visitor);
    }
    // 中序遍历
    public void inorderTraversal(BinaryTree.Node<E> node, Visitor<E> visitor){
        if(node == null || visitor == null) return;
        inorderTraversal(node.left, visitor);
        visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }
    public void inorderTraversal(Visitor<E> visitor){
        inorderTraversal((BinaryTree.Node<E>) myTree.root(), visitor);
    }
    // 后序遍历
    public void postorderTraversal(BinaryTree.Node<E> node, Visitor<E> visitor){
        if(node == null || visitor == null) return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        visitor.visit(node.element);
    }
    public void postorderTraversal(Visitor<E> visitor){
        postorderTraversal((BinaryTree.Node<E>) myTree.root(), visitor);
    }
}
