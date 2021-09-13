package class02;

/**
 * Not implemented by zuochengyun
 * 
 * Abstract class for self balancing binary search class02. Contains some methods
 * that is used for self balancing class02.
 * 
 * @author Ignas Lelys
 * @created Jul 24, 2011
 * 
 */
public abstract class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree {

    /**
     * Rotate to the left.
     * 
     * @param node Node on which to rotate.
     * @return Node that is in place of provided node after rotation.
     */
    protected Node rotateLeft(Node node) {
        //右子树的父指针指向当前节点的父节点
        Node temp = node.right;
        temp.parent = node.parent;

        //当前节点的右指针指向右子树的左节点
        node.right = temp.left;
        //如果右子树的左节点不为null,将其父节点指向node
        if (node.right != null) {
            node.right.parent = node;
        }
        temp.left = node;
        node.parent = temp;

        // temp took over node's place so now its parent should point to temp
        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        
        return temp;
    }

    /**
     * Rotate to the right.
     * 
     * @param node Node on which to rotate.
     * @return Node that is in place of provided node after rotation.
     */
    protected Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        // temp took over node's place so now its parent should point to temp
        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        
        return temp;
    }

}
