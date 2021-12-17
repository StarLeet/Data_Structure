package _08_并查集.UnionFind._01_QuickUnion;

/**
 * @ClassName _02_UF_QU_R_PC
 * @Description  Quick Union - 基于rank的优化 - 路径压缩(Path Compression)
 * @Author StarLee
 * @Date 2021/12/18
 */

public class _02_UF_QU_R_PC extends _01_UF_QU_R {
    public _02_UF_QU_R_PC(int capacity) {
        super(capacity);
    }
    /**
     * 在find时使路径上的所有节点都指向根节点，从而降低树的高度
     */
    public int find(int v){
        rangeCheck(v);
        if(parents[v] != v){
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
