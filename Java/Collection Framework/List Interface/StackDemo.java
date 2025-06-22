import java.util.*;

/**
 * This program demonstrates the usage of all major Stack and inherited Vector methods in Java with comments.
 *
 * What is Stack?
 * --------------
 * Stack is a subclass of Vector in Java that represents a last-in, first-out (LIFO) stack of objects.
 * It provides methods for standard stack operations such as push, pop, peek, and search.
 *
 * Use Cases:
 * - Undo/redo functionality in editors
 * - Expression evaluation and parsing (e.g., parentheses matching)
 * - Backtracking algorithms (e.g., maze solving, recursion simulation)
 * - Reversing data
 *
 * Note: For most modern use cases, Deque (ArrayDeque) is preferred for stack operations due to better performance and flexibility.
 */
public class StackDemo {
    public static void main(String[] args) {
        // Create a Stack
        Stack<String> stack = new Stack<>();

        // empty(): Checks if the stack is empty
        System.out.println("Is stack empty? " + stack.empty());

        // push(Object element): Pushes element onto the stack
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry");
        System.out.println("After push: " + stack);

        // peek(): Returns the top element without removing it
        System.out.println("Peek: " + stack.peek());

        // pop(): Removes and returns the top element
        System.out.println("Pop: " + stack.pop());
        System.out.println("After pop: " + stack);

        // search(Object element): Returns position from top (1-based), or -1 if not found
        System.out.println("Search 'Apple': " + stack.search("Apple"));
        System.out.println("Search 'Mango': " + stack.search("Mango"));

        // add(Object obj): Appends element to end (Vector method)
        stack.add("Date");
        System.out.println("After add: " + stack);

        // add(int index, Object obj): Inserts element at index (Vector method)
        stack.add(1, "Elderberry");
        System.out.println("After add at index 1: " + stack);

        // addAll(Collection c): Appends all elements from collection
        List<String> moreFruits = Arrays.asList("Fig", "Grape");
        stack.addAll(moreFruits);
        System.out.println("After addAll: " + stack);

        // addAll(int index, Collection c): Inserts all elements at index
        List<String> citrus = Arrays.asList("Honeydew", "Indian Fig");
        stack.addAll(2, citrus);
        System.out.println("After addAll at index 2: " + stack);

        // addElement(Object o): Adds element to end
        stack.addElement("Jackfruit");
        System.out.println("After addElement: " + stack);

        // capacity(): Returns current capacity
        System.out.println("Capacity: " + stack.capacity());

        // clear(): Removes all elements
        Stack<String> tempStack = (Stack<String>) stack.clone();
        tempStack.clear();
        System.out.println("After clear (tempStack): " + tempStack);

        // clone(): Returns a shallow copy
        Stack<String> clonedStack = (Stack<String>) stack.clone();
        System.out.println("Cloned stack: " + clonedStack);

        // contains(Object o): Checks if element exists
        System.out.println("Contains 'Banana'? " + stack.contains("Banana"));

        // containsAll(Collection c): Checks if all elements exist
        System.out.println("Contains all [Fig, Grape]? " + stack.containsAll(Arrays.asList("Fig", "Grape")));

        // copyInto(Object[] array): Copies elements into array
        String[] arr = new String[stack.size()];
        stack.copyInto(arr);
        System.out.println("After copyInto array: " + Arrays.toString(arr));

        // elementAt(int index): Gets element at index
        System.out.println("Element at index 3: " + stack.elementAt(3));

        // elements(): Returns enumeration of elements
        System.out.print("Elements enumeration: ");
        Enumeration<String> enumeration = stack.elements();
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " ");
        }
        System.out.println();

        // ensureCapacity(int minCapacity): Ensures minimum capacity
        stack.ensureCapacity(20);
        System.out.println("Ensured capacity for 20 elements.");

        // equals(): Compares for equality
        Stack<String> anotherStack = (Stack<String>) stack.clone();
        System.out.println("Equals anotherStack? " + stack.equals(anotherStack));

        // firstElement(): Gets first element
        System.out.println("First element: " + stack.firstElement());

        // get(int index): Gets element at index
        System.out.println("Element at index 2: " + stack.get(2));

        // hashCode(): Returns hash code
        System.out.println("HashCode: " + stack.hashCode());

        // indexOf(Object o): First index of element
        System.out.println("Index of 'Banana': " + stack.indexOf("Banana"));

        // indexOf(Object o, int index): First index from position
        System.out.println("Index of 'Banana' from 2: " + stack.indexOf("Banana", 2));

        // insertElementAt(Object o, int index): Inserts at index
        stack.insertElementAt("Kiwi", 2);
        System.out.println("After insertElementAt at index 2: " + stack);

        // isEmpty(): Checks if stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty());

        // iterator(): Iterator over elements
        System.out.print("Iterator: ");
        Iterator<String> it = stack.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // lastElement(): Gets last element
        System.out.println("Last element: " + stack.lastElement());

        // lastIndexOf(Object o): Last index of element
        System.out.println("Last index of 'Banana': " + stack.lastIndexOf("Banana"));

        // lastIndexOf(Object o, int index): Last index from position
        System.out.println("Last index of 'Banana' from 5: " + stack.lastIndexOf("Banana", 5));

        // listIterator(): List iterator
        System.out.print("ListIterator: ");
        ListIterator<String> listIt = stack.listIterator();
        while (listIt.hasNext()) {
            System.out.print(listIt.next() + " ");
        }
        System.out.println();

        // listIterator(int index): List iterator from index
        System.out.print("ListIterator from index 3: ");
        ListIterator<String> listIt2 = stack.listIterator(3);
        while (listIt2.hasNext()) {
            System.out.print(listIt2.next() + " ");
        }
        System.out.println();

        // remove(int index): Removes element at index
        stack.remove(2);
        System.out.println("After remove at index 2: " + stack);

        // remove(Object o): Removes first occurrence of element
        stack.remove("Banana");
        System.out.println("After remove 'Banana': " + stack);

        // removeAll(Collection c): Removes all elements in collection
        stack.removeAll(Arrays.asList("Apple", "Fig"));
        System.out.println("After removeAll [Apple, Fig]: " + stack);

        // removeAllElements(): Removes all components
        Stack<String> tempStack2 = (Stack<String>) stack.clone();
        tempStack2.removeAllElements();
        System.out.println("After removeAllElements (tempStack2): " + tempStack2);

        // removeElement(Object o): Removes first occurrence
        stack.removeElement("Date");
        System.out.println("After removeElement 'Date': " + stack);

        // removeElementAt(int index): Removes element at index
        stack.removeElementAt(1);
        System.out.println("After removeElementAt 1: " + stack);

        // removeRange(int fromIndex, int toIndex): Removes elements in range (protected, so use subList)
        stack.addAll(Arrays.asList("Lemon", "Mango", "Nectarine"));
        stack.subList(1, 3).clear();
        System.out.println("After removeRange (1,3) via subList.clear(): " + stack);

        // retainAll(Collection c): Retains only specified elements
        stack.retainAll(Arrays.asList("Jackfruit", "Kiwi"));
        System.out.println("After retainAll [Jackfruit, Kiwi]: " + stack);

        // set(int index, Object o): Replaces element at index
        stack.set(0, "Papaya");
        System.out.println("After set at index 0: " + stack);

        // setElementAt(Object o, int index): Sets element at index
        stack.setElementAt("Quince", 0);
        System.out.println("After setElementAt at index 0: " + stack);

        // setSize(int newSize): Sets the size of the vector
        stack.setSize(2);
        System.out.println("After setSize(2): " + stack);

        // size(): Number of elements
        System.out.println("Size: " + stack.size());

        // subList(int fromIndex, int toIndex): View of portion of stack
        stack.addAll(Arrays.asList("Raspberry", "Strawberry", "Tomato"));
        List<String> sub = stack.subList(0, 2);
        System.out.println("SubList (0,2): " + sub);

        // toArray(): Returns array of elements
        Object[] arr2 = stack.toArray();
        System.out.println("toArray: " + Arrays.toString(arr2));

        // toArray(Object[] array): Returns typed array
        String[] strArr = stack.toArray(new String[0]);
        System.out.println("toArray(T[]): " + Arrays.toString(strArr));

        // toString(): String representation
        System.out.println("toString: " + stack.toString());

        // trimToSize(): Trims capacity to current size
        stack.trimToSize();
        System.out.println("After trimToSize.");
    }
}
