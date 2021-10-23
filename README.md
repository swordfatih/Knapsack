# Knapsack
This project is the implementation of three different algorithms in Java meant to solve the Knapsack problem in an optimal way.

Explanation of the algorithm, from Wikipedia, the free encyclopedia : 
> The knapsack problem is a problem in combinatorial optimization: Given a set of items, each with a weight and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible. It derives its name from the problem faced by someone who is constrained by a fixed-size knapsack and must fill it with the most valuable items.

# Structure

- application
  - Main
- sacados
  - SacADos (class representing a backpack)
  - Objet (class representing an item)
  - Methode (interface representing a solving method)
- methodes
  - Gloutonne
  - Dynamique
  - PSE

# Methods
### Greedy algorithm (Gloutonne)
This algorithm sorts, in a descending order, all the items in the knapsack by the ratio of their value by their price. Then it fills the backpack until it is full.
This algorithm often doesn't give an exact solution.

### Dynamic programming (Dynamique)
This algorithm consists of creating an array of **y** columns where **y** represents the maximum weight of the knapsack and each **x**-th line represents the combination of the first x objects. Except for the first line, we fill the table by either the best value we already found for the first (x-1)-th item or we put the x-th item in the bag and add the best value we already found for the remaining weight. Finding back the combination of items for a given value just consists of running through the matrix diagonally. 

### Branch and bound (PSE)
This algorithm creates a binary tree with, for each bound, giving the next item to the left bound and nothing to the right bound. This algorithm is very slow if taken as-is but very powerful when some optimisations are applied such as not continuing useless branchs whenever they can't give us a better value than the one already found or if they are going to exceed the maximum weight.
