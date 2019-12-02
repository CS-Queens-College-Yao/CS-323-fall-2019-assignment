# Design & Analysis of Algorithms Assignment (Due Nov 27 11:59PM)

There are three problems overall. First, instructions. Please read carefully:
### Coding and Git
* You can work together on this project in groups up to 4 people. If you are in a group, I should see code commits in the final pull request from each member. I recommend one member forking the repository, and everyone making branches off one member's forked repository and each person merging their work into the master gradually. I encourage this option because it will offer an opportunity to learn more about Git/Github collaboration and practice the material with your peers.
* `git fork` this repository to your own Github account. If you are in a group, only one person should do this.
* `git clone` the forked repository into your local system (everybody does this)
* Work on the java files in your preferred editor, but first read through them! The only changes you introduce should be in the java files where indicated and ONE write-up file. There should not be any extra project files like .class files or any editor files. The file changes in the pull request should be nice and clear, and should not include any of the original assignment code.
* In the java files, make sure your name is commented at the top of the java files as a co-author if you have contributed to it.
* your code should have variables namedInThisStyle (snakecase), it should be clearly written and understandable, and it should have relevant comments where necessary. Please auto-indent on just your code before submission. Make sure your formatting and spacing is on-point, and make sure you don't touch the original formatting on the code given so far, otherwise it will make the diff messy in the pull request.
* Put comments in your code adequately describing what is happening
* Please submit something that COMPILES. If you know your code does not compile as it is, please comment out problematic lines and print to the output "DOES NOT COMPILE - SEE CODE". I will visit your code, so make sure you are commenting in your code adequately to explain your work.
* `git add [filename].java` and `git add YourName_DP_Writeup.pdf`, `git commit` appropriately (remember to write good commit messages, especially while you work together, and package it into commits that make sense in case you need to trace your steps or rollback), `git push`

### Writeup
* For each submission whether it is an individual or group, there should be one file that includes responses to the non-coding sections of all the problems.
* This writeup file will include short responses, proofs, and drawings to the problems. Please put everything into <b>*ONE* pdf file titled `Your_Full_Name_Possibly_Other_Full_Names_Writeup.pdf`</b>. This must be a PDF, NOT any other file format, and the naming must match that format exactly.
* Please type wherever you can, and present it neatly.
* Clearly label which question you are answering, and always start a new problem on another page.
* The last page of the write-up should be a summary of (1) how the work was divided up between individuals/who did what in both coding and write-up and (2) the experience of working together.

### Submission
* From your forked repository, create a pull request. Make sure the compare says the base is CS-Queens-College-Yao:master and the head is your repository:master.
* Please make your pull request look nice! Fill in your pull request with an appropriate title `Assignment Submission: Name 1 - Name 2 - Name 3`. In the description section, list out all the names of the group members and make sure to note wherever you struggled.
* Lastly, please do not look up or recycle solutions. It has been painfully obvious in the past when students have done so, and there will be consequences.
* Submit your PR link via this Google form: bit.ly/323-assignment-submit

## 1. Running Trials and Tribulations

