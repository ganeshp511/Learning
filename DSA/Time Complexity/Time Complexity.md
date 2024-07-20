Time Complexity

- Relation between input size and running time (operations)

- Time complexity is the relationship between the increase in input size and the corresponding number of operations performed by an algorithm.

- Its algorithm's performance scaling with increasing input size.
- add TC graph here

- The Big O chart above shows that O(1), which stands for constant time complexity, is the best. This implies that your algorithm processes only one statement without any iteration. Then there's O(log n), which is good, and others like it, as shown below:

O(1) - Excellent/Best
O(log n) - Good
O(n) - Fair
O(n log n) - Bad
O(n^2), O(2^n) and O(n!) - Horrible/Worst
You now understand the various time complexities, and you can recognize the best, good, and fair ones, as well as the bad and worst ones (always avoid the bad and worst time complexity).

When your calculation is not dependent on the input size, it is a constant time complexity (O(1)).
When the input size is reduced by half, maybe when iterating, handling recursion, or whatsoever, it is a logarithmic time complexity (O(log n)).
When you have a single loop within your algorithm, it is linear time complexity (O(n)).
When you have nested loops within your algorithm, meaning a loop in a loop, it is quadratic time complexity (O(n^2)).
When the growth rate doubles with each addition to the input, it is exponential time complexity (O2^n).

- In analyzing algorithms, we consider three types of time complexity:

1. BigO-worst case scenario:
Definition: The maximum amount of time an algorithm takes to complete for the worst possible input of size n.

Importance: This is crucial for understanding the upper bound of an algorithm's performance and ensuring that it will complete within a reasonable time under any circumstances.

Example: For a linear search algorithm, the worst-case time complexity is O(n) because in the worst case, it has to traverse the entire array to find the target element or determine that it is not present.

2. Theta-average case scenario (Θ):
Definition: Average amount of time algorithm takes to complete for n input size.

Importance: This provides a more realistic measure of an algorithm's performance for typical input cases, offering a balanced perspective between the worst-case and best-case scenarios.

Example: For a linear search algorithm, the average-case time complexity is O(n) because, on average, the target element will be found somewhere in the middle of the array.

3. Omega- Best Case Scenario (Ω):
Definition: The minimum amount of time an algorithm takes to complete for the best possible input of size n

Importance: This gives an optimistic view of the algorithm's performance and shows the potential efficiency under ideal conditions.

Example: For a linear search algorithm, the best-case time complexity is O(1) because in the best case, the target element is the first element of the array.

Example: Analyzing Binary Search
For the binary search algorithm, assuming the input array is sorted:

1. Worst-Case Time Complexity: 
𝑂(log𝑛)

Occurs when the element is not present, and the algorithm must divide the array until the end.

2. Best-Case Time Complexity: 
𝑂(1)

Occurs when the target element is at the middle of the array in the first check.

3. Average-Case Time Complexity: 
𝑂(log𝑛)

On average, the binary search will perform logarithmic operations due to halving the input size each iteration.
By considering these three types of time complexity with their respective asymptotic notations, we can gain a comprehensive understanding of an algorithm's performance across different scenarios.

Big O Time Complexity Examples
Constant Time: O(1)
When your algorithm is not dependent on the input size n, it is said to have a constant time complexity with order O(1). This means that the run time will always be the same regardless of the input size.

For example, if an algorithm is to return the first element of an array. Even if the array has 1 million elements, the time complexity will be constant if you use this approach:

The function above will require only one execution step, meaning the function is in constant time with time complexity O(1).

But as I said earlier, there are various ways to achieve a solution in programming. Another programmer might decide to first loop through the array before returning the first element:

This is just an example – likely nobody would do this. But if there is a loop, this is no longer constant time but now linear time with the time complexity O(n).

Linear Time: O(n)
You get linear time complexity when the running time of an algorithm increases linearly with the size of the input. This means that when a function has an iteration that iterates over an input size of n, it is said to have a time complexity of order O(n).

For example, if an algorithm is to return the factorial of any inputted number. This means if you input 5 then you are to loop through and multiply 1 by 2 by 3 by 4 and by 5 and then output 120:


console.log(calcFactorial(5)); // 120
The fact that the runtime depends on the input size means that the time complexity is linear with the order O(n).

Logarithm Time: O(log n)
This is similar to linear time complexity, except that the runtime does not depend on the input size but rather on half the input size. When the input size decreases on each iteration or step, an algorithm is said to have logarithmic time complexity.

This method is the second best because your program runs for half the input size rather than the full size. After all, the input size decreases with each iteration.

A great example is binary search functions, which divide your sorted array based on the target value.

For example, suppose you use a binary search algorithm to find the index of a given element in an array:

In the code above, since it is a binary search, you first get the middle index of your array, compare it to the target value, and return the middle index if it is equal. Otherwise, you must check if the target value is greater or less than the middle value to adjust the first and last index, reducing the input size by half.

Because for every iteration the input size reduces by half, the time complexity is logarithmic with the order O(log n).

Quadratic Time: O(n^2)
When you perform nested iteration, meaning having a loop in a loop, the time complexity is quadratic, which is horrible.

A perfect way to explain this would be if you have an array with n items. The outer loop will run n times, and the inner loop will run n times for each iteration of the outer loop, which will give total n^2 prints. If the array has ten items, ten will print 100 times (10^2).

Here is an example by Jared Nielsen, where you compare each element in an array to output the index when two elements are similar:


const fruit = ["🍓", "🍐", "🍊", "🍌", "🍍", "🍑", "🍎", "🍈", "🍊", "🍇"];
console.log(matchElements(fruit)); // "Match found at 2 and 8"
In the example above, there is a nested loop, meaning that the time complexity is quadratic with the order O(n^2).

Exponential Time: O(2^n)
You get exponential time complexity when the growth rate doubles with each addition to the input (n), often iterating through all subsets of the input elements. Any time an input unit increases by 1, the number of operations executed is doubled.

The recursive Fibonacci sequence is a good example. Assume you're given a number and want to find the nth element of the Fibonacci sequence.

The Fibonacci sequence is a mathematical sequence in which each number is the sum of the two preceding numbers, where 0 and 1 are the first two numbers. The third number in the sequence is 1, the fourth is 2, the fifth is 3, and so on... (0, 1, 1, 2, 3, 5, 8, 13, …).

This means that if you pass in 6, then the 6th element in the Fibonacci sequence would be 8:
