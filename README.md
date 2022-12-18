# CompetitiveProgrammingTutorial
It contains different topics on Competitive programming, I would try to code in JAVA, C, C++ and Python.
#
## 1. **Abridged Problem Description**

Let $(x, y)$ be the coordinates of a student’s house on a 2D plane. There are 2N students
and we want to pair them into N groups. Let di be the distance between the houses
of 2 students in group i. Form N groups such that cost = $\sum_{i=1}^N d_i$ is minimized.
Output the minimum cost. Constraints: $1 ≤ N ≤ 8$ and $0 ≤ x, y ≤ 1000$.

### **Sample input:**

$N = 2;$ Coordinates of the $2N = 4$ houses are $\{1, 1\}$, $\{8, 6\}$, $\{6, 8\}$, and $\{1, 3\}$.

### **Sample output:**
$cost = 4.83$

Now ask yourself: Which of the following best describes you?

- Uncompetitive programmer A (a.k.a. the blurry one):
  - Step 1: Reads the problem and becomes confused. (This problem is new for him).
  - Step 2: Tries to code something: Reading the non-trivial input and output.
  - Step 3: Realizes that all his attempts are not Accepted (AC):
  - Greedy (Section 3.4): Repeatedly pairing the two remaining students with the
shortest separating distances gives the Wrong Answer (WA).
Na¨ıve Complete Search: Using recursive backtracking (Section 3.2) and trying
all possible pairings yields Time Limit Exceeded (TLE).
- Uncompetitive programmer B (Give up):
  - Step 1: Reads the problem and realizes that he has seen this problem before.
But also remembers that he has not learned how to solve this kind of problem...
He is not aware of the Dynamic Programming (DP) solution (Section 3.5)...
  - Step 2: Skips the problem and reads another problem in the problem set.
- (Still) Uncompetitive programmer C (Slow):
  - Step 1: Reads the problem and realizes that it is a hard problem: ‘minimum
weight perfect matching on a small general weighted graph’. However,
since the input size is small, this problem is solvable using DP. The DP state is
a bitmask that describes a matching status, and matching unmatched students
i and j will turn on two bits i and j in the bitmask (Section 8.3.1).
  - Step 2: Codes I/O routine, writes recursive top-down DP, tests, debugs >.<...
  - Step 3: After 3 hours, his solution obtains AC (passed all the secret test data).
- Competitive programmer D:
Completes all the steps taken by uncompetitive programmer C in ≤ 30 minutes.
- Very competitive programmer E:
A very competitive programmer (e.g. the red ‘target’ coders in TopCoder [32])
would solve this ‘well known’ problem ≤ 15 minutes...

**Solution 1.1**: Greedy solution would give a WA, counter example: $\{(5,7), (7,7), (7,6), (5, 2)\}$ <br>
**Solution 1.2**: Backtracking solution would give TLE because the complexity would be $O(2^n)$

## 1.1 Tip 1: Type Code Faster!
Try this typing test at http://www.typingtest.com
Try to keep above 50, if it is much less than 50 then try to take this seriously. 
## 1.2 Tip 2: Quickly Identify Problem Types!

* Ad Hoc
* Complete Search
* Divide and Conquer
* Greedy (usually the original ones)
*  Dynamic Programming (usually the original ones)
*  Graph
*  Mathematics
*  String Processing
*  Computational Geometry
*  Some Harder/Rare problems

By no means this classification is complete. Some techniques,
e.g. ‘sorting’, are not classified here as they are ‘trivial’ and usually used only as a ‘sub-routine’ in a bigger problem.
## 1.3 Tip 3: Do Algorithm Analysis
Nowadays computers are fast, they can do $10^8$ operations a second. So we need to use this information to come up with the optimal complexity of the code.
