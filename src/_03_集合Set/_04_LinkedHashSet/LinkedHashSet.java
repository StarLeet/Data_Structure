package _03_集合Set._04_LinkedHashSet;

import _03_集合Set.Set;
import _04_映射Map.Map;
import _04_映射Map._03_LinkedHashMap.LinkedHashMap;

/**
 * @ClassName LinkedHashSet
 * @Description  LinkedHashSet底层维护的是LinkedHashMap
 * @Author StarLee
 * @Date 2021/12/5
 */

public class LinkedHashSet<E> implements Set<E> {
    private final LinkedHashMap<E, Object> map = new LinkedHashMap<>();

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
