package _08_并查集.UnionFind._01_QuickUnion;

/**
 * @ClassName UF_QU_Rank
 * @Description  Quick Union - 基于rank的优化
 * @Author StarLee
 * @Date 2021/12/18
 */

public class _01_UF_QU_R extends _00_UF_QuickUnion{
    private int[] ranks;

    public _01_UF_QU_R(int capacity) {
        super(capacity);

        ranks = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            ranks[i] = i;
        }
    }

    public void union(int v1, int v2){
        int p1 = find(v1);
        int p2 = find(v2);

        if(ranks[p1] < ranks[p2]){
            parents[p1] = p2;
        }else if(ranks[p1] > ranks[p2]){
            parents[p2] = p1;
        }else{ // ranks[p1] == ranks[p2]
            parents[p1] = p2;
            ranks[p2] += 1;
        }
    }
}
