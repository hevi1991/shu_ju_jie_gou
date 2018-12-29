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
    Array(int capacity) {
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
     *
     * @param e 元素内容
     */
    public void push(E e) {
        add(size, e);
    }

    /**
     * 增：数组头添加
     *
     * @param e 元素内容
     */
    public void pushLeft(E e) {
        add(0, e);
    }

    /**
     * 增：在index位置，插入元素e
     *
     * @param index 下标
     * @param e     元素内容
     */
    public void add(int index, E e) {
        if (size == data.length) {
            throw new IllegalArgumentException("Add faild. Array is full.");
        }
        if (index < 0 || index > size) {//下标非法
            throw new IllegalArgumentException("Add faild. Require index >= 0 and index <= size.");
        }
        for (int i = size - 1; i >= index; i--) {//大于指定位置的后续元素，往后拖
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 查：取得索引对应元素内容
     *
     * @param index 索引
     * @return 元素内容
     */
    E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, index over range.");
        }
        return data[index];
    }

    /**
     * 查：包含
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
    E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, index over range.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        return ret;
    }

    /**
     * 删：删除第一位，并取出结果
     *
     * @return 元素内容
     */
    E pullLeft() {
        return remove(0);
    }

    /**
     * 删：删除最后一位，并取出结果
     *
     * @return 元素内容
     */
    E pull() {
        return remove(size - 1);
    }

    /**
     * 删：删除第一个元素内容的对应元素
     *
     * @param e 元素内容
     */
    boolean removeElement(E e) {
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

    public static void main(String[] args) {
        Array<Student> arr = new Array<>();
        arr.push(new Student("Alice", 100));
        arr.push(new Student("Bob", 90));
        arr.push(new Student("Charlie", 80));
        System.out.println(arr);
    }
}
