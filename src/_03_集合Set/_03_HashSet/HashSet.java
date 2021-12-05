package _03_集合Set._03_HashSet;

import _03_集合Set.Set;
import _04_映射Map.Map;
import _04_映射Map._02_HashMap.HashMap;

/**
 * @ClassName HashSet
 * @Description  HashSet底层维护的就是HashMap
 * @Author StarLee
 * @Date 2021/12/5
 */

public class HashSet<E> implements Set<E> {
    private final HashMap<E, Object> map = new HashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        map.traversal(new Map.Visitor<E, Object>() {
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
