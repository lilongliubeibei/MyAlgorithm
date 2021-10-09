package class02;

/**
 * Not implemented by zuochengyun
 * <p>
 * Abstract binary search tree implementation. Its basically fully implemented
 * binary search tree, just template method is provided for creating Node (other
 * class02 can have slightly different nodes with more info). This way some code
 * from standart binary search tree can be reused for other kinds of binary
 * class02.
 *
 * @author Ignas Lelys
 * @created Jun 29, 2011
 */
public class AbstractBinarySearchTree {

    /**
     * Root node where whole tree starts.
     */
    public Node root;

    /**
     * Tree size.
     */
    protected int size;

    /**
     * Because this is abstract class and various class02 have different
     * additional information on different nodes subclasses uses this abstract
     * method to create nodes (maybe of class {@link Node} or maybe some
     * different node sub class).
     *
     * @param value  Value that node will have.
     * @param parent Node's parent.
     * @param left   Node's left child.
     * @param right  Node's right child.
     * @return Created node instance.
     */
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    /**
     * Finds a node with concrete value. If it is not found then null is
     * returned.
     *
     * @param element Element value.
     * @return Node with value provided, or null if not found.
     */
    public Node search(int element) {
        Node node = root;
        while (node != null && node.value != null) {
            if (node.value == element) {
                return node;
            }
            if (element < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    /**
     * Insert new element to tree.
     *
     * @param element Element to insert.
     */
    public Node insert(int element) {
        if (root == null) {
            root = createNode(element, null, null, null);
            size++;
            return root;
        }

        //插入节点的父节点
        Node insertParentNode = null;
        //从根节点开始查询
        Node searchTempNode = root;
        //二分法查找插入节点的父节点
        while (searchTempNode != null && searchTempNode.value != null) {
            if (element < searchTempNode.value) {
                insertParentNode = searchTempNode;
                searchTempNode = searchTempNode.left;
            } else {
                insertParentNode = searchTempNode;
                searchTempNode = searchTempNode.right;
            }
        }

        Node newNode = createNode(element, insertParentNode, null, null);

        if (insertParentNode.value > newNode.value) {
            insertParentNode.left = newNode;
        } else {
            insertParentNode.right = newNode;
        }

        size++;
        return newNode;
    }

    /**
     * Removes element if node with such value exists.
     *
     * @param element Element value to remove.
     * @return New node that is in place of deleted node. Or null if element for
     * delete was not found.
     */
    public Node delete(int element) {
        Node deleteNode = search(element);
        if (deleteNode != null) {
            return delete(deleteNode);
        } else {
            return null;
        }
    }

    /**
     * Delete logic when node is already found.
     *
     * @param deleteNode Node that needs to be deleted.
     * @return New node that is in place of deleted node. Or null if element for
     * delete was not found.
     */
    protected Node delete(Node deleteNode) {

//			Node nodeToReturn = null;
//////			if (deleteNode != null) {
//////				if (deleteNode.left == null) {
//////					nodeToReturn = transplant(deleteNode, deleteNode.right);
//////				} else if (deleteNode.right == null) {
//////					nodeToReturn = transplant(deleteNode, deleteNode.left);
//////				} else {
//////					//左右节点都不为null，那么取左子树最大或右子树最小节点
//////					Node successorNode = getMinimum(deleteNode.right);
//////					if (successorNode.parent != deleteNode) {
//////						//如果不是右节点
//////						//把successorNode.right移动到把successorNode
//////						transplant(successorNode, successorNode.right);
//////						//successorNode移动到deleteNode
//////						successorNode.right = deleteNode.right;
//////						successorNode.right.parent = successorNode;
//////					}
//////					transplant(deleteNode, successorNode);
//////					successorNode.left = deleteNode.left;
//////					successorNode.left.parent = successorNode;
//////					nodeToReturn = successorNode;
//////				}
//////				size--;
//////			}
//////			return nodeToReturn;
        

        //总共有四种情况：1.两边都没有左右孩子 2.只有一边有孩子  3.两边都有子孩子
        //node为要删除的结点
		//nodeToReturn: New node that is in place of deleted node. Or null if element for
		//	  delete was not found.
        Node nodeToReturn = null;
        if (deleteNode != null) {
            if (deleteNode.left == null && deleteNode.left == null) {
                if (deleteNode == root) {
                    root = null;
                } else if (deleteNode == deleteNode.parent.left) {
                    deleteNode.parent.left = null;
                } else {
                    deleteNode.parent.right = null;
                }
            } else if (deleteNode.left != null && deleteNode.right == null) {
                //有左节点，没有右节点
				nodeToReturn=deleteNode.left;
                if (deleteNode == root) {
                    root = deleteNode.left;
                } else if (deleteNode == deleteNode.parent.left) {
                    deleteNode.parent.left = deleteNode.left;
                } else {
                    deleteNode.parent.right = deleteNode.left;
                }
                
            } else if (deleteNode.left == null && deleteNode.right != null) {
				nodeToReturn=deleteNode.right;
                if (deleteNode == root) {
                    root = deleteNode.right;
                } else if (deleteNode == deleteNode.parent.left) {
                    deleteNode.parent.left = deleteNode.right;
                } else {
                    deleteNode.parent.right = deleteNode.right;
                }
            } else {
                //左右孩子都有的时候
                Node ghost = deleteNode.left;
                Node ghostParent = null;
                while (ghost.right != null) {
                    ghostParent = ghost;
                    ghost = ghost.right;
                }
				nodeToReturn=ghost;
                //进行替换
                deleteNode.value = ghost.value;
                //删除ghost结点（其右孩子一定为空）
                if (deleteNode == ghostParent) {
                    ghostParent.left = ghost.left;
                } else {
                    ghostParent.right = ghost.left;
                }
            }
        }
        return nodeToReturn;
    }

    /**
     * Put one node from tree (newNode) to the place of another (nodeToReplace).
     *
     * @param nodeToReplace Node which is replaced by newNode and removed from tree.
     * @param newNode       New node.
     * @return New replaced node.
     */
    private Node transplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == null) {
            this.root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = nodeToReplace.parent;
        }
        return newNode;
    }

    /**
     * @param element
     * @return true if tree contains element.
     */
    public boolean contains(int element) {
        return search(element) != null;
    }

    /**
     * @return Minimum element in tree.
     */
    public int getMinimum() {
        return getMinimum(root).value;
    }

    /**
     * @return Maximum element in tree.
     */
    public int getMaximum() {
        return getMaximum(root).value;
    }

    /**
     * Get next element element who is bigger than provided element.
     *
     * @param element Element for whom descendand element is searched
     * @return Successor value.
     */
    // TODO Predecessor
    public int getSuccessor(int element) {
        return getSuccessor(search(element)).value;
    }

    /**
     * @return Number of elements in the tree.
     */
    public int getSize() {
        return size;
    }

    /**
     * Tree traversal with printing element values. In order method.
     */
    public void printTreeInOrder() {
        printTreeInOrder(root);
    }

    /**
     * Tree traversal with printing element values. Pre order method.
     */
    public void printTreePreOrder() {
        printTreePreOrder(root);
    }

    /**
     * Tree traversal with printing element values. Post order method.
     */
    public void printTreePostOrder() {
        printTreePostOrder(root);
    }

    /*-------------------PRIVATE HELPER METHODS-------------------*/

    private void printTreeInOrder(Node entry) {
        if (entry != null) {
            printTreeInOrder(entry.left);
            if (entry.value != null) {
                System.out.println(entry.value);
            }
            printTreeInOrder(entry.right);
        }
    }

    private void printTreePreOrder(Node entry) {
        if (entry != null) {
            if (entry.value != null) {
                System.out.println(entry.value);
            }
            printTreeInOrder(entry.left);
            printTreeInOrder(entry.right);
        }
    }

    private void printTreePostOrder(Node entry) {
        if (entry != null) {
            printTreeInOrder(entry.left);
            printTreeInOrder(entry.right);
            if (entry.value != null) {
                System.out.println(entry.value);
            }
        }
    }

    protected Node getMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    protected Node getMaximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    protected Node getSuccessor(Node node) {
        // if there is right branch, then successor is leftmost node of that
        // subtree
        if (node.right != null) {
            return getMinimum(node.right);
        } else { // otherwise it is a lowest ancestor whose left child is also
            // ancestor of node
            Node currentNode = node;
            Node parentNode = node.parent;
            while (parentNode != null && currentNode == parentNode.right) {
                // go up until we find parent that currentNode is not in right
                // subtree.
                currentNode = parentNode;
                parentNode = parentNode.parent;
            }
            return parentNode;
        }
    }

    // -------------------------------- TREE PRINTING
    // ------------------------------------

    public void printTree() {
        printSubtree(root);
    }

    public void printSubtree(Node node) {
        if (node.right != null) {
            printTree(node.right, true, "");
        }
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, "");
        }
    }

    private void printNodeValue(Node node) {
        if (node.value == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.value.toString());
        }
        System.out.println();
    }

    private void printTree(Node node, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }

    public static class Node {
        public Node(Integer value, Node parent, Node left, Node right) {
            super();
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

    }

}
