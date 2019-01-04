package linkedlist;

/**
 * 链表
 *
 * @param <E>
 */
public class LinkedList<E> {

    /**
     * 节点
     */
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * 获取链表的元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 链表左侧添加
     *
     * @param e
     */
    public void addFirst(E e) {
        /*
        Node node = new Node(e);
        node.next = head;
        head = node;
        等于下面一句话
        */
        head = new Node(e, head);
        size++;
    }

    /**
     * 添加一个元素在目标索引
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failded. Illegal index.");
        }
        if (index == 0) {
            //第一个节点是不存在上一个节点的，所以这里直接调用addFirst。可以使用虚拟的链表头节点
            addFirst(e);
        } else {
            //第一个引用节点
            Node prev = this.head;
            //取得需要赋值的索引的上一个引用节点
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            //创建需要添加的节点
            /*
            Node node = new Node(e);
            node.next = prev.next;
            prev.next = node;
            */
            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    /**
     * 添加一个元素在链表末尾
     *
     * @param e
     */
    public void addLast(E e) {
        add(size - 1, e);
    }
}
