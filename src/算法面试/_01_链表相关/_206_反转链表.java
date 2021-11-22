package 算法面试._01_链表相关;

//   https://leetcode-cn.com/problems/reverse-linked-list/
public class _206_反转链表 {
    /**
     * 分析:      反转前: head——> 4——>3——>2——>1——>null
     *           反转后: head——> 1——>2——>3——>4——>null
     *       1. 假设我们调用的函数是reverseList_1(4),如上图所示
     *       2. 那么我们最先关注的应该是reverseList_1(3)返回的结果
     *          因为我们都知道递归的规律是嵌套调用reverseList_1(n--),
     *          所以直接关联于reverseList_1(4)的应该是reverseList_1(3)
     *          reverseList_1(3)的功能是反转head——>3——>2——>1——>null,
     *          返回的结果应该是newHead——>1——>2——>3——>null
     *       3. 那么reverseList_1(4)要想达成自己的目的,得在reverseList_1(3)的结果上加上4结点且4的next为空
     *          也就是 newHead——> 1——>2——>3——>4——>null
     *       4. 这就构成了递归的一般步骤,那么递归应该在何时停止呢？
     *          第一、当传入的是空链表时,不应该继续递归
     *          第二、当反转递归到最后一个结点时,不应该递归(因为反转后还是自己)
     * */
    public static ListNode reverseList_1(ListNode head) {  //递归解法
        if (head == null || head.next==null) return head;  //参照分析4.
        ListNode newHead = reverseList_1(head.next);  //参照分析2.
        head.next.next = head;//参照分析3.
        head.next = null; //参照分析3.
        return newHead;
    }

    /**
     *   head      1    2   3   4   5
     *
     *   newHead   1
     */
    public static ListNode reverseList_2(ListNode head){   //迭代解法
        ListNode newHead = null;
        ListNode p = null;
        while(head != null){
            p = head.next;
            head.next = newHead;
            newHead = head;
            head = p;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode list = initLinkedList(10);
        list = reverseList_2(list);
        System.out.println(list);

    }

    public static ListNode initLinkedList(int num){
        ListNode head = new ListNode(1);
        ListNode p = head;
        for (int i = 2; i <= num; i++) {
            p.next = new ListNode(i);
            p = p.next;
        }
        return head;
    }

}


