package _08_并查集.UnionFind._01_QuickUnion;

/**
 * @ClassName _03_UF_QU_R_PH
 * @Description  Quick Union - 基于rank的优化 - 路径减半(Path Halving)
 * @Author StarLee
 * @Date 2021/12/18
 */

public class _03_UF_QU_R_PH extends _01_UF_QU_R {
    public _03_UF_QU_R_PH(int capacity) {
        super(capacity);
    }

    public int find(int v){
        rangeCheck(v);
        while(v != parents[v]){
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
