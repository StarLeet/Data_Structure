package _04_映射Map._02_HashMap;

import java.util.Objects;

/**
 * @ClassName Simple_HashMap
 * @Description  最次的哈希Map,效率较差。 用作代码演替
 * @Author StarLee
 * @Date 2021/12/4
 */
@SuppressWarnings("all")
public class Simple_HashMap<K,V> extends HashMap<K,V> {
    @Override
    public V put(K key, V value) {
        resize();

        int index = index(key);
        // 取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if (root == null) {
            root = createNode(key, value, null);
            table[index] = root;
            size++;
            afterPut(root);
            return null;
        }

        // 添加新的节点到红黑树上面
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        Node<K, V> result = null;
        boolean searched = false; // 是否已经搜索过这个key
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            /*   性能比较差,因为通过equals就直接进行全树扫描了
                 如果能通过某种方式,确定该扫描左子树或者右子树,那么效率会大大提升
                 提示:引入哈希值,通过先比较哈希值,来决定放左边还是右边,这样扫描的时候,
                 只需要知道哈希值的比较结果,就能直接去扫描某个子树而不是整棵树(扫描对象缩减一大半)【实现如下】
            */
//            if (h1 > h2){     1. 第一次完善推演,哈希值大放右边,小放左边
//                cmp = 1;
//            }else if (h1 < h2){
//                cmp = -1;
//            }else
            if (Objects.equals(k1, k2)) {   // 2.哈希值相同,比较equals
                cmp = 0;
            } else if (searched) { // 5.如果已经扫描过,确认没有该Key,就采用内存地址比较,决定添加位置
                cmp = 1;
            } else { // searched == false; 4.还没有扫描,先扫描看一下有无该Key,扫描失败才决定比较内存地址
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    // 已经存在这个key
                    node = result;
                    cmp = 0;
                } else { // 不存在这个key,不需要再次扫描,直接分配在右边
                    searched = true;
                    cmp = 1;
                }
            }

            // 统一处理
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                node.hash = h1;
                return oldValue;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<K, V> newNode = createNode(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterPut(newNode);
        return null;
    }

    @Override
    protected Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        // 用于存储查找结果
        Node<K, V> result = null;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;
//            if (h1 > h2){       1. 第一次完善推演,哈希值大往右边找,小往左边
//                node = node.right;
//            }else if (h1 < h2){
//                node = node.left;
//            }
            if (Objects.equals(k1, k2)) { // 2.哈希值相等,用Objects.equals比较内存地址是否相同
                return node;
            } else if (node.right != null && (result = node(node.right, k1)) != null) {
                // 4.哈希值相等,内存不等,没有可比较性，就只能进行扫描了，因为比较内存地址大小具有随机性
                return result;
            } else { // 只能往左边找
                node = node.left;
            }
        }
        return null;
    }
}
