The **List** interface in Java is part of the Java Collections Framework and defines an ordered collection (also known as a sequence) that allows duplicate elements. Here’s a simplified hierarchy:

```
java.lang.Object
   └── java.util.Collection<E>
         └── java.util.List<E>
```

### Key Points in the Hierarchy

- **Collection**: The root interface for all collections (except maps).
- **List**: Extends Collection, adds positional access, search, iteration, and range-view operations.

### Main Implementations of List

- **ArrayList**: Resizable array implementation.
- **LinkedList**: Doubly-linked list implementation.
- **Vector**: Synchronized, legacy resizable array.
  - **Stack**: Subclass of Vector, represents a LIFO stack.

### Diagram

```
Collection
   |
   +-- List
         |
         +-- ArrayList
         +-- LinkedList
         +-- Vector
               |
               +-- Stack
```

### Summary

- **List** is a subinterface of **Collection**.
- Common implementations: **ArrayList**, **LinkedList**, **Vector**, **Stack**.
- Lists allow duplicates and maintain insertion order.