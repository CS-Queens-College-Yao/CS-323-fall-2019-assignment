
# Writeup
*Omar Mirza Fall 2019*
## 1. Running Trials and Tribulations

<b>(a)</b> 

<b>(c)</b> 

<b>(d)</b> 

<b>(e)</b> 

<b>(f)</b> 

## 2. Holiday Special - Putting Shifts Together

<b>(a)</b> The optimal solution to our main problem is composed of optimal solutions of smaller subproblems such as most consecutive chef for a smaller number of steps.

<b>(b)</b> Find the chef with the most consecutive chefs, choose them, repeat but for the remaining steps.

<b>(d)</b>

## 3. League of Patience

<b>(a)</b> For this problem I would adapt the Bellman-Ford Algorithm 

<b>(b)</b> The time complexity for Bellman-Ford is O(VE) 

<b>(c)</b> The genericShortest method is implementing dijkstra's algorithm 

<b>(d)</b> We need to change the code such that the durations are updated dynamically. We also need to increment the time according to how long it takes to wait and reach each node.

<b>(e)</b> Dijkstra's algorithm is O(V+E) 