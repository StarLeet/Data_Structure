package _03_集合Set;

/**
 * @ClassName Set
 * @Description  集合接口
 * @Author StarLee
 * @Date 2021/12/2
 */

public interface Set<E> {
    int size();  // 集合大小
    boolean isEmpty();  // 判断集合是否为空
    void clear();   // 清空集合
    boolean contains(E element);    // 元素是否存在
    void add(E element);    // 集合中添加指定元素
    void remove(E element);     // 移除指定元素
    void traversal(Visitor<E> visitor);     //访问器自定义逻辑,遍历集合

    abstract class Visitor<E> {
        boolean stop;
        public abstract boolean visit(E element);
    }

}
