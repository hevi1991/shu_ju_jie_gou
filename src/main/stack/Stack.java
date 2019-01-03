package stack;

/**
 * 栈
 *
 * @param <E> 泛型
 */
public interface Stack<E> {
    /**
     * 查询当前栈大小
     *
     * @return 大小
     */
    int getSize();

    /**
     * 判断栈是否为空
     *
     * @return 判断结果
     */
    boolean isEmpty();

    /**
     * 入栈
     *
     * @param e 内容
     */
    void push(E e);

    /**
     * 出栈
     *
     * @return 内容
     */
    E pop();

    /**
     * 看一眼栈顶元素
     *
     * @return 元素内容
     */
    E peek();

}
