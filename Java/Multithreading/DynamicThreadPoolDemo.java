import java.util.concurrent.*;

public class DynamicThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        int taskCount = 100;
        int cpuCores = Runtime.getRuntime().availableProcessors(); // Get CPU core count
        System.out.println("CPU cores detected: " + cpuCores);

        ExecutorService executor = Executors.newFixedThreadPool(cpuCores);
        CountDownLatch latch = new CountDownLatch(taskCount);
        long start = System.currentTimeMillis();

        for (int i = 0; i < taskCount; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    System.out.println("Running task " + taskId + " on thread: " + Thread.currentThread().getName());
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start) + " ms");
    }
}
