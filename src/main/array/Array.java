package array;

import obj.Student;

/**
 * 数组
 */
public class Array<E> {
    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组得容量构造Array
     *
     * @param capacity 容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造函数，默认10容量
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组大小
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取容量
     *
     * @return 容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 是否为空
     *
     * @return 结果
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 增：数组末尾添加
     * O(1)
     *
     * @param e 元素内容
     */
    public void push(E e) {
        add(size, e);
    }

    /**
     * 增：数组头添加
     * O(n), 因为要挪size-1次
     *
     * @param e 元素内容
     */
    public void pushLeft(E e) {
        add(0, e);
    }

    /**
     * 增：在index位置，插入元素e
     * 概率论知识才能计算时间复杂度，有个期望的概念。
     * O(n/2) = O(n) 通常取最糟糕的情况，及pushLeft，所以添加的时间复杂度是O(n)
     *
     * @param index 下标
     * @param e     元素内容
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {//下标非法
            throw new IllegalArgumentException("Add faild. Require index >= 0 and index <= size.");
        }
        if (size == data.length) {//动态扩容
            resize(2 * data.length);//为什么只扩2倍容量或者说不扩容1个，因为性能的优化
        }
        for (int i = size - 1; i >= index; i--) {//大于指定位置的后续元素，往后拖
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 更新data容量
     * O(n)
     *
     * @param newCapacity 新容量大小
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 查：取得索引对应元素内容
     * O(1)
     *
     * @param index 索引
     * @return 元素内容
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, index over range.");
        }
        return data[index];
    }

    /**
     * 查：包含
     * O(n)
     *
     * @param e 内容
     * @return 结果
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找第一个数组中元素e所在索引，
     * O(n)
     *
     * @param e 内容
     * @return 不存在返回-1，否则返回对应索引
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 改：设置索引元素
     *
     * @param index 索引
     * @param e     元素内容
     */
    void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, index over range.");
        }
        data[index] = e;
    }

    /**
     * 删：删除index元素，返回元素内容
     *
     * @param index 索引
     * @return 元素内容
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, index over range.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 4 && data.length / 2 != 0) {//少于一半的时候，重新设定容量
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删：删除第一位，并取出结果
     * O(n)
     *
     * @return 元素内容
     */
    public E popLeft() {
        return remove(0);
    }

    /**
     * 删：删除最后一位，并取出结果
     * O(1)
     *
     * @return 元素内容
     */
    public E pop() {
        return remove(size - 1);
    }

    /**
     * 删：删除第一个元素内容的对应元素
     * O(n/2)
     *
     * @param e 元素内容
     */
    public boolean removeElement(E e) {
        boolean flag = false;
        int index = find(e);
        if (index != -1) {
            remove(index);
            flag = true;
        }
        return flag;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }


    /**
     * 查看最后一个
     *
     * @return 内容
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 查看第一个
     *
     * @return 内容
     */
    public E getFirst() {
        return get(0);
    }
}
