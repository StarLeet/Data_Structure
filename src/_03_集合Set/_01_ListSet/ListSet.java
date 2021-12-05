package _03_集合Set._01_ListSet;

import _01_线性表.List;
import _01_线性表._02_LinkedList.LinkedList.LinkedList;
import _03_集合Set.Set;

/**
 * @ClassName ListSet
 * @Description  LinkedList实现Set
 * @Author StarLee
 * @Date 2021/12/2
 */

public class ListSet<E> implements Set<E> {
    private final LinkedList<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int index = list.indexOf(element);
        if(index == List.ELEMENT_NOT_FOUND){ // 没有该元素
            list.add(element); // 没有就添加
        }else{
            list.set(index, element); // 已经有就替换
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if(index != List.ELEMENT_NOT_FOUND){
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        int size = list.size();
        for(int i = 0; i < size; i++){
            visitor.visit(list.get(i));
        }
    }
}
