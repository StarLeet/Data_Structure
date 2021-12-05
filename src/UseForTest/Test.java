package UseForTest;

import _02_二叉树._01_BinarySearchTree.BinarySearchTree;

/**
 * @ClassName Test
 * @Description
 * @Author StarLee
 * @Date 2021/11/21
 */

public class Test {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50_000; i++) {
            tree.add(1000 + i);
        }
        long end = System.currentTimeMillis();
        System.out.println((double)(end - start)/1000);
    }
}

