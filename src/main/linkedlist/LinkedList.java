package linkedlist;

/**
 * 链表
 * O(n)
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

    /**
     * 虚拟头结点
     */
    private Node dummyHead;
    int size;

    public LinkedList() {
        dummyHead = new Node(null);
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
        add(0, e);
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
        //第一个引用节点
        Node prev = dummyHead;
        //取得需要赋值的索引的上一个引用节点，由于使用了虚拟头结点（相当于index：-1），所以这里index不需要-1
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        //创建需要添加的节点
        /*
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
        等于下面一句话
        */
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 添加一个元素在链表末尾
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 取得索引下的元素内容
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取第一个链表元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取最后一个链表元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 设置目标索引为新元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否存在元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除索引的元素
     *
     * @param index
     * @return 被删除元素内容
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;//引用清楚，让JVM进行垃圾回收
        size--;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList: ");
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
