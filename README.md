# Part B: Stacks and Queues

**The browsing history feature was a success.**
  
**Your manager said that the users are now demanding an additional feature where the Upzilla Browser will also show websites that the users have visited the most.**

`This project has the following function:`

**public void listTopVisitedSites(Queue<SiteStats> sites, int n)**

`sites is a queue that represents all the websites that the user has visited, as well the number of times that the user has visited each website`

`n represents the top n most visited sites that we want to retrieve from the queue`

`0 < n <= m, where m is the number of websites tracked by Queue<SiteStats> sites or the size of the queue`

`The function will print the top 5 sites and the no. of times they are visited. If multiple websites have been visited the same no. of times, then they should be ordered by recency (the last visited).`

`If the user has no browsing history, the function will print an empty array of type String`

`Note: The runtime and space complexity of your implementation should be O(
       N
       squared
       ) and O(1) respectively.`

**public static void updateCount(String url)**

`This method finds the given website in the queue and increments the visited count by 1, if the website is found in the queue. If the website is not  found, it adds a new node to the queue.`