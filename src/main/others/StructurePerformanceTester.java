package others;

public class StructurePerformanceTester {

    public static double run(Runnable test) {
        long startTime = System.nanoTime();
        test.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
