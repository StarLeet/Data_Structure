package _01_线性表._01_DynamicArray;

@SuppressWarnings("ALL")
public class Cust_ArrayList<E> implements DynamicArray<E>{
    private int size;      //元素的个数,用户操作的依据
    private E[] elements;  //动态数组的引用
    private static final int DEFAULT_CAPACITY = 10; //动态数组默认容量
    private static final int ELEMENT_NOT_FOUND = -1; //元素找不到时返回的值
    public Cust_ArrayList(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        //为方便起见,将小于默认容量的请求都设置为默认容量
        elements =(E[]) new Object[capacity];  /* 重要  */
        //因为不确定泛型的具体类型,用Object来接受所有的引用类型
    }
    public Cust_ArrayList(){
        this(DEFAULT_CAPACITY); //无参构造调用有参构造
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        this.size = 0;
        //清空数组完全不需要将元素清除,只需将size设为0即可,后续的方法可根据size限制用户的行为
        //此情况为数组多次重复利用,所以无需考虑释放空间。因为空间的开辟与销毁都会消耗大量资源
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return size==0;  //巧用~
    }

    public E get(int index){
        checkOutOfBound(index);
        return elements[index];
    }

    public E set(int index,E element){
        checkOutOfBound(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    public int indexOf(E element){
        for (int i = 0; i < this.size; i++) {
            if(elements[i] == element) return i;
        }
        return ELEMENT_NOT_FOUND;
    }

    public boolean contains(E element){  //元素是否存在
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(E element){
        add(size,element);
    }

    public void add(int index,E element){
        checkOutofBoundForAdd(index);

        ensureCapacity(size + 1);

        for (int i = index; i < size; i++) {
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    public E remove(int index){
        checkOutOfBound(index);
        E rmElement= elements[index];
        for (int i = index; i < size-1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return rmElement;
    }

    private void checkOutOfBound(int index){
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("index is error! The max size is " + this.size);
        }
    }

    private void checkOutofBoundForAdd(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index is error! The max size is " + this.size);
        }
    }

    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);  //妙用,浮点数计算不如位运算快
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("扩容了~新容量为" + newCapacity);
    }

    public void printList(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("size=").append(this.size).append(",[");
        for (int i = 0; i < size; i++) {
            if (i==0) {
                sb.append(elements[i]);
            }else {
                sb.append(",").append(elements[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
