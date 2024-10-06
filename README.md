# node
Skip List Implementation in Java
This repository contains an implementation of a Skip List data structure in Java. A Skip List is a probabilistic data structure that allows efficient search, insertion, and deletion operations in a sorted list. It achieves performance comparable to balanced trees, but with a simpler and more flexible design.

Features
Search: Efficiently search for a node with a specific value in O(log n) expected time.
Insert: Insert new elements into the skip list and ensure they are placed at the correct position across multiple layers.
Delete: Remove an element from the skip list across all levels.
Multiple Levels: The skip list maintains multiple layers of linked lists, where each higher level allows faster access to elements by skipping intermediate nodes.
Random Level Creation: The skip list randomly assigns new elements to higher levels, ensuring balanced layers over time.
Class Structure
Node
A class representing a node in the skip list. Each node stores:

val: The value of the node.
Pointers to its neighbors (left, right) and corresponding nodes in the upper and lower layers (up, down).
SkipList
The main class that manages the skip list. Key methods include:

search(int val): Searches for the specified value in the skip list.
insert(int data): Inserts a new value into the skip list.
delete(int data): Deletes a value from the skip list.
print(): Prints all the elements at the base level.
printAllLevel(): Prints all elements across every level in the skip list.
printcertainlevel(int val): Prints the first val elements on each level.
Skip List Methods
Search: Efficiently finds the node with the given value by traversing horizontally and vertically across levels.
Insert: Adds a new value to the list, with a chance of appearing on higher levels, determined by a random "coin flip."
Delete: Removes a node from the list, ensuring it's unlinked from all levels.
