# 4. Strings and Arrays

## String Immutability
- In Java, `String` objects are immutable. Once created, their values cannot be changed.
- Operations like concatenation or replace create new `String` objects instead of modifying the original.
- Example:
  ```java
  String s1 = "Hello";
  s1.concat(" World"); // s1 remains "Hello"
  s1 = s1.concat(" World"); // s1 is now "Hello World"
  ```
- Immutability provides security, thread-safety, and efficient string pooling.

## String Methods, StringBuilder vs StringBuffer
- Common `String` methods: `length()`, `charAt()`, `substring()`, `indexOf()`, `toLowerCase()`, `toUpperCase()`, `replace()`, `split()`, `trim()`
- `StringBuilder`: Mutable, fast for string manipulation, not thread-safe.
- `StringBuffer`: Mutable, thread-safe (synchronized), slightly slower.
- Example:
  ```java
  StringBuilder sb = new StringBuilder("Hello");
  sb.append(" World");
  System.out.println(sb); // Output: Hello World
  ```

## Arrays (1D, 2D, Jagged)
- 1D Array: Simple list of elements.
  ```java
  int[] arr = {1, 2, 3, 4};
  ```
- 2D Array: Array of arrays (matrix).
  ```java
  int[][] matrix = {
      {1, 2, 3},
      {4, 5, 6}
  };
  ```
- Jagged Array: 2D array with rows of different lengths.
  ```java
  int[][] jagged = new int[2][];
  jagged[0] = new int[]{1, 2};
  jagged[1] = new int[]{3, 4, 5};
  ```

## Array Methods: sort, copy, fill
- `Arrays.sort(array)`: Sorts the array in ascending order.
- `Arrays.copyOf(array, newLength)`: Copies the array to a new array of specified length.
- `Arrays.fill(array, value)`: Fills the array with the specified value.
- Example:
  ```java
  int[] arr = {3, 1, 2};
  Arrays.sort(arr); // arr = [1, 2, 3]
  int[] copy = Arrays.copyOf(arr, 5); // copy = [1, 2, 3, 0, 0]
  Arrays.fill(copy, 7); // copy = [7, 7, 7, 7, 7]
  ```

## Common Array & String Interview Problems
- Reverse a string or array.
- Check if a string is a palindrome.
- Find the largest/smallest element in an array.
- Remove duplicates from an array.
- Find the first non-repeated character in a string.
- Rotate an array.
- Merge two sorted arrays.
- Find subarrays/substrings with a given sum or property.
