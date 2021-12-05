package _04_映射Map;

/**
 * @ClassName Map
 * @Description  映射/字典接口
 * @Author StarLee
 * @Date 2021/12/2
 */

public interface Map<K,V> {
    int size();
    boolean isEmpty();
    void clear();
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {
        public boolean stop;
        public abstract boolean visit(K key, V value);
    }
}
