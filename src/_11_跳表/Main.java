package _11_跳表;

import _0_Tools.CountTime;
import _0_Tools.MyAssert;

import java.util.TreeMap;

/**
 * @ClassName Main
 * @Description
 * @Author StarLee
 * @Date 2022/1/1
 */

public class Main {
    public static void main(String[] args) {
        time();
    }
    private static void time() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        SkipList<Integer, Integer> list = new SkipList<>();
        int count = 100_0000;
        int delta = 10;

        CountTime.test("SkipList", () -> {
            test(list, count, delta);
        });

        CountTime.test("TreeMap", () -> {
            test(map, count, delta);
        });
    }

    public static void test(SkipList<Integer, Integer> list, int count, int delta) {
        for (int i = 0; i < count; i++) {
            list.put(i, i + delta);
        }
//        System.out.println(list);
        for (int i = 0; i < count; i++) {
            MyAssert.test(list.get(i) == i + delta);
        }
        MyAssert.test(list.size() == count);
        for (int i = 0; i < count; i++) {
            MyAssert.test(list.remove(i) == i + delta);
        }
        MyAssert.test(list.size() == 0);
    }

    private static void test(TreeMap<Integer, Integer> map, int count, int delta) {
        for (int i = 0; i < count; i++) {
            map.put(i, i + delta);
        }
        for (int i = 0; i < count; i++) {
            MyAssert.test(map.get(i) == i + delta);
        }
        MyAssert.test(map.size() == count);
        for (int i = 0; i < count; i++) {
            MyAssert.test(map.remove(i) == i + delta);
        }
        MyAssert.test(map.size() == 0);
    }
}
