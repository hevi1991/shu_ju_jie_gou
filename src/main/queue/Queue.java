package queue;

/**
 * 队列
 * @param <E>
 */
public interface Queue<E> {
    /**
     * 入队
     *
     * @param e 内容
     */
    void enqueue(E e);

    /**
     * 出队
     *
     * @return 内容
     */
    E dequeue();

    /**
     * 查看当前队首内容
     *
     * @return 内容
     */
    E getFront();

    /**
     * 取得当前队列内容个数
     *
     * @return 个数
     */
    int getSize();

    /**
     * 队列是否为空
     *
     * @return 结果
     */
    boolean isEmpty();
}
