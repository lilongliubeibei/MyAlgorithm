package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_TopologySort {

    // directed graph and no loop
    // public static List<Node> sortedTopology(Graph graph) {
    // HashMap<Node, Integer> inMap = new HashMap<>();
    // Queue<Node> zeroInQueue = new LinkedList<>();
    // for (Node node : graph.nodes.values()) {
    // inMap.put(node, node.in);
    // if (node.in == 0) {
    // zeroInQueue.add(node);
    // }
    // }
    // List<Node> result = new ArrayList<>();
    // while (!zeroInQueue.isEmpty()) {
    // Node cur = zeroInQueue.poll();
    // result.add(cur);
    // for (Node next : cur.nexts) {
    // inMap.put(next, inMap.get(next) - 1);
    // if (inMap.get(next) == 0) {
    // zeroInQueue.add(next);
    // }
    // }
    // }
    // return result;
    // }

    public static List<Node> sortedTopology(Graph graph) { // 拓扑排序
        List<Node> result = new ArrayList<>();
        HashMap<Node, Integer> map = new HashMap();
        Queue<Node> zeroInQueue = new LinkedList<>();


        for (Node node : graph.nodes.values()) {
            map.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        while (!zeroInQueue.isEmpty()) {
            Node poll = zeroInQueue.poll();
            result.add(poll);
            for (Node node : poll.nexts) {
                map.put(node, map.get(node) - 1);
                if (map.get(node) == 0) {
                    zeroInQueue.add(node);
                }
            }
        }
        return result;
    }
}
