package structrues.tree.binarySrotedTree;

import java.util.function.Consumer;

public class BinarySearchTree<E extends Comparable<E>> {
    public static void main(String[] args) {
        Integer[] treeNodes = {15, 7, 23, 3, 11, 19, 27, 1, 5, 13, 17, 25, 29};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for (Integer treeNode : treeNodes) {
            binarySearchTree.add(treeNode);
        }

        binarySearchTree.treeWalker(binarySearchTree.root, num -> System.out.print(num + " "), TRAVERSAL_ORDER.IN);
        System.out.println();
        binarySearchTree.remove(13);
        binarySearchTree.treeWalker(binarySearchTree.root, num -> System.out.print(num + " "), TRAVERSAL_ORDER.IN);

    }

    /**
     * 遍历顺序枚举
     */
    public enum TRAVERSAL_ORDER {
        // 前序
        PRE,

        // 中序
        IN,

        // 后序
        POST
    }


    /**
     * 二叉搜索树的节点
     *
     * @param <E> 节点元素类型
     */
    private static class Node<E extends Comparable<E>> {

        // 数据元素
        E item;

        // 左孩子
        Node<E> leftChild;

        // 右孩子
        Node<E> rightChild;

        public Node(E item, Node<E> leftChild, Node<E> rightChild) {
            this.item = item;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

    }


    // 二叉搜索树节点个数
    private int size;


    /**
     * 用于查询二叉树节点总数
     *
     * @return 二叉树节点总数
     */
    public int size() {
        return size;
    }


    /**
     * 用于判断二叉树是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // 用于获取二叉搜索树的根节点
    public Node<E> root;

    /**
     * 向二叉搜索树中插入元素
     *
     * @param element 新元素
     */
    public void add(E element) {

        if (root == null) {
            root = new Node<>(element, null, null);
            size++;
            return;
        }

        Node<E> current = root;
        while (true) {
            if (element.compareTo(current.item) > 0) {
                if (current.rightChild == null) {
                    current.rightChild = new Node<>(element, null, null);
                    size++;
                    return;
                } else {
                    current = current.rightChild;
                }
            } else if (element.compareTo(current.item) < 0) {
                if (current.leftChild == null) {
                    current.leftChild = new Node<>(element, null, null);
                    size++;
                    return;
                } else {
                    current = current.leftChild;
                }
            } else {
                return;
            }
        }
    }


    /**
     * 删除整棵树中的指定节点
     *
     * @param element 待删除节点的值
     * @return 若删除成功，则返回被删除节点的值，否则(节点不存在),返回null
     */
    public E remove(E element) {

        // 虚拟节点
        Node<E> pre = new Node<>(null, root, null);

        Node<E> current = root;
        while (current != null) {
            if (element.compareTo(current.item) > 0) {
                pre = current;
                current = current.rightChild;
            } else if (element.compareTo(current.item) < 0) {
                pre = current;
                current = current.leftChild;
            } else {
                break;
            }
        }

        // 待删除的节点不存在
        if (current == null) {
            return null;
        }

        // 1.若待删除节点为叶子节点，直接删除即可
        if (current.leftChild == null && current.rightChild == null) {
            if (pre.leftChild == current) {
                pre.leftChild = null;
            } else {
                pre.rightChild = null;
            }
            size--;
        }
        // 2.若待删除的节点只有一个子节点,唯一的一个子节点代替当前节点即可
        else if (current.leftChild == null || current.rightChild == null) {
            Node<E> child = current.leftChild != null ? current.leftChild : current.rightChild;
            if (pre.leftChild == current) {
                pre.leftChild = child;
            } else {
                pre.rightChild = child;
            }
            size--;
        }
        // 3.待删除的节点有两个子节点,用右子树的最小节点代替当前节点
        else {
            // 1.找到右子树最小节点
            Node<E> minNode = current.rightChild;
            while (minNode.leftChild != null) {
                minNode = minNode.leftChild;
            }
            // 2.删除右子树最小节点
            remove(minNode.item);
            // 3.用右子树最小节点代替当前节点
            minNode.leftChild = current.leftChild;
            minNode.rightChild = current.rightChild;
            if (pre.leftChild == current) {
                pre.leftChild = minNode;
            } else {
                pre.rightChild = minNode;
            }
        }

        if (pre.item == null) {
            root = pre.leftChild;
        }

        return element;
    }

    /**
     * 前序遍历所有节点
     *
     * @param consumer 对节点的处理函数
     */
    public void forEach(Consumer<E> consumer) {
        treeWalker(root, consumer, TRAVERSAL_ORDER.IN);
    }


    /**
     * 按照指定顺序遍所有节点
     *
     * @param consumer 对节点的处理函数
     * @param order    遍历顺序
     */
    public void forEach(Consumer<E> consumer, TRAVERSAL_ORDER order) {
        treeWalker(root, consumer, order);
    }

    /**
     * 对指定子树按照指定顺序进行遍历
     *
     * @param root     子树的根节点
     * @param consumer 对节点的处理函数
     * @param order    遍历顺序
     */
    private void treeWalker(Node<E> root, Consumer<E> consumer, TRAVERSAL_ORDER order) {
        switch (order) {
            case IN:
                if (root != null) {
                    treeWalker(root.leftChild, consumer, order);
                    consumer.accept(root.item);
                    treeWalker(root.rightChild, consumer, order);
                }
                break;
            case PRE:
                if (root != null) {
                    consumer.accept(root.item);
                    treeWalker(root.leftChild, consumer, order);
                    treeWalker(root.rightChild, consumer, order);
                }
                break;
            case POST:
                if (root != null) {
                    treeWalker(root.leftChild, consumer, order);
                    treeWalker(root.rightChild, consumer, order);
                    consumer.accept(root.item);
                }
                break;
        }
    }


    /**
     * 按照中序遍历顺序展示所有节点
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        forEach(item -> sb.append(item).append(","));
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }
}