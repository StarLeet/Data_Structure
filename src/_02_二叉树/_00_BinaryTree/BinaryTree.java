package _02_二叉树._00_BinaryTree;

import _0_Tools.TreePrinter.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BinaryTree
 * @Description  二叉树类(贯穿树的开始)
 * @Author StarLee
 * @Date 2021/11/23
 */
@SuppressWarnings("all")
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size; 	// 节点个数量
    protected Node<E> root; // 根结点

    /**
     * 二叉树节点类
     */
    public static class Node<E> {
        public E element;		// 元素值
        public Node<E> left;	// 左子节点
        public Node<E> right;	// 右子节点
        public Node<E> parent;	// 父亲节点
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        //判断是否是叶子节点
        public boolean isLeaf() { // 是否为叶子结点
            return left == null && right == null;
        }
        //判断是否有两个孩子(度为2)
        public boolean hasTwoChildren() { // 是否有两个子节点
            return left != null && right != null;
        }
        //判断是否是父节点的左孩子
        public boolean isLeftChild() { // 是否为左节点
            return parent != null && this == parent.left;
        }
        //判断是否是父节点的右孩子
        public boolean isRightChild() { // 是否为右节点
            return parent != null && this == parent.right;
        }

        public Node<E> sibling() { // 返回兄弟节点
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /***
     * 遍历模式实现接口,实现run方法可自行选择遍历方式
     * @param
     */
    public static interface TraverseMode<E>{
         public abstract void myTraverse(BinaryTree<E> tree);
    }
    public void traverseTree(TraverseMode traversemode){
        traversemode.myTraverse(this);
    }
    /**
     * 判断是否为完全二叉树(层序遍历实现)
     */
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;
            //leaf为true时,后续遍历的节点只能是叶子节点

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                // node.left==null && node.right!=null
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // 2. 能进来这里说明 node.right == null
                //    后面遍历的节点都必须是叶子节点
                leaf = true;
            }
        }

        return true;
    }
    /**
     * 求树的高度（非递归）  队列 + 层序遍历
     */
    public int height() {
        if (root == null) return 0;

        // 树的高度
        int height = 0;
        // 存储着每一层的元素数量
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) { // 意味着即将要访问下一层
                levelSize = queue.size(); // 核心
                height++;
            }
        }

        return height;
    }

    /**
     * 求树的高度（递归）
     */
    public int height2() {
        return height(root);
    }
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    /**
     * 创造节点
     * AVL树 与 B数 的节点各自有其特性
     * 因此在 BinaryTree 中提供一个方法让他们去覆盖
     */
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    /**
     * 前驱节点：中序遍历中的前一个节点
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（left.right.right.right....）
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        // 能来到这里说明左子树为空
        // 当父节点不为空，则顺着父节点找，直到找到【某结点为父节点的右子节点】时
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        // 来到这里有以下两种情况：
        // node.parent == null  无前驱，说明是根结点
        // node == node.parent.right一直不被满足 【某结点为父节点的右子节点】
        return node.parent;
    }

    /**
     * 后继节点：中序遍历中的后一个节点
     * 写法与前驱节点正好相反
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        // 来到这里说明没有右节点
        // 当父节点不为空，则顺着父节点找，直到找到【某结点为父节点的左子节点】时
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        // 来到这里有以下两种情况：
        // node.parent == null   无前驱，说明是根结点
        // node.parent.left == node一直不被满足 【某结点为父节点的左子节点】
        return node.parent;
    }
    /**
     * BinaryTreeInfo 工具类实现,以便打印工具可以运行
     */
    @Override
    public Object root() {
        return root;
    }
    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }
    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }
    @Override
    public Object string(Object node) {
        return node;
//        Node<E> myNode = (Node<E>)node;
//        String parentStr = "null";
//        if(myNode.parent != null){
//            parentStr = myNode.parent.element.toString();
//        }
//        return myNode.element + "_p(" + parentStr + ")";
    }
}