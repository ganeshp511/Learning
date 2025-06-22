# Java Queue Cheatsheet (One-liner)

`Queue` = FIFO; `Deque` = double-ended (queue/stack); `PriorityQueue` = ordered by priority; `BlockingQueue` = thread-safe, blocking ops; `LinkedList`/`ArrayDeque` = general-purpose; use based on order, access, and concurrency needs.

Here is a deeper explanation of the Java Queue hierarchy:

1. **Collection Interface**
   - The root interface for all collection types in Java. It defines basic methods like `add`, `remove`, `size`, and `iterator`.

2. **Queue Interface**
   - Extends `Collection`.
   - Represents a collection designed for holding elements prior to processing, typically in FIFO (First-In-First-Out) order.
   - Adds methods specific to queue operations: `offer`, `poll`, `peek`, `remove`, `element`.

3. **Deque Interface** (Double Ended Queue)
   - Extends `Queue`.
   - Allows insertion and removal of elements from both ends (head and tail).
   - Adds methods like `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

4. **Implementing Classes:**
   - **LinkedList**: Implements both `Queue` and `Deque`. Can be used as a queue, stack, or double-ended queue.
   - **ArrayDeque**: Implements `Deque`. Provides a resizable array implementation of a double-ended queue.
   - **PriorityQueue**: Implements `Queue`. Orders elements according to their natural ordering or a comparator, not strictly FIFO.
   - **Concurrent Queues** (in `java.util.concurrent`): Thread-safe implementations like `LinkedBlockingQueue`, `PriorityBlockingQueue`, `ArrayBlockingQueue`, `ConcurrentLinkedQueue`.

**Visual Hierarchy:**
```
java.lang.Object
  └── java.util.Collection (interface)
        └── java.util.Queue (interface)
              ├── java.util.Deque (interface)
              │     ├── java.util.LinkedList (class)
              │     └── java.util.ArrayDeque (class)
              ├── java.util.PriorityQueue (class)
              └── java.util.concurrent.BlockingQueue (interface)
                    ├── java.util.concurrent.LinkedBlockingQueue (class)
                    ├── java.util.concurrent.PriorityBlockingQueue (class)
                    └── etc.
```

**Key Points:**
- `Queue` is for FIFO structures.
- `Deque` is for double-ended queues (can be used as stack or queue).
- `PriorityQueue` is for priority-based ordering.
- `BlockingQueue` and its implementations are for thread-safe, blocking operations in concurrent programming.

Let me know if you want this detailed explanation added as comments to your code or need a diagram!