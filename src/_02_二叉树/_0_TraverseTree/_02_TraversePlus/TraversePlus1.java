package _02_二叉树._0_TraverseTree._02_TraversePlus;

import _02_二叉树._00_BinaryTree.BinaryTree;

import java.util.Stack;

/**
 * @ClassName TraversePlus1
 * @Description  非递归遍历增强
 * @Author StarLee
 * @Date 2021/12/8
 */
@SuppressWarnings("all")
public class TraversePlus1<E> {
    private final BinaryTree<E> myTree;

    public TraversePlus1(BinaryTree<E> myTree) {
        this.myTree = myTree;
    }

    public static abstract class Visitor<E> {
        public abstract boolean visit(E element); // 如果返回true，就代表停止遍历
        // visit用来书写遍历逻辑,可以是遍历元素值+1,总之不局限于打印元素了
    }

    public void preorder2(Visitor<E> visitor) {
        if (visitor == null || myTree.root() == null) return;
        Stack<BinaryTree.Node<E>> stack = new Stack<>();
        stack.push((BinaryTree.Node<E>) myTree.root());
        while (!stack.isEmpty()) {
            BinaryTree.Node<E> node = stack.pop();
            // 访问node节点
            if (visitor.visit(node.element)) return;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public void preorder(Visitor<E> visitor) {
        if(visitor == null || myTree.root() == null) return;
        BinaryTree.Node<E> node = (BinaryTree.Node<E>) myTree.root();
        Stack<BinaryTree.Node<E>> stack = new Stack<>();
        while(true){
            if(node != null){
                // 访问 node 节点
                if(visitor.visit(node.element)) return;
                // 右子节点入栈
                if(node.right != null){
                    stack.push(node.right);
                }
                node = node.left;
            }else if(stack.isEmpty()){
                return;
            }else{
                // 处理右边
                node = stack.pop();
            }
        }
    }

    public void inorder(Visitor<E> visitor) {
        if(visitor == null || myTree.root() == null) return;
        BinaryTree.Node<E> node = (BinaryTree.Node<E>)myTree.root();
        Stack<BinaryTree.Node<E>> stack = new Stack<>();
        while(true){
            if(node != null){
                stack.push(node);
                // 向左走
                node = node.left;
            }else if(stack.isEmpty()){
                return;
            }else{
                node = stack.pop();
                // 访问 node 节点
                if(visitor.visit(node.element)) return;
                // 让右节点进行中序遍历
                node = node.right;
            }

        }
    }

    public void postorder(Visitor<E> visitor) {
        if (visitor == null || myTree.root() == null) return;
        // 记录上一次弹出访问的节点
        BinaryTree.Node<E> prev = null;
        Stack<BinaryTree.Node<E>> stack = new Stack<>();
        stack.push((BinaryTree.Node<E>)myTree.root());
        while (!stack.isEmpty()) {
            BinaryTree.Node<E> top = stack.peek();
            if (top.isLeaf() || (prev != null && prev.parent == top)) {
                prev = stack.pop();
                // 访问节点
                if (visitor.visit(prev.element)) return;
            } else {
                if (top.right != null) {
                    stack.push(top.right);
                }
                if (top.left != null) {
                    stack.push(top.left);
                }
            }
        }
    }
}
