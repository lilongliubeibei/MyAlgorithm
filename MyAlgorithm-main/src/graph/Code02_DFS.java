package graph;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {

    public static void dfs0(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }

        HashSet<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        set.add(node);
		System.out.println(node.value);
        stack.add(node);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                if (!set.contains(next)) {
                    stack.push(pop);
                    stack.push(next);
					System.out.println(next.value);
                    set.add(next);
                }
            }
        }
    }
}
