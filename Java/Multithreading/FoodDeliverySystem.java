/*
 * Mini Project: Online Food Delivery System (Threaded)
 * Demonstrates: All core multithreading concepts from basics to advanced
 */

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FoodDeliverySystem {

    // Shared BlockingQueue acts as a thread-safe order buffer between producers and consumers
    private static final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(10);

    // Fixed ThreadPool to simulate 3 delivery agents
    private static final ExecutorService deliveryPool = Executors.newFixedThreadPool(3);

    // Atomic counter to safely increment order IDs across threads
    private static final AtomicInteger orderIdCounter = new AtomicInteger(1);

    // Lock for thread-safe access to the log
    private static final ReentrantLock logLock = new ReentrantLock();

    // Synchronized list to keep delivery logs
    private static final List<String> deliveryLog = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread started: " + Thread.currentThread().getName());

        // Producer thread for generating customer orders
        Thread producerThread = new Thread(new OrderProducer(), "OrderProducer");
        producerThread.setPriority(Thread.MAX_PRIORITY);
        producerThread.start();

        // 3 DeliveryAgent threads (via ExecutorService)
        for (int i = 1; i <= 3; i++) {
            deliveryPool.submit(new DeliveryAgent("DeliveryAgent-" + i));
        }

        // Let the system run for 8 seconds
        Thread.sleep(8000);

        // Gracefully stop producer thread and shut down delivery thread pool
        producerThread.interrupt();
        deliveryPool.shutdown();
        deliveryPool.awaitTermination(5, TimeUnit.SECONDS);

        // Print final delivery logs
        printDeliveryLog();
        System.out.println("System terminated. Main thread: " + Thread.currentThread().getName());
    }

    // Represents a customer's order
    static class Order {
        int orderId;
        String foodItem;

        Order(int id, String item) {
            this.orderId = id;
            this.foodItem = item;
        }
    }

    // Producer that keeps adding orders to the queue
    static class OrderProducer implements Runnable {
        private final String[] menu = {"Burger", "Pizza", "Fries", "Noodles", "Biryani"};

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500); // simulate time taken to place a new order
                    int id = orderIdCounter.getAndIncrement();
                    Order order = new Order(id, menu[new Random().nextInt(menu.length)]);
                    orderQueue.put(order); // block if queue is full
                    log("[NEW ORDER] OrderID: " + id + ", Item: " + order.foodItem);
                } catch (InterruptedException e) {
                    log("OrderProducer interrupted. Stopping order generation.");
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Consumer that delivers orders from the queue
    static class DeliveryAgent implements Runnable {
        private final String agentName;

        DeliveryAgent(String name) {
            this.agentName = name;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !deliveryPool.isShutdown()) {
                try {
                    // Try to get an order within 2 seconds, or yield if none
                    Order order = orderQueue.poll(2, TimeUnit.SECONDS);
                    if (order != null) {
                        Thread.sleep(new Random().nextInt(1000)); // simulate delivery time
                        log(agentName + " delivered OrderID: " + order.orderId);
                    } else {
                        log(agentName + " found no orders. Yielding...");
                        Thread.yield();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Thread-safe logging method
    static void log(String message) {
        logLock.lock();
        try {
            deliveryLog.add(message);
            System.out.println(message);
        } finally {
            logLock.unlock();
        }
    }

    // Print all delivery logs after system shutdown
    static void printDeliveryLog() {
        System.out.println("\n========= DELIVERY LOG =========");
        deliveryLog.forEach(System.out::println);
        System.out.println("================================");
    }
}
