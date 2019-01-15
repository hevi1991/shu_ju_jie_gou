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
        return contains(root, e);
    }

    /**
     * 包含
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.equals(node.e)) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 删除指定元素
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除以node为根的二分搜索树中值为e的节点，递归算法
     * 返回删除节点后，新的二分搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {// e.compareTo(node.e) == 0
            if (node.left == null) {// 左子树为空的情况，如同removeMin
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {// 右子树为空的情况，如同removeMax
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 带删除节点左右子树均不为空的情况
            /*
            1.找到比待删除的节点大的最小节点，即"待删除节点右子树的最小节点(以下简称successor)"
            2.右子树中删除s这个节点
            3.替代需要删除的节点
             */
            Node successor = minimum(node.right);
            //替代
            successor.right = removeMin(node.right);//删除完successor的右子树
            successor.left = node.left;
            node.left = node.right = null;//被删除的节点脱离关系
            return successor;
        }
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

    /**
     * 前序遍历
     * root开始，一直往下遍历所有子节点，直到叶子节点
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        //模拟访问
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     * 从最小开始升序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        //模拟访问
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后续遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        //模拟访问
        System.out.println(node.e);
    }

    /**
     * 寻找最小值
     *
     * @return
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找最大值
     *
     * @return
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除以node为根的二分搜索树中最小的节点
     * 返回删除节点新的二分搜索树的根
     *
     * @param node 被删除的节点
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            //最小节点的右子树节点（必然小于其父节点）替代了最小节点
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;//一层层传回去，最终输出根节点
    }

    public E removeMax() {
        E res = maximum();
        root = removeMax(root);
        return res;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    private E removeMinByHewei() {
        Node minimum = minimum(root);
        Node parentNode = findParentNodeByHewei(minimum, root);
        if (parentNode == null) {
            //如果找不到它的父节点，意味着minimum就是root
            if (size == 1) {
                root = null;
            } else {
                root = root.right;
            }
        } else {
            parentNode.left = minimum.right;
            minimum.right = null;
        }
        size--;
        return minimum.e;
    }

    /**
     * 查找节点的父节点
     *
     * @param targetNode
     * @param searchingNode
     * @return
     */
    public Node findParentNodeByHewei(Node targetNode, Node searchingNode) {
        if (searchingNode == null) {
            return null;
        }
        if (searchingNode.left != null && searchingNode.right != null) {
            int compareLeft = targetNode.e.compareTo(searchingNode.left.e);
            int compareRight = targetNode.e.compareTo(searchingNode.right.e);
            if (compareLeft == 0 || compareRight == 0) {
                return searchingNode;
            }
            if (compareLeft < 0) {
                return findParentNodeByHewei(targetNode, searchingNode.left);
            } else {
                return findParentNodeByHewei(targetNode, searchingNode.right);
            }
        } else {
            if (searchingNode.left != null) {
                int compareLeft = targetNode.e.compareTo(searchingNode.left.e);
                if (compareLeft == 0) {
                    return searchingNode;
                } else {
                    return findParentNodeByHewei(targetNode, targetNode.left);
                }
            } else if (searchingNode.right != null) {
                int compareRight = targetNode.e.compareTo(searchingNode.right.e);
                if (compareRight == 0) {
                    return searchingNode;
                } else {
                    return findParentNodeByHewei(targetNode, searchingNode.right);
                }
            }
            return null;
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int nextInt = random.nextInt(10000);
            if (i == 0) {
                System.out.println("root: " + nextInt);
            }
            bst.add(nextInt);
        }

//        System.out.println(bst);
//        System.out.println("--------");
//        bst.preOrder();
        System.out.println("--------");
        bst.inOrder();
        System.out.println("--------");
//        bst.postOrder();


        for (int i = 0; i < 10; i++) {
            bst.removeMax();
            System.out.println("--------");
            bst.inOrder();
            System.out.println("--------");
        }
    }
}
