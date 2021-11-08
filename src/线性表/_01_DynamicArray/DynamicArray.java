package 线性表._01_DynamicArray;

public interface DynamicArray<E> {
    int size();  //元素数量
    boolean isEmpty();   //是否为空
    boolean contains(E element);  //是否包含某个元素
    void add(E element);  //添加元素到尾部
    E get(int index);   //获取指定位置元素
    E set(int index,E element);   //修改指定位置的元素
    void add (int index,E element);   //往指定位置添加元素
    E remove(int index);     //输出指定位置的元素
    int indexOf(E element);   //获取指定元素的下标
    void clear();   //清空动态数组
}
