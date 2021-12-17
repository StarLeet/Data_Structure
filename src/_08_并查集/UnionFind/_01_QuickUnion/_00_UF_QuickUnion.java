package _08_并查集.UnionFind._01_QuickUnion;

import _08_并查集.UnionFind.UnionFind;

/**
 * @ClassName UF_QuickUnion
 * @Description  并查集——快合并实现
 * @Author StarLee
 * @Date 2021/12/18
 */

public class _00_UF_QuickUnion extends UnionFind {
    public _00_UF_QuickUnion(int capacity) {
        super(capacity);
    }
    /**
     * 通过parent链条不断往上找，直到找到根节点
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while(v != parents[v]){
            v = parents[v];
        }
        return v;
    }
    /**
     * 将v1的根节点嫁接到v2的根节点上
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if(p1 == p2) return;
        parents[p1] = p2;
    }
}
