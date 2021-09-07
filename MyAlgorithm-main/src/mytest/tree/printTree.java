package mytest.tree;


import java.util.*;

public class printTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //先序遍历  根左右
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    //中序遍历  左根右
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    //后序遍历  左右根
    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    //先序遍历  根左右
    public static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.value + " ");
            if (pop.right != null) {
                stack.add(pop.right);
            }
            if (pop.left != null) {
                stack.add(pop.left);
            }
        }
    }

    //左根右
    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        Stack<Node> stack = new Stack<>();
        Node tmp = head;
        while (tmp != null) {
            stack.add(tmp);
            tmp = tmp.left;
        }
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.value + " ");
            Node right = pop.right;
            while (right != null) {
                stack.add(right);
                right = right.left;
            }
        }
    }

    // 左右根   反过来就是根右左，可以先序变化一下再反转
    public static void posOrderUnRecur1(Node head) {
        System.out.print("post-order: ");
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            stack2.add(pop);
            if (pop.left != null) {
                stack.add(pop.left);
            }
            if (pop.right != null) {
                stack.add(pop.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    // 左右根  不用栈的方式
    public static void posOrderUnRecur2(Node head) {
//        System.out.print("post-order: ");
//        Stack<Node> stack = new Stack<>();
//        Stack<Node> stack2 = new Stack<>();
//        stack.add(head);
//        while (!stack.isEmpty()) {
//            Node peek = stack.peek();
//            while(head!=null&&peek!=head.left&&peek!head)
//        }
//        while (!stack2.isEmpty()) {
//            System.out.print(stack2.pop().value+" ");
//        }
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        System.out.println();
        inOrderUnRecur(head);
        System.out.println();
        posOrderUnRecur1(head);
//        posOrderUnRecur2(head);
        System.out.println();
        layerOrder(head);
        System.out.println();
        System.out.println(layerOrderForWidth(head));

    }

    //层序遍历，使用队列
    public static void layerOrder(Node head) {

        System.out.print("layerOrder:");
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.print(poll.value + " ");
            if (poll.left != null) queue.add(poll.left);
            if (poll.right != null) queue.add(poll.right);
        }
    }

    //层序遍历，使用队列,求最大宽度
    public static int layerOrderForWidth(Node head) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node lastNode = head;
        int count = 0;
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            count++;
            if (poll == lastNode) {
                maxWidth = Math.max(maxWidth, count);
                count = 0;
                lastNode = poll.right == null ? (poll.left == null ? null : poll.left) : poll.right;
            }
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        return maxWidth;
    }
}
