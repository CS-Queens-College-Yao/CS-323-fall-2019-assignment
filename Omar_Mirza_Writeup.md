
# Writeup
*Omar Mirza Fall 2019*
## 1. Running Trials and Tribulations

<b>(a)</b> We continually split the problems into two subproblems (safe trial or risky trial) in the code these problems are represented as 

    runTrialsRecur(i-1, days-1) // Risky trial
    runTrialsRecur(possibleSpeeds-i, days) // Safe trial

<b>(c)</b> 

    f(6,2)
       ├───f(5,1)
       │   └───5
       └───f(5,2)
           ├───f(4,1)
           │   └───4
           └───f(4,2)
                 ├───f(3,1)
                 │   └───3
                 └───f(3,2)
                     ├───f(2,1)
                     │   └───2
                     └───f(2,2)
                           ├───f(2,1)
                           │   └───2
                           └───f(1,2)
                               └───1




<b>(d)</b> 6*2 = 12 subproblems

<b>(e)</b> N*M = NM subproblems

<b>(f)</b> We save our previous answers in a 2d array, then at the beginning of the next problem we check the 2darray if we already solved it.

## 2. Holiday Special - Putting Shifts Together

<b>(a)</b> The optimal solution to our main problem is composed of optimal solutions of smaller subproblems such as most consecutive chef for a smaller number of steps.

<b>(b)</b> Find the chef with the most consecutive chefs, choose them, repeat but for the remaining steps.

<b>(d)</b> 

<b>(e)</b> 

## 3. League of Patience

<b>(a)</b> For this problem I would adapt the Bellman-Ford Algorithm 

<b>(b)</b> The time complexity for Bellman-Ford is O(VE) 

<b>(c)</b> The genericShortest method is implementing dijkstra's algorithm 

<b>(d)</b> We need to change the code such that the durations are updated dynamically. We also need to increment the time according to how long it takes to wait and reach each node.

<b>(e)</b> Dijkstra's algorithm is O(V+E) 