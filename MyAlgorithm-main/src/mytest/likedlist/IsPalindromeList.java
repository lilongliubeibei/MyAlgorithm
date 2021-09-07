//package mytest.likedlist;
//
//import java.util.Stack;
//
//public class IsPalindromeList {
//
//    private static class Node {
//        public Node next;
//        public int value;
//
//        public Node(int value) {
//            this.value = value;
//        }
//    }
//
//    //数组方式
//    static boolean isPalinByArray(Node head) {
//        if (head == null) {
//            return false;
//        }
//        if (head.next == null) {
//            return true;
//        }
//        if (head.next.next == null) {
//            return head.next.value == head.next.next.value;
//        }
//        int length = 0;
//        Node tmp = head;
//        while (tmp != null) {
//            length++;
//            tmp = tmp.next;
//        }
//        Node[] arr = new Node[length];
//        Node tmp2 = head;
//        int i = 0;
//        while (tmp2 != null) {
//            arr[i++] = tmp2;
//            tmp2 = tmp2.next;
//        }
//        Node tmp3 = head;
//        int j = arr.length - 1;
//        while (tmp3 != null) {
//            if (arr[j--].value != tmp3.value) {
//                return false;
//            }
//            tmp3 = tmp3.next;
//        }
//        return true;
//    }
//
//    //栈方式
//    static boolean isPalinByStack(Node head) {
//        if (head == null) {
//            return false;
//        }
//        if (head.next == null) {
//            return true;
//        }
//        if (head.next.next == null) {
//            return head.next.value == head.next.next.value;
//        }
//        Stack<Node> stack = new Stack<>();
//        Node tmp = head;
//        while (tmp != null) {
//            stack.push(tmp);
//            tmp = tmp.next;
//        }
//        Node tmp3 = head;
//        while (tmp3 != null) {
//            if (stack.pop().value != tmp3.value) {
//                return false;
//            }
//            tmp3 = tmp3.next;
//        }
//        return true;
//    }
//
//    //对折方式
//    static boolean isPalinByStackMid(Node head) {
//        if (head == null) {
//            return false;
//        }
//        if (head.next == null) {
//            return true;
//        }
//        if (head.next.next == null) {
//            return head.next.value == head.next.next.value;
//        }
//        Node fast = null;
//        Node slow = null;
//        //求中间节点
//        while (head.next.next != null && head.next != null) {
//            fast = head.next.next;
//            slow = head.next;
//        }
//        //中间节点之后逆序
//        Node temp = slow;
//        Node pre = slow;
//        Node next = temp.next;
//        while (temp.next != null) {
//            temp.next = pre;
//            pre = temp;
//            temp = next;
//            next = next.next;
//        }
//        temp.next = pre;
//
//        Node temp2=head;
//        Node temp3=temp;
//        //分别从头节点和尾节点遍历
//        while (temp2!=null&&temp3!=null) {
//               if(temp2.value!=temp3.value){
//                   return false;
//               }
//               temp3=temp3.next;
//               temp2=temp2.next;
//        }
//
//        //复原中间节点以后逆序
//        pre=null;
//        next=temp.next;
//        while(){
//
//        }
//
//        Stack<Node> stack = new Stack<>();
//        Node tmp = head;
//        while (tmp != null) {
//            stack.push(tmp);
//            tmp = tmp.next;
//        }
//        Node tmp3 = head;
//        while (tmp3 != null) {
//            if (stack.pop() != tmp3) {
//                return false;
//            }
//            tmp3 = tmp3.next;
//        }
//        return true;
//    }
//}
