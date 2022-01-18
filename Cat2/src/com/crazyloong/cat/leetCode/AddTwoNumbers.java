package com.crazyloong.cat.leetCode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {
    int x = 0;

    /**
     *
     * 自己的答案
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        addTwoNumbers111(l1,l2,result);
        return result;
    }

    public void addTwoNumbers111(ListNode l1, ListNode l2,ListNode l3) {
        ListNode flag = new ListNode(0);
        int c = l1.val+l2.val+x;
        int d = c%10;
        x = (c - d)/10;
        l3.val = d;
        l3.next = flag;
        if (l1.next == null && l2.next == null && x ==0){
            l3.next = null;
            return ;
        }
        if (l1.next == null){
            l1.next = new ListNode(0);
        }
        if (l2.next == null){
            l2.next = new ListNode(0);
        }
        addTwoNumbers111(l1.next,l2.next,flag);
    }

    public static void main(String[] args) {
        AddTwoNumbers a = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(3);
        ListNode l11 = new ListNode(5);
        ListNode l22 = new ListNode(7);
        ListNode l111 = new ListNode(4);
        ListNode l222 = new ListNode(5);
        l1.next = l11;
        l2.next = l22;
        l11.next = l111;
        l22.next = l222;
        ListNode aaa =a.addTwoNumbers(l1,l2);
        System.out.println(aaa.val);
        System.out.println(aaa.next.val);
        System.out.println(aaa.next.next.val);
    }

}
