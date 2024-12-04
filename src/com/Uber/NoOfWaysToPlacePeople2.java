class Solution {
    public int numberOfPairs(int[][] points) {
        
        Arrays.sort(points, (a,b) -> {
            if(a[0] == b[0]){ //otherwise If two points have the same x, you sort by y in descending order to ensure Takina's y(height) is less than or equal to Chisato's y(height). 
                return b[1]-a[1]; ///desc order sort of height - y
            }
            return a[0]-b[0]; //sort in increasing order of x as Takina's/Bob's x should be greater than Chisato's/Alice's x position
        });

       int count = 0;
        for(int i = 0; i < points.length; i++) {

            int x = points[i][0];  // Chisato/Alice's x-coordinate
            int y = points[i][1];  // Chisato/Alice's y-coordinate
            int prev = Integer.MIN_VALUE;  // Track the largest y of valid person 2 (Takina/Bob)

            for(int j = i + 1; j < points.length; j++) {  // Iterate over remaining points
                int x1 = points[j][0];  // Takina/Bob's x-coordinate
                int y1 = points[j][1];  // Takina/Bob's y-coordinate

                if(y1 <= y && y1 > prev) {  // Ensure Takina's y is valid and greater than previous valid y. y1 > prev is to check overlap conditions with other people
                    prev = y1;
                    count++;
                }
                /**
                Q: Why we compare y co-ords only in the above if consition and not x?
    Ans: Sorting points by x ensures that for any point p, the subsequent points in the list have a higher or equal x value, so we only need to check the y values for validity.
                 */
            }
        }
    return count;

    }
}
//https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/solutions/4722529/sorting-java/?envType=company&envId=uber&favoriteSlug=uber-three-months
/**
Explanation:
Sorting:

First, you sort the points by x in ascending order (so that Takina's/Bob's x is always greater than or equal to Chisato's/Alice's x).
If two points have the same x, you sort by y in descending order to ensure Takina's y(height) is less than or equal to Chisato's y(height).
Counting Valid Pairs:

The outer loop iterates over each point points[i] (representing Chisato/Alice's position).
The inner loop iterates over all points points[j] (representing Takina/Bob's position) that come after the current i in the sorted array.
The condition y1 <= y && y1 > prev ensures that:
Takina's y1 is less than or equal to Chisato's y.
The y1 is also greater than the previous valid y, ensuring no overlap of rectangles formed by the two points.
 */
 /**
 Expl 2-
 Approach
Question seems to be hard, but in reality it is medium level question. Just sort the array based x coordinate, if x coordinates are same sort based in descinding order based on y coordinate.
Make a two for loops, for each point we check in it's right.
If two coordinates no other coordinates should not be in their rectangle space,
Consider we are finding no of lower right coordinates for upper left coordinate(a), If we find lower right coordinate(b), the next lower right coordinate(c) height(y) must be greater than b's height and less than or equal a's height.
  */
