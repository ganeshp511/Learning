- Faster than linear search. Half of the remaining elements can be eliminated at a time. instead of eliminating them one by one.
- only works on sorted array

#Pseudocode:

- create fun with two parameters one is sorted array and other value
- create two pointers one at a start of array and another at end of array
- based on left and right pointers calculate middle pointer
- while middle!=value and start<=end loop:
    - if middle>value then move right pointer down
    - if middle is less than value then move left pointer
    - if value never found then return -1