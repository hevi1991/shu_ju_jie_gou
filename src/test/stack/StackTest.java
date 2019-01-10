package stack;

import org.junit.jupiter.api.Test;
import others.StructurePerformanceTester;

import java.util.Random;

public class StackTest {

    public void testStack(Stack<Integer> stack, int opCount) {
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
    }

    @Test
    void test() {
        int opCount = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = StructurePerformanceTester.run(() -> testStack(arrayStack, opCount));
        System.out.println("ArrayStack, time: " + time1 + "s");
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = StructurePerformanceTester.run(() -> testStack(linkedListStack, opCount));
        System.out.println("LinkedListStack, time: " + time2 + "s");
    }
}
