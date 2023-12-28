package structrues.tree.binarySrotedTree;

/**
 * @Description 二叉排序树
 * @Author 王俊然
 * @Date 2023/12/23 10:53
 */
public class BSTTest {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BSTTest bstTest = new BSTTest();
        bstTest.createBTree(arr);

        // bstTest.preSearch(bstTest.root);
        // System.out.println();
        // bstTest.midSearch(bstTest.root);
        // System.out.println();
        // bstTest.postSearch(bstTest.root);

        // bstTest.deleteRightMin(bstTest.root);
        // System.out.println();
        boolean b = bstTest.deleteNode(7);
        System.out.println(b);
    }

    /**
     * 内部类，节点
     */
    private class Node implements Comparable<Node> {
        public Node() {
        }

        public Node(Node left, Node right, Integer value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        Node left;
        Node right;
        Integer value;

        @Override
        public int compareTo(Node o) {
            return 0;
        }
    }

    private Node root = null;

    // 根据数组创建二叉排序树
    public void createBTree(int[] arr) {
        for (int num : arr) {
            Node node = new Node(null, null, num);
            this.addNode(root, node);
        }
    }


    // 构建节点
    public void addNode(Node parent, Node node) {
        if (root == null) {
            root = node;
        } else {
            if (parent.value > node.value) {
                if (parent.left == null) {
                    parent.left = node;
                } else {
                    addNode(parent.left, node);
                }
            } else {
                if (parent.right == null) {
                    parent.right = node;
                } else {
                    addNode(parent.right, node);
                }
            }
        }
    }

    // 前序遍历
    public void preSearch(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");
        preSearch(node.left);
        preSearch(node.right);
    }

    // 中序遍历
    public void midSearch(Node node) {
        if (node == null) {
            return;
        }
        midSearch(node.left);
        System.out.print(node.value + " ");
        midSearch(node.right);
    }

    // 后序遍历
    public void postSearch(Node node) {
        if (node == null) {
            return;
        }
        postSearch(node.left);
        postSearch(node.right);
        System.out.print(node.value + " ");
    }

    public boolean deleteNode(int value) {
        // 没有节点
        if (root == null) {
            return false;
        }

        Node result = searchNode(root, value);
        // 没找到
        if (result == null) {
            return false;
        }

        Node parent = searchParentNode(root, value);
        if (parent == null) {
            parent = root;
        }

        boolean leftOrRight = false;
        if (parent.left == result) {
            leftOrRight = true;
        }
        // 根据父节点删除
        // 如果result是叶子节点，直接删除就可以了
        if (result.left == null && result.right == null) {
            if (leftOrRight) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return true;
        } else if (result.left != null && result.right != null) {    // result节点的左右子树都存在，将其右子树的最小节点的值付给result，然后删除最小节点
            Node rightMinNode = findRightMinNode(result.right);
            deleteNode(rightMinNode.value);
            result.value = rightMinNode.value;
            return true;
        } else {    // 如果result只有一个节点
            if (result.left != null) {  // 只有左子节点，用左子结点代替他
                if (leftOrRight) {
                    parent.left = result.left;
                } else {
                    parent.right = result.left;
                }
                return true;

            } else if (result.right != null) {// 只有右子节点，用左子结点代替他
                if (leftOrRight) {
                    parent.left = result.right;
                } else {
                    parent.right = result.right;
                }
                return true;
            }
        }
        return false;
    }

    private Node findRightMinNode(Node node) {
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    private Node searchNode(Node node, int value) {
        Node temp = node;
        if (node != null && node.value == value) {
            return temp;
        } else {
            if (temp.right != null && value > temp.value) {
                return searchNode(temp.right, value);
            } else if (temp.left != null && value < temp.value) {
                return searchNode(temp.left, value);
            } else {
                return null;
            }
        }
    }

    private Node searchParentNode(Node node, int value) {
        Node temp = node;
        if ((node.left != null && node.left.value == value) || (node.right != null && node.right.value == value)) {
            return temp;
        } else {
            if (temp.right != null && value > temp.value) {
                return searchParentNode(temp.right, value);
            } else if (temp.left != null && value < temp.value) {
                return searchParentNode(temp.left, value);
            } else {
                return null;
            }
        }
    }
}
