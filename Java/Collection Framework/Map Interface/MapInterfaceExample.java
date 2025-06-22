import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Collection;

public class MapInterfaceExample{
    public static void main(String args[]){
        Map <String,Integer> map1=new HashMap<>();
        // put(Object, Object): Adds key-value pairs
        map1.put("key1",1);
        map1.put("key2",2);
        System.out.println("Map elements: "+map1);

        // get(Object): Retrieves value for a key
        System.out.println("Value for key1: "+map1.get("key1"));

        // getOrDefault(Object key, V defaultValue): Gets value or default if key not present
        System.out.println("Value for key3 or default: "+map1.getOrDefault("key3", 100));

        // putIfAbsent(K key, V value): Adds only if key is absent
        map1.putIfAbsent("key2", 5); // won't change value for key2
        map1.putIfAbsent("key3", 3); // will add key3
        System.out.println("After putIfAbsent: "+map1);

        // merge(K key, V value, BiFunction): Merges value for a key
        map1.merge("key1", 10, new java.util.function.BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer oldVal, Integer newVal) {
            return oldVal + newVal;
            }
        }); // key1 = 1+10=11
        System.out.println("After merge: "+map1);

        // containsKey(Object): Checks if key exists
        System.out.println("Contains key 'key1': "+map1.containsKey("key1"));

        // containsValue(Object): Checks if value exists
        System.out.println("Contains value 2: "+map1.containsValue(2));

        // remove(Object): Removes entry by key
        map1.remove("key2");
        System.out.println("After remove key2: "+map1);

        // size(): Number of entries
        System.out.println("Size of map1: "+map1.size());

        // isEmpty(): Checks if map is empty
        System.out.println("Is map1 empty? "+map1.isEmpty());

        // keySet(): Set of all keys
        Set<String> keys = map1.keySet();
        System.out.println("All keys of map1: "+keys);

        // values(): Collection of all values
        Collection<Integer> values = map1.values();
        System.out.println("All values of map1: "+values);

        // entrySet(): Set of all key-value pairs
        Set<Map.Entry<String,Integer>> entries = map1.entrySet();
        System.out.println("All entries of map1: "+entries);

        // hashCode(): Hash code of the map
        System.out.println("Hash code of map1: "+map1.hashCode());

        // equals(Object): Compare two maps
        Map<String,Integer> map2 = new HashMap<>();
        map2.put("key1", 11);
        map2.put("key3", 3);
        System.out.println("map1 equals map2? "+map1.equals(map2));

        // putAll(Map): Copy all entries from another map
        Map<String,Integer> map3 = new HashMap<>();
        map3.put("a", 100);
        map3.put("b", 200);
        map1.putAll(map3);
        System.out.println("After putAll from map3: "+map1);

        // clear(): Remove all entries
        map1.clear();
        System.out.println("After clear, map1: "+map1);
        System.out.println("----------------------------------------------------");

        // LinkedHashMap example: maintains insertion order
        LinkedHashMap<String, Integer> linkedMap = new java.util.LinkedHashMap<>();
        linkedMap.put("one", 1);
        linkedMap.put("two", 2);
        linkedMap.put("three", 3);
        System.out.println("LinkedHashMap elements (insertion order): " + linkedMap);
        linkedMap.remove("two");
        System.out.println("After removing 'two': " + linkedMap);
        linkedMap.put("four", 4);
        System.out.println("After adding 'four': " + linkedMap);
        System.out.println("Keys: " + linkedMap.keySet());
        System.out.println("Values: " + linkedMap.values());
        System.out.println("Entries: " + linkedMap.entrySet());
        System.out.println("----------------------------------------------------");

        // Hashtable example: synchronized, does not allow null keys/values
        Hashtable<String, Integer> hashtable = new java.util.Hashtable<>();
        hashtable.put("alpha", 100);
        hashtable.put("beta", 200);
        hashtable.put("gamma", 300);
        System.out.println("Hashtable elements: " + hashtable);
        hashtable.remove("beta");
        System.out.println("After removing 'beta': " + hashtable);
        hashtable.put("delta", 400);
        System.out.println("After adding 'delta': " + hashtable);
        System.out.println("Keys: " + hashtable.keySet());
        System.out.println("Values: " + hashtable.values());
        System.out.println("Entries: " + hashtable.entrySet());
        System.out.println("Contains key 'alpha': " + hashtable.containsKey("alpha"));
        System.out.println("Contains value 300: " + hashtable.containsValue(300));
        System.out.println("Size: " + hashtable.size());
        System.out.println("Is empty? " + hashtable.isEmpty());
        System.out.println("----------------------------------------------------");

        // TreeMap example: keys are sorted in natural order
        TreeMap<String, Integer> treeMap = new java.util.TreeMap<>();
        treeMap.put("banana", 30);
        treeMap.put("apple", 10);
        treeMap.put("cherry", 20);
        System.out.println("TreeMap elements (sorted by key): " + treeMap);
        treeMap.remove("banana");
        System.out.println("After removing 'banana': " + treeMap);
        treeMap.put("date", 40);
        System.out.println("After adding 'date': " + treeMap);
        System.out.println("Keys: " + treeMap.keySet());
        System.out.println("Values: " + treeMap.values());
        System.out.println("Entries: " + treeMap.entrySet());
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("----------------------------------------------------");

        // TreeMap with custom Comparator (sort by key length, then alphabetically)
        TreeMap<String, Integer> lengthTreeMap = new TreeMap<>(new java.util.Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int lenCompare = Integer.compare(s1.length(), s2.length());
                return (lenCompare != 0) ? lenCompare : s1.compareTo(s2);
            }
        });
        lengthTreeMap.put("kiwi", 5);
        lengthTreeMap.put("banana", 30);
        lengthTreeMap.put("fig", 15);
        lengthTreeMap.put("apple", 10);
        lengthTreeMap.put("date", 40);
        lengthTreeMap.put("cherry", 20);
        System.out.println("TreeMap with custom comparator (by key length): " + lengthTreeMap);
        System.out.println("Keys (by length): " + lengthTreeMap.keySet());
        System.out.println("Values: " + lengthTreeMap.values());
        System.out.println("Entries: " + lengthTreeMap.entrySet());
        System.out.println("First key: " + lengthTreeMap.firstKey());
        System.out.println("Last key: " + lengthTreeMap.lastKey());
        System.out.println("----------------------------------------------------");
 
        
        /*
         * The **Map interface hierarchy** in Java is as follows:

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
         */
    }
}