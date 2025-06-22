// Queue Interface Hierarchy in Java:
//
// java.lang.Object
//   └── java.util.Collection (interface)
//         └── java.util.Queue (interface)
//               ├── java.util.Deque (interface)
//               │     ├── java.util.LinkedList (class)
//               │     └── java.util.ArrayDeque (class)
//               ├── java.util.PriorityQueue (class)
//               └── java.util.concurrent.* (various queue classes)
//
// Example below demonstrates usage of Queue interface with LinkedList implementation.

import java.util.Queue;
import java.util.LinkedList;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        // Adding elements
        queue.add("A");
        queue.offer("B");
        queue.add("C");

        // Retrieving and removing elements
        System.out.println(queue.remove()); // Output: A
        System.out.println(queue.poll());   // Output: B

        // Retrieving without removing
        System.out.println(queue.element()); // Output: C
        System.out.println(queue.peek());    // Output: C
    }
}
