package UseForTest;

import _02_二叉树._01_BinarySearchTree.BinarySearchTree;
import _0_Tools.TreePrinter.BinaryTrees;

/**
 * @ClassName Test
 * @Description
 * @Author StarLee
 * @Date 2021/11/21
 */

public class Test {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 5; i++) {
            tree.add(i + 2);
        }
        BinaryTrees.println(tree);
    }
}

