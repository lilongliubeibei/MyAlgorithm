package mytest.likedlist;

import linked_list.Code07_FindFirstIntersectNode;

import java.util.HashSet;

public class FirstInsectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    //获取一个链表的入环节点
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
//        //hashset方法
//        HashSet hashSet = new HashSet();
//        Node cur = head;
//        while (cur != null) {
//            if (hashSet.contains(cur)) {
//                return cur;
//            } else {
//                hashSet.add(cur);
//                cur = cur.next;
//            }
//        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow && fast != null) {
            if (fast.next == null||fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int length = 0;
        Node cur = head1;
        while (cur != null) {
            cur=cur.next;
            length++;
        }
        cur = head2;
        while (cur != null) {
            cur=cur.next;
            length--;
        }
        Node first = length > 0 ? head1 : head2;
        Node second = length > 0 ? head2 : head1;
        length = Math.abs(length);
        while (length > 0) {
            first = first.next;
            length--;
        }
        while (first != second) {
            if (first == null || second == null) {
                return null;
            }
            first = first.next;
            second = second.next;
        }
        return first;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {

        //如果两个链表入环节点相同
        if (loop1 == loop2) {
            //遍历之前节点，找到最先相交的点
            int length = 0;
            Node cur = head1;
            while (cur != loop1) {
                cur=cur.next;
                length++;
            }
            cur = head2;
            while (cur != loop2) {
                cur=cur.next;
                length--;
            }
            Node first = length > 0 ? head1 : head2;
            Node second = length > 0 ? head2 : head1;
            length = Math.abs(length);
            while (length > 0) {
                first = first.next;
                length--;
            }
            while (first != second) {
                if (first == loop1 || second == loop2) {
                    return loop1;
                }
                first = first.next;
                second = second.next;
            }
            return first;
        }
        //入环节点不同，快慢指针，如果慢指针转1圈内，快慢指针重合则相交 loop1和loop2都符合条件
        Node cur1 = loop1.next;
        Node cur2 = loop2.next.next;
        while (cur1 != loop1) {
            if (cur1 == cur2) {
                return loop1;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        //->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }
}