package queue;

import org.junit.jupiter.api.Test;
import others.StructurePerformanceTester;

import java.util.Random;

class QueueTest {

    /**
     * 测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位秒
     *
     * @param q       队列
     * @param opCount 运行个数
     * @return 运行时间（单位：秒）
     */
    private static void testQueue(Queue<Integer> q, int opCount) {
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }
    }

    @Test
    void test() {
        int opCount = 200000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = StructurePerformanceTester.run(() -> testQueue(arrayQueue, opCount));
        System.out.println("ArrayQueue, time: " + time1 + "s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = StructurePerformanceTester.run(() -> testQueue(loopQueue, opCount));
        System.out.println("LoopQueue, time: " + time2 + "s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = StructurePerformanceTester.run(() -> testQueue(linkedListQueue, opCount));
        System.out.println("LinkedListQueue, time: " + time3 + "s");
    }
}