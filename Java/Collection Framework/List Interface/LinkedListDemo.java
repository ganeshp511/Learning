import java.util.*;

/**
 * This program demonstrates the usage of all major LinkedList methods in Java with comments.
 *
 * What is LinkedList?
 * -------------------
 * LinkedList is a doubly-linked list implementation of the List and Deque interfaces in Java.
 * It allows for efficient insertions and deletions at both ends and supports all optional list operations.
 *
 * Use Cases:
 * - When you need frequent insertions and deletions from the beginning or middle of a list
 * - Implementing queues, deques, and stacks
 * - When random access is less important than fast modification
 *
 * Note: For random access, ArrayList is usually faster. For frequent add/remove operations, LinkedList is preferred.
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        // Create a LinkedList
        LinkedList<String> list = new LinkedList<>();

        // add(E e): Appends element to the end
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("After add: " + list);

        // add(int index, E element): Inserts element at specific index
        list.add(1, "Date");
        System.out.println("After add at index 1: " + list);

        // addAll(Collection<E> c): Appends all elements from another collection
        List<String> moreFruits = Arrays.asList("Elderberry", "Fig");
        list.addAll(moreFruits);
        System.out.println("After addAll: " + list);

        // addAll(int index, Collection<E> c): Inserts all elements at specific index
        List<String> citrus = Arrays.asList("Grape", "Honeydew");
        list.addAll(2, citrus);
        System.out.println("After addAll at index 2: " + list);

        // addFirst(E e): Inserts element at the beginning
        list.addFirst("Apricot");
        System.out.println("After addFirst: " + list);

        // addLast(E e): Appends element to the end
        list.addLast("Kiwi");
        System.out.println("After addLast: " + list);

        // clear(): Removes all elements
        LinkedList<String> tempList = new LinkedList<>(list);
        tempList.clear();
        System.out.println("After clear (tempList): " + tempList);

        // clone(): Returns a shallow copy
        LinkedList<String> clonedList = (LinkedList<String>) list.clone();
        System.out.println("Cloned list: " + clonedList);

        // contains(Object o): Checks if element exists
        System.out.println("Contains 'Banana'? " + list.contains("Banana"));

        // descendingIterator(): Iterator in reverse order
        System.out.print("Descending iterator: ");
        Iterator<String> descIt = list.descendingIterator();
        while (descIt.hasNext()) {
            System.out.print(descIt.next() + " ");
        }
        System.out.println();

        // element(): Retrieves but does not remove head
        System.out.println("Element (head): " + list.element());

        // get(int index): Gets element at index
        System.out.println("Element at index 3: " + list.get(3));

        // getFirst(): Gets first element
        System.out.println("First element: " + list.getFirst());

        // getLast(): Gets last element
        System.out.println("Last element: " + list.getLast());

        // indexOf(Object o): First index of element
        System.out.println("Index of 'Banana': " + list.indexOf("Banana"));

        // lastIndexOf(Object o): Last index of element
        list.add("Banana");
        System.out.println("Last index of 'Banana': " + list.lastIndexOf("Banana"));

        // listIterator(int index): List iterator from index
        ListIterator<String> it = list.listIterator(2);
        System.out.print("ListIterator from index 2: ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // offer(E e): Adds element as tail
        list.offer("Lemon");
        System.out.println("After offer: " + list);

        // offerFirst(E e): Inserts element at front
        list.offerFirst("Mango");
        System.out.println("After offerFirst: " + list);

        // offerLast(E e): Inserts element at end
        list.offerLast("Nectarine");
        System.out.println("After offerLast: " + list);

        // peek(): Retrieves but does not remove head
        System.out.println("Peek (head): " + list.peek());

        // peekFirst(): Retrieves but does not remove first element
        System.out.println("Peek first: " + list.peekFirst());

        // peekLast(): Retrieves but does not remove last element
        System.out.println("Peek last: " + list.peekLast());

        // poll(): Retrieves and removes head
        System.out.println("Poll (head): " + list.poll());
        System.out.println("After poll: " + list);

        // pollFirst(): Retrieves and removes first element
        System.out.println("Poll first: " + list.pollFirst());
        System.out.println("After pollFirst: " + list);

        // pollLast(): Retrieves and removes last element
        System.out.println("Poll last: " + list.pollLast());
        System.out.println("After pollLast: " + list);

        // pop(): Pops element from stack (removes first)
        System.out.println("Pop: " + list.pop());
        System.out.println("After pop: " + list);

        // push(E e): Pushes element onto stack (adds first)
        list.push("Olive");
        System.out.println("After push: " + list);

        // remove(): Retrieves and removes head
        System.out.println("Remove (head): " + list.remove());
        System.out.println("After remove: " + list);

        // remove(int index): Removes element at index
        list.remove(2);
        System.out.println("After remove at index 2: " + list);

        // remove(Object o): Removes first occurrence of element
        list.remove("Banana");
        System.out.println("After remove 'Banana': " + list);

        // removeFirst(): Removes and returns first element
        System.out.println("Remove first: " + list.removeFirst());
        System.out.println("After removeFirst: " + list);

        // removeFirstOccurrence(Object o): Removes first occurrence
        list.add("Papaya");
        list.add("Papaya");
        list.removeFirstOccurrence("Papaya");
        System.out.println("After removeFirstOccurrence 'Papaya': " + list);

        // removeLast(): Removes and returns last element
        System.out.println("Remove last: " + list.removeLast());
        System.out.println("After removeLast: " + list);

        // removeLastOccurrence(Object o): Removes last occurrence
        list.add("Quince");
        list.add("Quince");
        list.removeLastOccurrence("Quince");
        System.out.println("After removeLastOccurrence 'Quince': " + list);

        // set(int index, E element): Replaces element at index
        list.set(0, "Raspberry");
        System.out.println("After set at index 0: " + list);

        // size(): Number of elements
        System.out.println("Size: " + list.size());

        // spliterator(): Spliterator for traversing
        Spliterator<String> spliterator = list.spliterator();
        System.out.print("Spliterator: ");
        spliterator.forEachRemaining(fruit -> System.out.print(fruit + " "));
        System.out.println();

        // toArray(): Returns array of elements
        Object[] arr = list.toArray();
        System.out.println("toArray: " + Arrays.toString(arr));

        // toArray(T[] a): Returns typed array
        String[] strArr = list.toArray(new String[0]);
        System.out.println("toArray(T[]): " + Arrays.toString(strArr));

        // toString(): String representation
        System.out.println("toString: " + list.toString());
    }
}