You are developing a training program for recovering but injury-prone athletes that will allow them to figure on what pace they should do their race training at. The athlete tells you they have M days to prepare, and over those days they want to figure out the fastest speed out of a range of N different speeds (we'll index them as 1st, 2nd, 3rd, ... to the nth) they can train for the race at without injuring themselves.
Assume that you are always given a reasonable N and M and there is always enough time to try out all N of those speeds over the course of M days (N <= M).

 If the athlete only had one day, then you would recommend that they start at the 1st speed, and increment 1 by 1 up to the point at which they get injured. It would take you minimum N test runs. However, if you had more than one day, you'd want to minimize the number of test runs it takes to get to that breaking point because the athlete wants to ideally spend the rest of the time training at that set speed.

* You will recommend that the athlete always keeps their training conditions about the same, so the only difference should be the speed.
* If the athlete is minorly injured during a practice run on a particular speed, they should set the rest of the day to recover and they can only start running again the next day. And they know they definitely cannot run at any faster speed than the one they got injured out. For example, if the athlete tries out the 6th speed and does get injured, the next set of possibilities to test would be 1st to 4th speed and losing 1 day.
* If the athlete is not injured during a practice run on a particular speed, they can continue to run that day. And the athlete can maintain at least that speed during the actual race. For example, if the athlete tries the 5th speed and doesn't hurt any muscles, the problem that remains is testing 6th speed up to the nth speed and the same number of days remaining.
* Injuries are possible at all possible speeds.

#### Given: M days, N paces
#### Find: What’s the <b>minimum number of speedtest runs the athlete needs to go on </b> to figure out the fastest speed they can train for a race at without getting injured?

#### (a) Describe the optimal substructure/recurrence that would lead to a recursive solution
#### (b) Code your recursive solution under runTrialsRecur(int possibleSpeeds, int days). If your recursive function runs forever, in order for grading to happen quickly please comment out the code progress you made instead.
#### (c) Draw recurrence tree for given (# speeds = 6, # days = 2)
#### (d) How many distinct subproblems do you end up with given 6 speeds and 2 days?
#### (e) How many distinct subproblems for N speeds and M days?
#### (f) Describe how you would memoize runTrialsRecur.
#### (g) Code a dynamic programming bottom-up solution runTrialsBottomUp(int possibleSpeeds, int days)
#### (h) Extra Credit: 15 pts, write a function that will also print which speeds the athlete should test during those minimum number of speedtest runs, and have it print to the output

#### Turn in: In your write-up file, parts: a, c, d, e, f with clear and careful explanations! Coding parts b, g, h (optional) in the file RunningTrials.java

## 2. Holiday Special - Putting Shifts Together

You manage a community kitchen and for the upcoming holiday there are a few
recipes, each with a specified n complicated steps that require special skills. You have a volunteer cooking staff of m people, and each person is able to do a specific subset of the steps required. A recipe has n steps, and each of the m volunteers tells you which steps they can or cannot do based on skill. From experience, you know things run most smoothly when you minimize the number of switches between people. In other words, you would prefer to arrange fewer but longer shifts wherever possible.
You want to design a system where if there is a recipe and you know who can do which steps,
you will automatically make the best schedule that requires least switching between
cooks.

Example if your recipe has 8 steps <1, 2, 3, 4, 5, 6, 7, 8>
* Cook 1: <2, 3, 4, 5, 6>
* Cook 2: <5, 7, 8>
* Cook 3: <1, 3, 4, 8>
* Cook 4: <1, 5, 7, 8><br>
Your optimal solution of 2 switches could be any one of these: <br>
  * Cook 1 does <2, 3, 4, 5, 6>, Cook 2 does no steps, Cook 3 does no steps, Cook 4 does <1, 7, 8> (Cook 4 does step 1, Cook 1 does up to 6, Cook 4 picks up again to do 7 and 8) --> 2 switches<br>

  * Cook 1 does <2, 3, 4, 5, 6>, Cook 2 does no steps, Cook 3 does <1>, Cook 4 does <7, 8> (Cook 3 does step 1, Cook 1 does up to 6, Cook 4 does 7 and 8) --> 2 switches <br>

  * Cook 1 does <2, 3, 4, 5, 6>, Cook 2 does <7, 8>, Cook 3 does <1>, Cook 4 does nothing (Cook 3 does step 1, Cook 1 does 2-6, Cook 2 does 7-8)  --> 2 switches <br>

  * Cook 1 does <2, 3, 4, 5, 6>, Cook 2 does <7, 8>, Cook 3 does nothing, Cook 4 <1>
  (Cook 4 does step 1, Cook 1 does up to 6, Cook 2 does 7-8)--> 2 switches <br>


For example, if your recipe has <1, 2, 3, 4, 5, 6> steps
* Cook 1: <1, 2, 3, 4, 5>
* Cook 2: <1, 2, 3, 4>
* Cook 3: <1, 2, 4, 5, 6> <br>
Your optimal solution could be:<br>
* Cook 1 or Cook 2 does <1, 2, 3>, Cook 3 does <4, 5, 6>. Only 1 switch
* Cook 1 or Cook 2 does <1, 2, 3, 4>, Cook 3 does <5, 6>. Only 1 switch
* Cook 1 does <1, 2, 3, 4, 5>, Cook 2 does nothing,, Cook 3 does <6>. Only 1 switch


#### Given: n number of steps, m number of Cooks that give you a list of steps (sorted) they can participate in. Assume there's a lookup table where you can find if Cook X signed up for step Y in O(1), so no need to factor that into your runtime.
#### Find: An optimal way to schedule Cooks to steps such that there is the least amount of switching as possible.

##### (a) Describe the optimal substructure of this problem.
#### (b) Describe the greedy algorithm in plain words that could find an optimal way to schedule the volunteers for one recipe.
#### (c) Code your greedy algorithm in the file "HolidaySpecial.java" under the "makeShifts" method where it says "Your code here". Read through the documentation for that method. Note that I've already set up everything necessary for the provided test cases. Do not touch the other methods except possibly adding another test case to the main method. Run the code without your implementation first and you should see this:
[![holiday-special-output.png](https://i.postimg.cc/CK3bWQ04/holiday-special-output.png)](https://postimg.cc/JyQyXKGy)
<br> With your implementation, your final output should look something like this:

[![holiday-special-after-output.png](https://i.postimg.cc/NGHQsPcH/holiday-special-after-output.png)](https://postimg.cc/7bwFX9bH)

#### (d) What is the runtime complexity of your greedy algorithm? Again, you don't need to factor in the setup of the signup table, just your scheduling algorithm.
#### (e) In your write-up file, based on your answer to part b, give a full proof that your greedy algorithm returns an optimal solution.


#### Turn in: In your write-up file you should have answers to parts: a, b, d with clear and careful explanations! Coding part c in the file HolidaySpecial.java, I will be looking at the final output

## 3. League of Patience
You make a living helping players (your clients) of an online game reach a specific destination in the game. The game has a map with various routes between different game locations that represent a player's progress, and each location has quests that could lead you to other locations. Since you are a professional, you know exactly how long the every single quest will take. At each location, you can choose a specific quest that will allow you to advance to the adjacent location you want. However, most of the time you will not be able to immediately play a quest the minute you get to the location. The exact time of the quests are released by the game devs the day of, so they are variable each day. There's an API endpoint by game developers that
tells you, given a time and the specific quest between two locations, what time the next quest starts.

You conceptualize the game map like a connected graph G=(V, E) where the game locations are vertices and there are directed edges between adjacent locations, and each edge (u, v) would have the following information:

* duration(e): the time, in minutes, it takes for you to win the battle or finish
the quest that transports you from u to v
* next(e): what time the next quest that allows you to advance from u to v takes
place

You want to write a program that will tell you which exact route of quests you want
to take from a starting time, client's starting location S, to the location they want you to advance them to: T.
You don't care how many quests you have to complete: some quests are shorter, some are longer. You just want the fastest overall time between game location S and T at a particular start time. Note that you need to be strategic about length of time between when you finish the quest and the time you can start the next one, so you must account for that in your algorithm.

<b>Given</b>: starting time X, starting location S, destination T, adjacency matrix of edges, where the a[u][v] is how long the quest takes to play between u and v, and a function next(time, u, v) that will hit the game's API to request the closest next starting time for the quest to advance from u to v.

<b>Goal</b>: The fastest combined play time and wait time to get from starting location S to destination T.

#### (a) Describe an algorithm solution to this problem. Feel free to talk about how you would adapt an algorithm we covered in class.
#### (b) What is the complexity of your proposed solution in (a)?
#### (c) See the file LeagueOfPatience.java, the method "genericShortest". Note you can run the LeagueOfPatience.java file and the method will output the solution from that method. Which algorithm is this genericShortest method implementing?
#### (d) In the file LeagueOfPatience.java, how would you use the existing code to help you implement your algorithm? The existing code only handles one piece of data per edge, so describe some modifications. Note the helper methods available to you, including one that simulates the game's API that returns the next quest time.
#### (e) What's the current complexity of "genericShortest" given V vertices and E edges? How would you make the "genericShortest" implementation faster? Describe any algorithm changes or data structure changes. What's the complexity of the optimal implementation?
#### (f) Code! In the file LeagueOfPatience.java, in the method "myFastestPlay", implement the algorithm you described in part (a) using your answers to (d). Don't need to implement the optimal data structure.
#### (g) Extra credit (15 points): Print out not just the fastest time to get to a destination, but also the path to get there.

#### Turn in: In your write-up file you should have answers to parts: a-e! Coding part f and (optional) g in the file LeagueOfPatience.java
