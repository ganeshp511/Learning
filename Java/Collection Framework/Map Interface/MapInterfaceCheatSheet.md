Here’s a one-liner cheatsheet for the main Java Map classes, their use, and key differences:

- **HashMap**: Fast, unordered map; allows null keys/values; not thread-safe.
- **LinkedHashMap**: Like HashMap, but maintains insertion order.
- **TreeMap**: Sorted map (by key); no null keys; can use custom Comparator.
- **Hashtable**: Legacy, synchronized (thread-safe) map; no null keys/values.
- **EnumMap**: Map for enum keys only; very fast and memory-efficient.
- **WeakHashMap**: Keys are weakly referenced; entries removed when key is garbage collected.
- **IdentityHashMap**: Uses reference equality (==) for keys, not equals(); allows nulls; rare use.

**Summary:**  
- Use `HashMap` for general purpose.
- Use `LinkedHashMap` for predictable iteration order.
- Use `TreeMap` for sorted keys.
- Use `Hashtable` for legacy thread-safe code.
- Use `EnumMap` for enum keys.
- Use `WeakHashMap` for memory-sensitive caches.
- Use `IdentityHashMap` for reference-based key comparison.