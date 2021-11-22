package 算法面试._01_链表相关;

//   https://leetcode-cn.com/problems/linked-list-cycle/
@SuppressWarnings("all")
public class _141_环形链表 {
    /***
     *    核心思想:快慢指针
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next; // 快指针每次都比慢指针快一步(包括开始)
        while (fast != null && fast.next != null) {   //说明fast指针到尾了
            slow = slow.next;    //慢指针走一步
            fast = fast.next.next;  //快指针走两步
            if(slow == fast) return true;  //快慢指针相遇,说明有环

            /**
             *    为什么快指针一定能追上慢指针呢?(如果有环)
             *    首先快指针走2格,慢指针走1格,不论二者相距多少个结点
             *    从相对速度而言,快指针总是比慢指针快1格
             *    所以快指针是一格一格追上慢指针的,二者并不存在永远错过的情况
             *
             *    如果某一次临近 会发生:
             *    1、当快指针就在慢指针后面，那么下一次慢指针移动一位，快指针移动两位，相遇
             *    2、当快指针和慢指针差一个位置，那么下一次慢指针移动一位，快指针移动两位，他们会变成第一种情况
             *    3、当快指针和慢指针差两个位置，那么下一次慢指针移动一位，快指针移动两位，他们会变成第二种情况
             */
        }
        return false;
    }
}
