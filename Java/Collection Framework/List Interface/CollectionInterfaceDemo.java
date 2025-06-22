import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * This program demonstrates the usage of all major methods from the List interface (ArrayList implementation) in Java.
 */
public class CollectionInterfaceDemo {
    public static void main(String[] args) {
        // Create an ArrayList
        ArrayList<String> list = new ArrayList<>();

        // add(Object o): Appends element to the end
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("After add: " + list);

        // add(int index, Object element): Inserts element at specific index
        list.add(1, "Date");
        System.out.println("After add at index 1: " + list);

        // addAll(Collection C): Appends all elements from another collection
        List<String> moreFruits = Arrays.asList("Elderberry", "Fig");
        list.addAll(moreFruits);
        System.out.println("After addAll: " + list);

        // addAll(int index, Collection C): Inserts all elements at specific index
        List<String> citrus = Arrays.asList("Grape", "Honeydew");
        list.addAll(2, citrus);
        System.out.println("After addAll at index 2: " + list);

        // clear(): Removes all elements
        ArrayList<String> tempList = new ArrayList<>(list);
        tempList.clear();
        System.out.println("After clear (tempList): " + tempList);

        // clone(): Returns a shallow copy
        ArrayList<String> clonedList = (ArrayList<String>) list.clone();
        System.out.println("Cloned list: " + clonedList);

        // contains(Object o): Checks if element exists
        System.out.println("Contains 'Banana'? " + list.contains("Banana"));

        // ensureCapacity(int minCapacity): Ensures minimum capacity
        list.ensureCapacity(20);
        System.out.println("Ensured capacity for 20 elements.");

        // forEach(Consumer<? super E> action): Performs action for each element
        System.out.print("forEach: ");
        list.forEach(fruit -> System.out.print(fruit + " | "));
        System.out.println();

        // get(int index): Gets element at index
        System.out.println("Element at index 3: " + list.get(3));

        // indexOf(Object O): First index of element
        System.out.println("Index of 'Banana': " + list.indexOf("Banana"));

        // isEmpty(): Checks if list is empty
        System.out.println("Is list empty? " + list.isEmpty());

        // lastIndexOf(Object O): Last index of element
        list.add("Banana");
        System.out.println("Last index of 'Banana': " + list.lastIndexOf("Banana"));

        // listIterator(): List iterator
        ListIterator<String> it = list.listIterator();
        System.out.print("ListIterator: ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // listIterator(int index): List iterator from index
        ListIterator<String> it2 = list.listIterator(2);
        System.out.print("ListIterator from index 2: ");
        while (it2.hasNext()) {
            System.out.print(it2.next() + " ");
        }
        System.out.println();

        // remove(int index): Removes element at index
        list.remove(2);
        System.out.println("After remove at index 2: " + list);

        // remove(Object o): Removes first occurrence of element
        list.remove("Banana");
        System.out.println("After remove 'Banana': " + list);

        // removeAll(Collection c): Removes all elements in collection
        list.removeAll(Arrays.asList("Apple", "Fig"));
        System.out.println("After removeAll [Apple, Fig]: " + list);

        // removeIf(Predicate filter): Removes elements matching predicate
        list.removeIf(fruit -> fruit.startsWith("H"));
        System.out.println("After removeIf (starts with 'H'): " + list);

        // Add more elements for further demonstration
        list.addAll(Arrays.asList("Kiwi", "Lemon", "Mango", "Nectarine", "Orange"));
        System.out.println("After adding more fruits: " + list);

        // removeRange(int fromIndex, int toIndex): Removes elements in range (protected, so use subList)
        list.subList(2, 4).clear(); // Removes elements at index 2 and 3
        System.out.println("After removeRange (2,4) via subList.clear(): " + list);

        // retainAll(Collection<?> c): Retains only specified elements
        list.retainAll(Arrays.asList("Kiwi", "Orange"));
        System.out.println("After retainAll [Kiwi, Orange]: " + list);

        // set(int index, E element): Replaces element at index
        list.set(0, "Papaya");
        System.out.println("After set at index 0: " + list);

        // size(): Number of elements
        System.out.println("Size: " + list.size());

        // spliterator(): Spliterator for traversing
        Spliterator<String> spliterator = list.spliterator();
        System.out.print("Spliterator: ");
        spliterator.forEachRemaining(fruit -> System.out.print(fruit + " "));
        System.out.println();

        // subList(int fromIndex, int toIndex): View of portion of list
        list.addAll(Arrays.asList("Quince", "Raspberry", "Strawberry"));
        List<String> sub = list.subList(0, 2);
        System.out.println("SubList (0,2): " + sub);

        // toArray(): Returns array of elements
        Object[] arr = list.toArray();
        System.out.println("toArray: " + Arrays.toString(arr));

        // toArray(Object[] O): Returns typed array
        String[] strArr = list.toArray(new String[0]);
        System.out.println("toArray(T[]): " + Arrays.toString(strArr));

        // trimToSize(): Trims capacity to current size
        list.trimToSize();
        System.out.println("After trimToSize.");

        // Demonstrate CopyOnWriteArrayList (thread-safe collection): Safe for concurrent modification
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
        cowList.addAll(Arrays.asList("Honeydew", "Indian Fig", "Jackfruit"));
        System.out.println("CopyOnWriteArrayList: " + cowList);
        cowList.remove("Indian Fig");
        System.out.println("After remove in CopyOnWriteArrayList: " + cowList);

        // Demonstrate ConcurrentLinkedQueue (concurrent collection): Non-blocking queue for high concurrency
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.add("Kiwi");
        queue.add("Lemon");
        queue.offer("Mango"); // offer() is similar to add(), but returns false if it fails
        System.out.println("ConcurrentLinkedQueue: " + queue);
        System.out.println("Polled from queue: " + queue.poll()); // poll() retrieves and removes the head, or returns null
        System.out.println("After poll: " + queue);

        // clear(): Removes all elements from the collection
        list.clear();
        System.out.println("After clear: " + list);
    }
}
