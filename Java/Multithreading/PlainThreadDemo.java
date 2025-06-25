public class PlainThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        int taskCount = 1000;
        Thread[] threads = new Thread[taskCount];
        long start = System.currentTimeMillis();

        for (int i = 0; i < taskCount; i++) {
            final int taskId = i;
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(10); // Simulate work
                     System.out.println("Thread task " + taskId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join(); // Wait for all threads to finish
        }

        long end = System.currentTimeMillis();
        System.out.println("Plain threads took: " + (end - start) + " ms");
    }
}
