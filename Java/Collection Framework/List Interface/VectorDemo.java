import java.util.*;
import java.util.function.UnaryOperator;

/**
 * This program demonstrates the usage of all major Vector methods in Java with comments.
 *
 * What is Vector?
 * ----------------
 * Vector is a legacy class in Java that implements a growable array of objects.
 * It is synchronized, meaning it is thread-safe for use in multi-threaded environments.
 * Vector allows dynamic resizing, random access, and provides many methods for manipulating elements.
 *
 * Use Cases:
 * - When you need a thread-safe, dynamically resizable array.
 * - When legacy code or APIs require Vector.
 * - For most modern use cases, ArrayList is preferred unless thread safety is specifically needed.
 */
public class VectorDemo {
    public static void main(String[] args) {
        // Create a Vector
        Vector<String> vector = new Vector<>();

        // add(E e): Appends element to the end
        vector.add("Apple");
        vector.add("Banana");
        vector.add("Cherry");
        System.out.println("After add: " + vector);

        // add(int index, E element): Inserts element at index
        vector.add(1, "Date");
        System.out.println("After add at index 1: " + vector);

        // addAll(Collection<? extends E> c): Appends all elements from collection
        List<String> moreFruits = Arrays.asList("Elderberry", "Fig");
        vector.addAll(moreFruits);
        System.out.println("After addAll: " + vector);

        // addAll(int index, Collection<? extends E> c): Inserts all elements at index
        List<String> citrus = Arrays.asList("Grape", "Honeydew");
        vector.addAll(2, citrus);
        System.out.println("After addAll at index 2: " + vector);

        // addElement(E obj): Adds element to end
        vector.addElement("Kiwi");
        System.out.println("After addElement: " + vector);

        // capacity(): Returns current capacity
        System.out.println("Capacity: " + vector.capacity());

        // clear(): Removes all elements
        Vector<String> tempVector = (Vector<String>) vector.clone();
        tempVector.clear();
        System.out.println("After clear (tempVector): " + tempVector);

        // clone(): Returns a shallow copy
        Vector<String> clonedVector = (Vector<String>) vector.clone();
        System.out.println("Cloned vector: " + clonedVector);

        // contains(Object o): Checks if element exists
        System.out.println("Contains 'Banana'? " + vector.contains("Banana"));

        // containsAll(Collection<?> c): Checks if all elements exist
        System.out.println("Contains all [Fig, Grape]? " + vector.containsAll(Arrays.asList("Fig", "Grape")));

        // copyInto(Object[] anArray): Copies elements into array
        String[] arr = new String[vector.size()];
        vector.copyInto(arr);
        System.out.println("After copyInto array: " + Arrays.toString(arr));

        // elementAt(int index): Gets element at index
        System.out.println("Element at index 3: " + vector.elementAt(3));

        // elements(): Returns enumeration of elements
        System.out.print("Elements enumeration: ");
        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " ");
        }
        System.out.println();

        // ensureCapacity(int minCapacity): Ensures minimum capacity
        vector.ensureCapacity(20);
        System.out.println("Ensured capacity for 20 elements.");

        // equals(Object o): Compares for equality
        Vector<String> anotherVector = (Vector<String>) vector.clone();
        System.out.println("Equals anotherVector? " + vector.equals(anotherVector));

        // firstElement(): Gets first element
        System.out.println("First element: " + vector.firstElement());

        // forEach(Consumer<? super E> action): Performs action for each element
        System.out.print("forEach: ");
        vector.forEach(fruit -> System.out.print(fruit + " | "));
        System.out.println();

        // get(int index): Gets element at index
        System.out.println("Element at index 2: " + vector.get(2));

        // hashCode(): Returns hash code
        System.out.println("HashCode: " + vector.hashCode());

        // indexOf(Object o): First index of element
        System.out.println("Index of 'Banana': " + vector.indexOf("Banana"));

        // indexOf(Object o, int index): First index from position
        System.out.println("Index of 'Banana' from 2: " + vector.indexOf("Banana", 2));

        // insertElementAt(E obj, int index): Inserts at index
        vector.insertElementAt("Lemon", 2);
        System.out.println("After insertElementAt at index 2: " + vector);

        // isEmpty(): Checks if vector is empty
        System.out.println("Is vector empty? " + vector.isEmpty());

        // iterator(): Iterator over elements
        System.out.print("Iterator: ");
        Iterator<String> it = vector.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // lastElement(): Gets last element
        System.out.println("Last element: " + vector.lastElement());

        // lastIndexOf(Object o): Last index of element
        System.out.println("Last index of 'Banana': " + vector.lastIndexOf("Banana"));

        // lastIndexOf(Object o, int index): Last index from position
        System.out.println("Last index of 'Banana' from 5: " + vector.lastIndexOf("Banana", 5));

        // listIterator(): List iterator
        System.out.print("ListIterator: ");
        ListIterator<String> listIt = vector.listIterator();
        while (listIt.hasNext()) {
            System.out.print(listIt.next() + " ");
        }
        System.out.println();

        // listIterator(int index): List iterator from index
        System.out.print("ListIterator from index 3: ");
        ListIterator<String> listIt2 = vector.listIterator(3);
        while (listIt2.hasNext()) {
            System.out.print(listIt2.next() + " ");
        }
        System.out.println();

        // remove(int index): Removes element at index
        vector.remove(2);
        System.out.println("After remove at index 2: " + vector);

        // remove(Object o): Removes first occurrence of element
        vector.remove("Banana");
        System.out.println("After remove 'Banana': " + vector);

        // removeAll(Collection<?> c): Removes all elements in collection
        vector.removeAll(Arrays.asList("Apple", "Fig"));
        System.out.println("After removeAll [Apple, Fig]: " + vector);

        // removeAllElements(): Removes all components
        Vector<String> tempVector2 = (Vector<String>) vector.clone();
        tempVector2.removeAllElements();
        System.out.println("After removeAllElements (tempVector2): " + tempVector2);

        // removeElement(Object obj): Removes first occurrence
        vector.removeElement("Date");
        System.out.println("After removeElement 'Date': " + vector);

        // removeElementAt(int index): Removes element at index
        vector.removeElementAt(1);
        System.out.println("After removeElementAt 1: " + vector);

        // removeIf(Predicate<? super E> filter): Removes elements matching predicate
        vector.removeIf(fruit -> fruit.startsWith("H"));
        System.out.println("After removeIf (starts with 'H'): " + vector);

        // removeRange(int fromIndex, int toIndex): Removes elements in range (protected, so use subList)
        vector.addAll(Arrays.asList("Mango", "Nectarine", "Orange"));
        vector.subList(1, 3).clear();
        System.out.println("After removeRange (1,3) via subList.clear(): " + vector);

        // replaceAll(UnaryOperator<E> operator): Replaces each element using operator
        vector.replaceAll(fruit -> fruit.toUpperCase());
        System.out.println("After replaceAll (toUpperCase): " + vector);

        // retainAll(Collection<?> c): Retains only specified elements
        vector.retainAll(Arrays.asList("KIWI", "LEMON"));
        System.out.println("After retainAll [KIWI, LEMON]: " + vector);

        // set(int index, E element): Replaces element at index
        if (!vector.isEmpty()) {
            vector.set(0, "Papaya");
            System.out.println("After set at index 0: " + vector);
        }

        // setElementAt(E obj, int index): Sets element at index
        if (!vector.isEmpty()) {
            vector.setElementAt("Quince", 0);
            System.out.println("After setElementAt at index 0: " + vector);
        }

        // setSize(int newSize): Sets the size of the vector
        vector.setSize(2);
        System.out.println("After setSize(2): " + vector);

        // size(): Number of elements
        System.out.println("Size: " + vector.size());

        // Remove nulls before sorting to avoid NullPointerException
        vector.removeIf(Objects::isNull);
        // sort(Comparator<? super E> c): Sorts the vector
        vector.addAll(Arrays.asList("Raspberry", "Strawberry", "Tomato"));
        vector.sort(Comparator.naturalOrder());
        System.out.println("After sort: " + vector);

        // spliterator(): Spliterator for traversing
        Spliterator<String> spliterator = vector.spliterator();
        System.out.print("Spliterator: ");
        spliterator.forEachRemaining(fruit -> System.out.print(fruit + " "));
        System.out.println();

        // subList(int fromIndex, int toIndex): View of portion of vector
        List<String> sub = vector.subList(0, Math.min(2, vector.size()));
        System.out.println("SubList (0,2): " + sub);

        // toArray(): Returns array of elements
        Object[] arr2 = vector.toArray();
        System.out.println("toArray: " + Arrays.toString(arr2));

        // toArray(T[] a): Returns typed array
        String[] strArr = vector.toArray(new String[0]);
        System.out.println("toArray(T[]): " + Arrays.toString(strArr));

        // toString(): String representation
        System.out.println("toString: " + vector.toString());

        // trimToSize(): Trims capacity to current size
        vector.trimToSize();
        System.out.println("After trimToSize.");
    }
}
