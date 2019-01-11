package bst;

import java.util.Random;

public class BST<E extends Comparable<E>> {

    /**
     * 向二分搜索树中添加新的元素e
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 以node为根的二分搜索树中插入元素e，递归算法
     *
     * @param node
     * @param e
     * @return 返回插入新节点后的二分搜索树的根
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return false;
    }

    public void remove(E e) {

    }

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int nextInt = random.nextInt(100);
            bst.add(nextInt);
        }
    }
}
