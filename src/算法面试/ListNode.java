package 算法面试;


/**
 *     公共结点类定义
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "Node" + val +
                "——>" + next;
    }
}
