package 算法面试._02_二叉树相关;

import _02_二叉树._01_BinarySearchTree.BinarySearchTree;
import _0_Tools.TreePrinter.BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName _226_翻转二叉树
 * @Description https://leetcode-cn.com/problems/invert-binary-tree/
 * @Author StarLee
 * @Date 2021/11/20
 */
@SuppressWarnings("all")
public class _226_翻转二叉树 {  // 翻转二叉树的核心在于反转各个子树,所以只要是遍历算法都能实现
    /**
    *  @MethodName invertTree
    *  @Description  后序遍历实现
    *  @Param [node]
    *  @return void
    */
    public static void invertTree(BinarySearchTree.Node<Integer> node){
        if (node == null) return;
        invertTree(node.left);
        invertTree(node.right);
        BinarySearchTree.Node<Integer> tmpNode = node.left;
        node.left = node.right;
        node.right = tmpNode;
    }
    /**
    *  @MethodName invertTree1
    *  @Description  中序遍历实现
    *  @Param [node]
    *  @return void
    */
    public static void invertTree1(BinarySearchTree.Node<Integer> node){
        if (node == null) return;
        invertTree(node.left);
        BinarySearchTree.Node<Integer> tmpNode = node.left;
        node.left = node.right;
        node.right = tmpNode;
        invertTree(node.left); //因为上面进行了值对换,所以node.left其实是之前的node.right
    }
    /**
    *  @MethodName invertTree2
    *  @Description  层序遍历实现(非递归)
    *  @Param [node]
    *  @return void
    */
    public static void invertTree2(BinarySearchTree.Node<Integer> node){
        if (node == null) return;
        Queue<BinarySearchTree.Node<Integer>> queue = new LinkedList<>();
        queue.offer(node);

        BinarySearchTree.Node<Integer> tmpNode = null;
        BinarySearchTree.Node<Integer> tmpNode1 = null;
        while(!queue.isEmpty()){
            tmpNode = queue.poll();
            tmpNode1 = tmpNode.left;
            tmpNode.left = tmpNode.right;
            tmpNode.right = tmpNode1;
            if (tmpNode.left != null) queue.offer(tmpNode.left);
            if (tmpNode.right != null) queue.offer(tmpNode.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 5; i++) {
            tree.add((int)(Math.random()*20));
        }
        BinaryTrees.println(tree);
        System.out.println("===============");
        invertTree2((BinarySearchTree.Node<Integer>) tree.root());
        BinaryTrees.println(tree);
    }
}
