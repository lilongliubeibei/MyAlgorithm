

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	public HashMap<Integer, com.easy.day7.Node> nodes;
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
