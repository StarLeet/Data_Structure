package _04_映射Map._02_HashMap;

import _04_映射Map.Map;
import _0_Tools.TreePrinter.BinaryTreeInfo;
import _0_Tools.TreePrinter.BinaryTrees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @ClassName HashMap
 * @Description  哈希表、红黑树实现Map(不要求具备可比较性,所以通过equals来决定是否覆盖)
 * @Author StarLee
 * @Date 2021/12/3
 */
@SuppressWarnings("all")
public class HashMap<K,V> implements Map<K,V> {
    protected static final boolean RED = false;
    protected static final boolean BLACK = true;
    protected int size;
    protected Node<K, V>[] table;  //哈希表
    protected static final int DEFAULT_CAPACITY = 1 << 4;   //默认容量
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;   //装填因子(扩容相关)

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    protected static class Node<K, V> {
        protected int hash;   //存放节点哈希值
        protected K key;
        protected V value;
        protected boolean color = RED;
        protected Node<K, V> left;
        protected Node<K, V> right;
        protected Node<K, V> parent;
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();  //假定null的哈希值为0
            /*   扰动计算   */
            this.hash = hash ^ (hash >>> 16);  //将哈希值高16位与低16位再进行一次异或运算，使其分布更均匀
            this.value = value;
            this.parent = parent;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {  //得到兄弟节点
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        @Override
        public String toString() {
            return "Node_" + key + "_" + value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) return;
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    /**
     *  @MethodName put
     *  @Description  重中之重！！ 考虑哈希碰撞的时候非常复杂
     *  @Param [key, value]
     *  @return V
     */
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
            if (h1 > h2) {      // 1.比较哈希值
                cmp = 1;  // 往右走,设置cmp交给后面统一处理
            } else if (h1 < h2) {
                cmp = -1;  // 往左走,设置cmp交给后面统一处理
            } else if (Objects.equals(k1, k2)) {   // 2.哈希值相同,比较equals
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {   // 3.地址不同,判断是否可比
                // 这个if体为空,并排除了compareTo为0的情况,因为我们不认为compareTo为0的结果
                // 能判断二者为一个对象，所以直接往后扫描,将覆盖操作交给equals
            } else if (searched) { // 5.如果已经扫描过,确认没有该Key,就采用内存地址比较,决定添加位置
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else { // searched == false; 4.还没有扫描,先扫描看一下有无该Key,扫描失败才决定比较内存地址
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    // 已经存在这个key
                    node = result;
                    cmp = 0;
                } else { // 不存在这个key,不需要再次扫描,直接通过比较内存地址来分配位置
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
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
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    /**
     *  @MethodName containsValue
     *  @Description  因为哈希值是根据key计算的,所以想找出Value只能遍历哈希表上的所有红黑树
     *  @Param [value]
     *  @return boolean
     */
    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(value, node.value)) return true;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) return;

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) return;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    public void print() {
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            final Node<K, V> root = table[i];
            System.out.println("【index = " + i + "】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object string(Object node) {
                    return node;
                }

                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>)node).right;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>)node).left;
                }
            });
            System.out.println("---------------------------------------------------");
        }
    }

    protected void resize() {
        // 装填因子 <= 0.75
        if (size / table.length <= DEFAULT_LOAD_FACTOR) return;

        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];

        Queue<Node<K, V>> queue = new LinkedList<>();
        /*      遍历哈希表,取出各个桶里的树根     */
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) continue;

            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                /*   层序遍历,一个个挪动节点   */
                // 挪动代码得放到最后面,因为节点信息得重置
                moveNode(node);
            }
        }
    }

    /**
     *  @MethodName moveNode
     *  @Description  扩容之后,用于移动旧表红黑树节点
     *  @Param [newNode]
     *  @return void
     */
    protected void moveNode(Node<K, V> newNode) {
        // 重置
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;  //红黑树新添加节点默认为RED

        int index = index(newNode);
        // 取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            afterPut(root);
            return;
        }

        // 添加新的节点到红黑树上面
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = newNode.key;
        int h1 = newNode.hash;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            /*      将equals与搜索算法都移除,提高效率
             *      因为我们只是挪动树上的节点,这些节点的Key一定是唯一的
             *      这个在我们添加的时候就已经确定了,我们引入搜索算法也是为了确定Key唯一
             *      所以在已经保证Key唯一的情况下,我们可以移除equals跟搜索算法,因为他们已经不起作用了
             * */
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
            } else {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                //identityHashCode()由内存地址算出的唯一哈希值
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }
            /*      cmp为0的情况也可以移除,因为所有的Key都不相等,cmp完全不可能为0   */
        } while (node != null);

        // 看看插入到父节点的哪个位置
        newNode.parent = parent;
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        // 新添加节点之后的处理
        afterPut(newNode);
    }

    /**
     *  @MethodName node
     *  @Description  查找指定key,并将其所在的节点返回
     *  @Param [key]
     *  @return _04_映射Map._02_HashMap.HashMap.Node<K,V>
     */
    protected Node<K, V> node(K key) {
        Node<K, V> root = table[index(key)];  // 通过hash()计算出指定桶,桶中存储红黑树的根
        return root == null ? null : node(root, key);
    }

    /**
     *  @MethodName node
     *  @Description  寻找哈希表中某棵红黑树上的key
     *  @Param [node, k1]  node为根  k1为需要寻找的key
     *  @return _04_映射Map._02_HashMap.HashMap.Node<K,V>
     */
    protected Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        // 用于存储查找结果
        Node<K, V> result = null;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;
            // 1.先比较哈希值
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) { // 2.哈希值相等,用Objects.equals比较内存地址是否相同
                return node;
            } else if (k1 != null && k2 != null  // 3.哈希值相等,内存不等,判断是否有可比较性
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) { // 排除compareTo为0的情况
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) {
                // 4.哈希值相等,内存不等,没有可比较性，就只能进行扫描了，因为比较内存地址大小具有随机性
                return result;
            } else { // 只能往左边找
                node = node.left;
            }
        }
        return null;
    }

    /**
     *  @MethodName index
     *  @Description  根据key生成对应的索引（在桶数组中的位置）等价于hash()
     *  @Param [key]
     *  @return 哈希表中的索引
     */
    protected int index(K key) {
        return hash(key) & (table.length - 1);
    }
    /**
     *  @MethodName hash
     *  @Description  将外部传入的Key进行扰动计算,跟内部Key的hash同步操作
     *  @Param [key]
     *  @return int
     */
    protected int hash(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    protected int index(Node<K, V> node) {
        return node.hash & (table.length - 1);
    }

    /***************************************************************************
     *  由此以下,为引入LinkedHashMap而添加的方法(染色、旋转、找前驱、删除后平衡处理等)  *
     ***************************************************************************/

    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    /**
     *  @MethodName LinkedRemove
     *  @Description  为引入LinkedHashMap的链表删除而定义的空方法,具体由LinkedHashMap实现
     *  @Param [targetNode, removeNode]
     *  @return void
     */
    protected void LinkedRemove(Node<K,V> targetNode, Node<K,V> removeNode){}

    /*************************************************************************
     *         由此以下,为红黑树特有的方法(染色、旋转、找前驱、删除后平衡处理等)      *
     *************************************************************************/

    /**
     *  @MethodName successor
     *  @Description  从BST中移植而来
     *  @Param [node]
     *  @return _04_映射Map._02_HashMap.HashMap.Node<K,V>
     */
    protected Node<K, V> successor(Node<K, V> node) {
        if (node == null) return null;
        // 前驱节点在左子树当中（right.left.left.left....）
        Node<K, V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     *  @MethodName remove
     *  @Description  从BST移植改造而来,引入index的计算,用来确定具体操作的那棵树
     *  @Param [node]
     *  @return V
     */
    protected V remove(Node<K, V> node) {
        if (node == null) return null;

        Node<K,V> targetNode = node;  //LinkedHashMap中链表中想要删除的节点

        size--;

        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;

        // 获取红黑树对应的索引;同一棵树上的任一节点得到的索引是一样的
        int index = index(node);

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        }
        // LinkedHashMap的链表删除操作,特殊处理度为1、2的节点
        LinkedRemove(targetNode,node);
        return oldValue;
    }

    protected void afterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    protected void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterPut(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    protected void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            table[index(grand)] = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    protected Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    protected Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    protected Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    protected boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    protected boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    protected boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

}
