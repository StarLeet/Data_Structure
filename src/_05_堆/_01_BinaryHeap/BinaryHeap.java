package _05_堆._01_BinaryHeap;

import _05_堆.AbstractHeap;
import _0_Tools.TreePrinter.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @ClassName BinaryHeap
 * @Description  最大堆实现(改变compare的比较策略就变成最小堆)
 * @Author StarLee
 * @Date 2021/12/5
 */
@SuppressWarnings("all")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(E[] elements, Comparator<E> comparator)  {
        super(comparator);

        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {   // 为了维持和保护堆数据,采用深拷贝
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    public BinaryHeap(E[] elements)  {
        this(elements, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap() {
        this(null, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;  // 1.先加到数组末尾
        siftUp(size - 1);   // 2.然后进行上滤
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    /**
     * 删除堆顶元素
     */
    public E remove() {
        emptyCheck();

        int lastIndex = --size;
        /*  1. 用最后一个元素覆盖根元素
        *   2. 删除最后一个元素
        *   3. 进行下滤操作
        * */
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;

        siftDown(0);
        return root;
    }

    /**
     *  @MethodName replace
     *  @Description  删除堆顶元素,并替换上新元素
     *  @Param [element]
     *  @return E
     */
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    /**
     * 批量建堆,将一组数据排列成堆
     * 思想: 先局部成堆,然后整体成堆
     */
    private void heapify() {
        // 自上而下的上滤   本质是添加
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}

        // 自下而上的下滤(效率更高)  本质是删除
        // (size >> 1) - 1 最后一个非叶子节点的索引
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 让index位置的元素下滤
     * 本质是：同大于自己的子节点交换位置
     * @param index
     */
    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1; // 非叶子节点的数量
        // 第一个叶子节点的索引 == 非叶子节点的数量
        // index < 第一个叶子节点的索引
        // 必须保证index位置是非叶子节点
        while (index < half) {
            // index的节点有2种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;

            // 选出左右子节点最大的那个
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) break;

            // 将子节点存放到index位置
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }
        elements[index] = element;
    }

    /**
     * 让index位置的元素上滤
     * 本质是：同小于自己的父节点交换位置,一般发生在添加元素中
     */
    private void siftUp(int index) {
        E element = elements[index];
        /*    优化后,不需要每次上滤都覆盖父节点,而是等到最后才覆盖
        *     同时上滤成功一次,父节点向下覆盖一次
        * */
        while (index > 0) {
            // 父节点索引 = (子节点索引-1) / 2
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break;

            // 将父元素存储在index位置,    父节点向下覆盖
            elements[index] = parent;

            // 重新赋值index
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     *  用二叉树的打印方式来打印堆,因为二者逻辑等价
     */
    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int)node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int)node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int)node];
    }
}
