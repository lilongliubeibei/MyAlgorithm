package class05;


//二叉树遍历  O(N)  O(1)

//1.对于当前节点cur，若cur无左孩子，则cur=cur.right。
//
//		2.如果cur有左孩子，找到cur左子树最右节点，记为mostright。
//
//		　　2.1如果mostright右孩子为空，则mostright.right=cur。cur向左移动。(此时为第一次访问该节点)
//
//		　　2.2如果mostright右孩子为cur，则mostright.right=null。cur向右移动(此时为第二次访问该节点)
public class Code01_MorrisTraversal {
	
	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}


/*	中序遍历的两处打印输出语句，一处是在cur没有左子树的时候，直接打印cur；
	第二处是在cur有左子树，把当前节点的左子树遍历完了，cur向右移动之前，此时是第二次遍历到该节点，打印cur。*/
	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			System.out.print(cur1.value + " ");
			cur1 = cur1.right;
		}
		System.out.println();
	}


//	得到的遍历序列是1、2、4、2、5、1、3、6、3、7，可以发现有左孩子的节点1、2、3遍历了两次
//	（上面分析代码的时候有图示讲解了为什么会遍历两次），对于没有左孩子的节点4、5、6、7遍历了一次，
//	因为这四个节点没有左孩子，也就不会遍历到左子树上去，所以只会遍历一次。
//	这段分析可以解释为什么Morris遍历的时间复杂度是 O ( n ) O(n)O(n) ，因为每个节点最多被遍历两次。


//	第一处是在cur没有左子树的时候，直接打印cur，然后cur向右移动；
//	第二处是在cur有左子树，在cur向左移动之前，此时是第一次遍历当前节点，故打印cur。
	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					System.out.print(cur1.value + " ");
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			} else {
				System.out.print(cur1.value + " ");
			}
			cur1 = cur1.right;
		}
		System.out.println();
	}

	
/*	3）后序遍历：
	Morris中，对一个节点最多只会遍历两次，那么怎么做到后序遍历呢？
	做法是这样的：对于没有左子树的节点，这些节点只会遍历一次，不用关心这些节点。
	对于含有左子树的节点，这些节点会被遍历两次，在第二次遍历的时候逆序打印左子树的右边界。
	最后在函数退出之前再单独逆序打印整棵树的右边界。*/
	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
					printEdge(cur1.left);
				}
			}
			cur1 = cur1.right;
		}
		printEdge(head);
		System.out.println();
	}

	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}

	public static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
		morrisIn(head);
		morrisPre(head);
		morrisPos(head);
		printTree(head);

	}

}
