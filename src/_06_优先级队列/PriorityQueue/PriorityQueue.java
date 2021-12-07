package _06_优先级队列.PriorityQueue;

import _05_堆._01_BinaryHeap.BinaryHeap;

import java.util.Comparator;

/**
 * @ClassName PriorityQueue
 * @Description  优先级队列的实现
 * @Author StarLee
 * @Date 2021/12/6
 */
@SuppressWarnings("unused")
public class PriorityQueue<E> {
    // 利用二叉堆实现优先级队列
    private final BinaryHeap<E> heap;

    // 通过 comparator 自定义优先级高低
    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    public void enQueue(E element) {
        heap.add(element);
    }

    public E deQueue() {
        return heap.remove();
    }

    public E front() {
        return heap.get();
    }
}
