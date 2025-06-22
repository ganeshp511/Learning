The **Map interface hierarchy** in Java is as follows:

- **Map (interface)**  
  The root interface for key-value pair collections. Unlike Collection, it does not extend Collection.

    - **HashMap**  
      Implements Map using a hash table. Allows null keys and values. Not synchronized.

    - **LinkedHashMap**  
      Extends HashMap. Maintains insertion order.

    - **TreeMap**  
      Implements Map using a red-black tree. Keys are sorted (natural order or by Comparator).

    - **Hashtable**  
      Legacy class. Synchronized, does not allow null keys or values.

    - **EnumMap**  
      Specialized Map for enum keys.

    - **WeakHashMap**  
      Keys are held with weak references, so they can be garbage collected.

    - **IdentityHashMap**  
      Uses reference equality (==) instead of equals() for keys.

**Subinterfaces:**
- **SortedMap**  
  Extends Map. Keys are sorted (TreeMap implements this).

- **NavigableMap**  
  Extends SortedMap. Adds navigation methods (TreeMap implements this).

**Diagram:**
```
Map
 ├── HashMap
 │    └── LinkedHashMap
 ├── TreeMap (implements NavigableMap → SortedMap → Map)
 ├── Hashtable
 ├── EnumMap
 ├── WeakHashMap
 └── IdentityHashMap
```

**Note:**  
- `Map` is not a subtype of `Collection`.
- `TreeMap` implements `NavigableMap`, which extends `SortedMap`, which extends `Map`.

Most classes that implement the `Map` interface in Java—such as `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable`, `EnumMap`, `WeakHashMap`, and `IdentityHashMap`—**support the same set of core methods** defined by the `Map` interface (like `put()`, `get()`, `remove()`, `containsKey()`, `containsValue()`, `keySet()`, `values()`, `entrySet()`, etc.).

However, **some behaviors may differ**:
- **Null keys/values:**  
  - `HashMap`, `LinkedHashMap`, `WeakHashMap`, and `IdentityHashMap` allow null keys and values.
  - `TreeMap` allows null values but not null keys (unless using a custom Comparator).
  - `Hashtable` and `EnumMap` do **not** allow null keys or values.
- **Ordering:**  
  - `HashMap` and `Hashtable` do not guarantee order.
  - `LinkedHashMap` maintains insertion order.
  - `TreeMap` sorts keys.
- **Thread safety:**  
  - `Hashtable` is synchronized (thread-safe).
  - Others are not thread-safe by default.

**Summary:**  
All these classes use the same methods (from the `Map` interface), but their behavior and performance characteristics can differ.

