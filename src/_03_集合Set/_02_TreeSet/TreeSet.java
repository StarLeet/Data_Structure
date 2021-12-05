package _03_集合Set._02_TreeSet;

import _02_二叉树._02_BBST._02_RBTree.RBTree;
import _02_二叉树._0_TraverseTree._02_TraversePlus.TraversePlus;
import _03_集合Set.Set;

/**
 * @ClassName TreeSet
 * @Description  红黑树实现集合
 * @Author StarLee
 * @Date 2021/12/2
 */

public class TreeSet<E> implements Set<E> {
    private final RBTree<E> tree = new RBTree<>();

    public int size() {
        return tree.size();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public void clear() {
        tree.clear();
    }

    public boolean contains(E element) {
        return tree.contains(element);
    }

    public void add(E element) {
        tree.add(element); // 红黑树自带去重
    }

    public void remove(E element) {
        tree.remove(element);
    }

    public void traversal(Visitor<E> visitor) {  // 用户传入的Set内的访问器
        TraversePlus<E> traversePlus = new TraversePlus<E>(tree);
        traversePlus.inorderTraversal(new TraversePlus.Visitor<E>() {
            @Override
            public boolean visit(E element) {   // 二叉树访问器的方法
                return visitor.visit(element);   //  将traversal()内的访问逻辑置换到外部传入Visitor的逻辑
            }
        });
    }
}
