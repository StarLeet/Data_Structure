package _01_线性表;

public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;

    /**
     *  @MethodName clear
     *  @Description 清空线性表
     *  @Param []
     *  @return void
     */
    void clear();

    /**
     *  @MethodName size
     *  @Description  返回表中元素个数
     *  @Param []
     *  @return int
     */
    int size();

    /**
     *  @MethodName isEmpty
     *  @Description  判断线性表是否为空
     *  @Param []
     *  @return boolean
     */
    boolean isEmpty();

    /**
     *  @MethodName contains
     *  @Description  元素是否存在
     *  @Param [element]
     *  @return boolean
     */
    boolean contains(E element);

    /**
     *  @MethodName add
     *  @Description  表中添加元素
     *  @Param [element]
     *  @return void
     */
    void add(E element);

    /**
     *  @MethodName get
     *  @Description  根据索引获取元素
     *  @Param [index]
     *  @return E
     */
    E get(int index);

    /**
     *  @MethodName set
     *  @Description  修改指定索引的元素
     *  @Param [index, element]
     *  @return 原来的元素
     */
    E set(int index, E element);

    /**
     *  @MethodName add
     *  @Description  指定索引添加元素
     *  @Param [index, element]
     *  @return void
     */
    void add(int index, E element);


    /**
     *  @MethodName remove
     *  @Description  删除指定索引元素
     *  @Param [index]
     *  @return 被删除的元素
     */
    E remove(int index);

    /**
     *  @MethodName indexOf
     *  @Description  找出元素的索引
     *  @Param [element]
     *  @return int
     */
    int indexOf(E element);
}

