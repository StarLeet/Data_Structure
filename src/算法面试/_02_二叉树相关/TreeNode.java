package 算法面试._02_二叉树相关;

/**
 * @ClassName TreeNode
 * @Description 树节点定义
 * @Author StarLee
 * @Date 2021/11/20
 */

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
