package _02_二叉树._02_BBST._01_AVLTree;

import _02_二叉树._02_BBST.BBST;

import java.util.Comparator;

/**
 * @ClassName AVLTree
 * @Description  AVL树完整代码
 * @Author StarLee
 * @Date 2021/11/24
 */

public class AVLTree<E> extends BBST<E> {
    // AVL树的节点，需要计算平衡因子，因此比普通二叉树多维护一个height属性
    // (将height放入普通二叉树里没有用处，浪费空间)
    private static class AVLNode<E> extends Node<E>{
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
        public int balanceFactor(){ // 获取该节点平衡因子
            int leftHeight = left==null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right==null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }
        public void updateHeight(){ // 更新高度
            int leftHeight = left==null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right==null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }
        public Node<E> tallerChild(){
            int leftHeight = left==null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right==null ? 0 : ((AVLNode<E>)right).height;
            if(leftHeight > rightHeight) return left;
            if(rightHeight > leftHeight) return right;
            // 高度一样则返回同方向的，左子节点则返回左，否则返回右
            return isLeftChild() ? left : right;
        }
        @Override
        public String toString() {
            String parentString = "null";
            if(parent != null){
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }

    public AVLTree(Comparator<E> comparator){
        super(comparator);
    }
    public AVLTree() {
        this(null);
    }

    /**
     * 增加节点后的调整
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {  //寻找最近的失衡祖父节点
            if(isBalanced(node)){ // 如果平衡
                // 寻找失衡节点的同时，顺带更新高度  (无需递归计算高度)
                updateHeight(node);
            }else{ // 如果不平衡
                // 恢复平衡
                rebalance(node);
                // 整棵树恢复平衡
                break;
            }
        }
    }
    /**
     * 删除节点后的调整
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 重写父类中的 createNode
     * 返回 AVLNode
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }
    /**
     * 判断传入节点是否平衡（平衡因子的绝对值 <= 1）
     */
    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }
    /**
     * 更新高度
     */
    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }
    /**
     * 恢复平衡
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance2(Node<E> grand){
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if(parent.isLeftChild()){//L
            if(node.isLeftChild()){//LL
                rotateRight(grand);//LL则右旋
            }else{//LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else{//R
            if(node.isLeftChild()){//RL
                rotateRight(parent);
                rotateLeft(grand);
            }else{//RR
                rotateLeft(grand);//RR则左旋
            }
        }
    }
    private void rebalance(Node<E> grand){
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if(parent.isLeftChild()){//L
            if(node.isLeftChild()){//LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            }else{//LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        }else{//R
            if(node.isLeftChild()){//RL
                rotate(grand, grand, node.left, node, node.right, parent);
            }else{//RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);
        // 更新高度的顺序由低到高就行
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }
}
