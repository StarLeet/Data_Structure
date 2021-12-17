package _08_并查集;

import _08_并查集.UnionFind.UnionFind;
import _08_并查集.UnionFind._01_QuickUnion.*;
import _08_并查集.UnionFind._02_GenericUnionFind.GenericUnionFind;
import _0_Tools.CountTime;
import _0_Tools.MyAssert;

/**
 * @ClassName Main
 * @Description
 * @Author StarLee
 * @Date 2021/12/18
 */

public class Main {
    static final int count = 100_0000;

    public static void main(String[] args) {
//		testTime(new UF_QuickFind(count)); // 太慢了,不测试
//		testTime(new _00_UF_QuickUnion(count)); // 太慢了,不测试
        testTime(new _05_UF_QU_S(count));
        testTime(new _01_UF_QU_R(count));
        testTime(new _02_UF_QU_R_PC(count));
        testTime(new _04_UF_QU_R_PS(count));
        testTime(new _03_UF_QU_R_PH(count));
        testTime(new GenericUnionFind<Integer>());

//		GenericUnionFind<Student> uf = new GenericUnionFind<>();
//		Student stu1 = new Student(1, "jack");
//		Student stu2 = new Student(2, "rose");
//		Student stu3 = new Student(3, "jack");
//		Student stu4 = new Student(4, "rose");
//		uf.makeSet(stu1);
//		uf.makeSet(stu2);
//		uf.makeSet(stu3);
//		uf.makeSet(stu4);
//
//		uf.union(stu1, stu2);
//		uf.union(stu3, stu4);
//
//		uf.union(stu1, stu4);
//
//		Asserts.test(uf.isSame(stu2, stu3));
//		Asserts.test(uf.isSame(stu3, stu4));
//		Asserts.test(!uf.isSame(stu1, stu3));
    }

    static void testTime(GenericUnionFind<Integer> uf) {
        for (int i = 0; i < count; i++) {
            uf.makeSet(i);
        }

        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        MyAssert.test(!uf.isSame(2, 7));

        uf.union(4, 6);

        MyAssert.test(uf.isSame(2, 7));

        CountTime.test(uf.getClass().getSimpleName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int)(Math.random() * count),
                        (int)(Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int)(Math.random() * count),
                        (int)(Math.random() * count));
            }
        });
    }

    static void testTime(UnionFind uf) {
        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        MyAssert.test(!uf.isSame(2, 7));
        uf.union(4, 6);
        MyAssert.test(uf.isSame(2, 7));

        CountTime.test(uf.getClass().getSimpleName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int)(Math.random() * count),
                        (int)(Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int)(Math.random() * count),
                        (int)(Math.random() * count));
            }
        });
    }
}
