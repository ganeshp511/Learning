import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class SetinterfaceExample {
    public static void main(String[] args) {
        Set<Object> set1 = new HashSet<>();
        Set<Object> set2 = new HashSet<>();
        // add() is used to add unordered list. The function adds the element only if
        // the specified element is not already present in the set else the function
        // returns False if the element is already present in the Set.
        set1.add("item1");
        set1.add("item2");
        set1.add("item3");
        System.out.println("Set1 " + set1);
        // This method is used to append all of the elements from the mentioned
        // collection to the existing set. The elements are added randomly without
        // following any specific order.
        set2.addAll(set1);
        System.out.println("Set1 added into Set2: " + set2);
        // This method is used to remove all the elements from the set but not delete
        // the set. The reference for the set still exists.
        set2.clear();
        System.out.println("Clearing Set2 items: " + set2);
        set2.add(set1);
        System.out.println("Set2 " + set2);
        // The contains method in Java's Set interface checks if a specific object is
        // present in the set
        System.out.println("Set2 contains Set1 object: " + set2.contains(set1));
        // This method is used to check whether the set contains all the elements
        // present in the given collection or not. This method returns true if the set
        // contains all the elements and returns false if any of the elements are
        // missing.
        System.out.println("Set2 containsAll Set1: " + set2.containsAll(set1));
        // This method is used to get the hashCode value for this instance of the Set.
        // It returns an integer value which is the hashCode value for this instance of
        // the Set.
        System.out.println("Set2 hashCode: " + set2.hashCode());
        // This method is used to check whether the set is empty or not.
        System.out.println("Set2 isEmpty: " + set2.isEmpty());
        // This method is used to return the iterator of the set. ieterator obj memory
        // reference.
        Iterator<Object> iterator = set2.iterator();
        // Use an Iterator with a Set because it doesn’t support index-based access, and
        // it's the only safe way to remove elements while iterating.
        while (iterator.hasNext()) {
            System.out.println("iterating through Set2: " + iterator.next());
        }
        System.out.println("Size of Set2: " + set2.size());
        // toArray() will convert set elements to array
        Object hashSetArray[] = set2.toArray();
        // Arrays.toString() we need to use here because array dont ovveride object
        // class toString() so we cannot directly use hashSetArray.toString()
        System.out.println("HashSetArray: " + Arrays.toString(hashSetArray));
        System.out.println("Set2: " + set2);

        Iterator<Object> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Object firstObjecct = iterator2.next();
            // The instanceof keyword in Java is used to test whether an object is an
            // instance of a specific class or interface. It returns true if the object is
            // of the specified type (or a subclass/implementation), and false otherwise.
            if (firstObjecct instanceof Set) {
                // (Set<?>) firstObj casts the firstObj (which is of type Object) to a Set type.
                // The cast tells the compiler to treat firstObj as a Set. This is necessary
                // because elements retrieved from a generic collection like Set<Object> are of
                // type Object by default.
                Set<?> innerSet = (Set<?>) firstObjecct;
                // The <?> is a wildcard that means "a set of some unknown type." It allows you
                // to work with the set without knowing the exact type of its elements. For
                // example, Set<String> or Set<Integer> can both be assigned to Set<?>.
                innerSet.remove("item3");
                System.out.println("item3 reoved from first object of Set2: " + innerSet);
            } else {
                System.out.println("First object is not a set");
            }
        }
        /*
         * In Java, you can only cast an object to a class or interface that is in its
         * inheritance hierarchy. This means:
         * 
         * Upcasting: You can always cast a subclass object to its superclass or to any
         * interface it implements. This is safe and often implicit.
         * Example: Dog extends Animal → Dog d = new Dog(); Animal a = (Animal) d;
         * Downcasting: You can cast a superclass reference back to a subclass, but only
         * if the actual object is an instance of that subclass. Use instanceof to check
         * before downcasting.
         * Example: Animal a = new Dog(); Dog d = (Dog) a;
         * Interfaces: You can cast to an interface if the object implements that
         * interface.
         * You cannot cast between unrelated classes (classes that do not share a
         * parent-child relationship). Doing so will cause a ClassCastException at
         * runtime
         */

        /*
         * Here’s an example that demonstrates why you need to use `instanceof` before
         * casting and removing an item:
         * 
         * ````java
         * import java.util.*;
         * 
         * public class InstanceofExample {
         * public static void main(String[] args) {
         * Set<Object> set2 = new HashSet<>();
         * Set<String> innerSet = new HashSet<>(Arrays.asList("item1", "item2",
         * "item3"));
         * String notASet = "I am not a set";
         * 
         * set2.add(innerSet);
         * set2.add(notASet);
         * 
         * for (Object obj : set2) {
         * if (obj instanceof Set) {
         * Set<?> s = (Set<?>) obj;
         * s.remove("item3");
         * System.out.println("Removed 'item3' from: " + s);
         * } else {
         * System.out.println("Not a Set: " + obj);
         * }
         * }
         * }
         * }
         * ````
         ** 
         * Output:**
         * ```
         * Removed 'item3' from: [item1, item2]
         * Not a Set: I am not a set
         * ```
         ** 
         * Explanation:**
         * - The code checks if each object in `set2` is a `Set` using `instanceof`.
         * - If it is, it safely casts and removes `"item3"`.
         * - If not, it prints a message and does not attempt to remove, avoiding a
         * `ClassCastException`.
         */

    }
}