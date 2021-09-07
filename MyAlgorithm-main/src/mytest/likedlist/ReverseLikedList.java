package mytest.likedlist;

import java.util.UUID;

public class ReverseLikedList {

    private static class Node {
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode {
        public DoubleNode next;
        public DoubleNode last;
        public int value;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static Node reverseNode(Node head) {
        if (head == null) return null;

        Node pre = null;
        Node next = head.next;
        while (head.next != null) {
            head.next = pre;
            pre = head;
            head = next;
            next = next.next;
        }
        head.next = pre;
        return head;
    }


    public static DoubleNode reverseDoubleNode(DoubleNode head) {
        if (head == null) return null;
        DoubleNode pre = null;
        DoubleNode next = head.next;
        while (head.next != null) {
            head.next = pre;
            pre = head.next;
            head = next;
            head.last = next;
            next = next.next;
        }
        head.next = pre;
        head.last = null;
        return head;
    }
}
