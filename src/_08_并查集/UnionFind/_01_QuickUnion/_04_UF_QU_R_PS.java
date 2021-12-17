package _08_并查集.UnionFind._01_QuickUnion;

/**
 * @ClassName _04_UF_QU_R_PS
 * @Description  Quick Union - 基于rank的优化 - 路径分裂(Path Spliting)
 * @Author StarLee
 * @Date 2021/12/18
 */

public class _04_UF_QU_R_PS extends _01_UF_QU_R{
    public _04_UF_QU_R_PS(int capacity) {
        super(capacity);
    }

    public int find(int v){
        rangeCheck(v);
        while(v != parents[v]){
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return parents[v];
    }
}
