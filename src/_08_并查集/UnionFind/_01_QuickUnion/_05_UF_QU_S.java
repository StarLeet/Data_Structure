package _08_并查集.UnionFind._01_QuickUnion;

/**
 * @ClassName _05_UF_QU_S
 * @Description  Quick Union - 基于size的优化
 * @Author StarLee
 * @Date 2021/12/18
 */

public class _05_UF_QU_S extends _00_UF_QuickUnion{
    private int[] sizes;

    public _05_UF_QU_S(int capacity) {
        super(capacity);

        sizes = new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1;
        }
    }
    /**
     * 将v1的根节点嫁接到v2的根节点上
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if(p1 == p2) return;

        if(sizes[p1] < sizes[p2]){
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        }else{
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }
}
