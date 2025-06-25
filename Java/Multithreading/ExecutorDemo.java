import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        int taskCount = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(taskCount);
        long start = System.currentTimeMillis();

        for (int i = 0; i < taskCount; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(10); // Simulate work
                     System.out.println("Executor task " + taskId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // Wait for all tasks to finish
        executor.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("Executor service took: " + (end - start) + " ms");
    }
}
