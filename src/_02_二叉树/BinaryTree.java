package _02_二叉树;

import _02_二叉树.BinarySearchTree.BinarySearchTree;
import _0_Tools.TreePrinter.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BinaryTree
 * @Description
 * @Author StarLee
 * @Date 2021/11/23
 */
@SuppressWarnings("all")
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected BinarySearchTree.Node<E> root; // 根节点

    public static class Node<E> {
        public E element; // 元素值
        public Node<E> left; // 左节点
        public Node<E> right; // 右节点
        public Node<E> parent; // 父节点

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
        public boolean isLeaf(){ // 是否叶子节点
            return left==null && right==null;
        }
        public boolean hasTwoChildren(){ // 是否有两个子节点
            return left!=null && right!=null;
        }
    }

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
        Node<E> myNode = (Node<E>)node;
        String parentStr = "null";
        if(myNode.parent != null){
            parentStr = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentStr + ")";
    }

    /**
     * 元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空所有的元素
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     *  @MethodName: isComplete
     *  @Description: 判断是否是完全二叉树
     *  @Param:
     *  @return: boolean
     */
    public boolean isComplete(){
        if(root == null){
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while(!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(leaf && !node.isLeaf()){ // 要求是叶子结点，但是当前节点不是叶子结点
                return false;
            }
            if(node.left != null){
                queue.offer(node.left);
            }else if(node.right != null){
                // node.left==null && node.right!=null
                return false;
            }
            if(node.right != null){
                queue.offer(node.right);
            }else{  //node.right==null
                leaf = true; // 要求后面都是叶子节点
            }
        }
        return true;
    }

    /**
     * 求前驱节点
     */
    protected Node<E> predecessor(Node<E> node) {
        if(node == null) return null;

        // 前驱节点在左子树中(left.right.right.right....)
        if(node.left != null ){ // 左子树不为空,则找到它的最右节点
            Node<E> p = node.left;
            while(node.right != null){
                p = p.right;
            }
            return p;
        }
        // 能来到这里说明左子树为空
        // 当父节点不为空，则顺着父节点找，直到找到【该结点为父节点的右节点】时
        while(node.parent != null && node.parent.left==node){
            node = node.parent;
        }
        // node.parent == null	无前驱
        // node.parent.right == node 该节点为根结点的右节点
        return node.parent;
    }
    /**
     * 求后继节点
     */
    protected Node<E> successor(Node<E> node) {
        if(node == null) return null;

        if(node.right != null){ // 存在右节点
            Node<E> p = node.right;
            while(p.left != null){
                p = p.left;
            }
            return p;
        }

        while(node.parent!=null && node.parent.right==node){
            node = node.parent;
        }

        // node.parent == null
        // node.parent.left == node
        return node.parent;
    }
}
