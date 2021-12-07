package _05_堆;

import java.util.Comparator;

/**
 * @ClassName AbstractHeap
 * @Description  堆的抽象类,提供一部分方法实现
 * @Author StarLee
 * @Date 2021/12/5
 */
@SuppressWarnings("all")
public abstract class AbstractHeap<E> implements Heap<E> {
    protected int size;
    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2)
                : ((Comparable<E>)e1).compareTo(e2);
    }
}
