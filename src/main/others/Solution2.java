package others;

// https://leetcode-cn.com/problems/remove-linked-list-elements/
public class Solution2 {
    public ListNode removeElements1(ListNode head, int val) {
        //先删除头部与需要删除内容一致的元素
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }
        //如果删完了，返回空
        if (head == null) {
            return null;
        }
        //确定链表头不是需要被删的内容后，向下循环删除需要删元素
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    //虚拟头节点
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    //递归
    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode res = removeElements3(head.next, val);
        if (head.val == val) {
            head.next = null;
            return res;
        } else {
            head.next = res;
            return head;
        }
    }

}
