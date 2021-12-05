package _03_集合Set._02_TreeSet;

import _03_集合Set.Set;

/**
 * @ClassName Main
 * @Description
 * @Author StarLee
 * @Date 2021/12/2
 */

public class Main {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(12);
        treeSet.add(10);
        treeSet.add(7);
        treeSet.add(11);
        treeSet.add(10);
        treeSet.add(11);
        treeSet.add(9);

        treeSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
    }
}
