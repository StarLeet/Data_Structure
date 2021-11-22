package 算法面试._02_二叉树相关;

import _02_二叉树.BinarySearchTree.BinarySearchTree;
import _0_Tools.TreePrinter.BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName _104_二叉树的高度
 * @Description  https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @Author StarLee
 * @Date 2021/11/20
 */
@SuppressWarnings("all")
public class _104_二叉树的高度 {

    public static int treeHeight(BinarySearchTree<?> tree){
        int height = 0;
        height = countHeight1((BinarySearchTree.Node<?>) tree.root());
        return height;
    }

    /**
     *  @MethodName countHeight
     *  @Description  递归计算子树高度,求子树最大高度+1得到整棵树高度
     *  @Param [node]
     *  @return int
     */
    public static int countHeight(BinarySearchTree.Node<?> node){
        if (node == null) return 0;
        return 1 + Math.max(countHeight(node.left),countHeight(node.right));
    }

    /**
    *  @MethodName countHeight1
    *  @Description  层次遍历(队列实现),计算树的高度
    *  @Param [node]
    *  @return int
    */
    public static int countHeight1(BinarySearchTree.Node<?> node){
        if (node == null) return 0;
        Queue<BinarySearchTree.Node<?>> queue = new LinkedList<>();
        int levelSize = 1; // 存储每一层的元素数量
        int height = 0;  // 树的高度
        queue.offer(node);

        BinarySearchTree.Node<?> tmpNode = null;
        while (!queue.isEmpty()){
            tmpNode = queue.poll();  //队头出队
            levelSize--;
            if (tmpNode.left != null) queue.offer(tmpNode.left); //左孩子入队
            if (tmpNode.right != null) queue.offer(tmpNode.right); //右孩子入队
            if (levelSize == 0){  // 即将要访问下一层
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 20; i++) {
            tree.add((int)(Math.random()*20));
        }
        BinaryTrees.println(tree);
        System.out.println(treeHeight(tree));
    }
}
