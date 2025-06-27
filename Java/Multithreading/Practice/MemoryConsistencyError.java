class MemoryConsistencyError {
    // Declare flag and value as fields
    // adding volatile for instant visi
    static volatile boolean flag = false;
    static int value = 0;
    private static final Object lock = new Object();
    /*
     * The lock object is used as a monitor for synchronization. When you write:
     * 
     * it ensures that only one thread at a time can execute the code inside the
     * block for that lock object. This provides:
     * 
     * Mutual exclusion: Prevents multiple threads from modifying or reading shared
     * variables at the same time.
     * Memory consistency: Ensures that changes made by one thread to shared
     * variables are visible to other threads that synchronize on the same lock.
     */

    public static void main(String args[]) {

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                flag = true;
                value = 42;
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                if (flag == true) {
                    System.out.println("Value:" + value);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
/*
 * Yes, this is a **correct example** of a potential memory consistency error in
 * Java.
 * 
 * ### Why?
 * - Both `flag` and `value` are `static` fields shared between threads.
 * - `thread1` sets `flag = true` and then `value = 42`.
 * - `thread2` checks `flag` and, if true, prints `value`.
 * 
 * ### The Problem
 * Without proper synchronization, the following can happen:
 * - `thread2` may see `flag == true` **before** it sees `value == 42` (due to
 * CPU caching, compiler reordering, or memory visibility issues).
 * - This means `thread2` could print `Value: 0` or nothing at all, even though
 * `flag` is `true`.
 * 
 * ### How to Fix
 * To avoid memory consistency errors, you should use synchronization or declare
 * `flag` as `volatile`:
 * 
 * ````java
 * static volatile boolean flag = false;
 * ````
 * 
 * or use synchronized blocks.
 * 
 * ---
 ** 
 * Summary:**
 * Your code is a classic demonstration of a memory consistency error risk in
 * Java multithreading.
 */