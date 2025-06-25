
public class MultithreadingExample {
    public static void main(String args[]) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        // create object of Thread3 class and assign to Runnable instance
        Runnable runnable = new Thread3();
        // pass that instance to Thread class constructor
        Thread thread3 = new Thread(runnable);

        Thread thread4 = new Thread(() -> {
            System.out.println("Thread4 running");
        });

        Thread thread5 = new Thread() {
            public void run() {
                System.out.println("Thread5 running");
            }
        };
        thread3.setName("Worker-3 ");
        thread3.setPriority(Thread.MAX_PRIORITY);

        thread3.start();
        thread4.start();
        thread5.start();

        try {
            // thread3.join();

        } catch (Exception e) {
            System.out.println(e);
        }

        thread1.start();

        thread2.start();

        System.out.println("Thread 3 alive? " + thread3.isAlive());
        System.out.println("Current thread: " + Thread.currentThread());

    }

}
