package com.Uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Maze Robot Distance
 * https://leetcode.com/company/uber/discuss/4718477/Uber-or-Phone-Screen-or-Amsterdam
 * https://leetcode.com/discuss/interview-experience/5253429/Uber-or-SDE-2-or-Phone-Screen-or-SF-USA-or-2024/
 * You are given a 2D maze represented by a matrix maze consisting of the following characters:
 *
 *
 * 'O' represents the position of a robot.
 * 'X' represents a blocker (obstacle).
 * 'E' represents an empty cell.
 *
 *
 * You are also given an integer array distance of length 4, where distance[0], distance[1], distance[2], and distance[3] represent the desired distance from the robot to the nearest blocker in the left, right, top, and bottom directions, respectively.
 * Return an array of coordinates (row, column) representing the positions of the robots that have the provided distance to the nearest blocker in all four directions.
 * Example:
 * maze = [
 *     ['O', 'E', 'E'],
 *     ['X', 'E', 'E'],
 *     ['E', 'E', 'O']
 * ], distance = [1, 3, 1, 1]
 * Output: [[0, 0]]
 * Explanation: The robot at (0, 0) has a distance of 1 to the blocker on the right, 1 to the blocker on the top, and 1 to the blocker on the bottom. The robot at (2, 2) has a distance of 3 to the blocker on the left, but the required distance is 1, so it is not included in the answer.
 */

public class MazeRobotDistance {

    public static void main(String[] args) {

        char[][] maze = new char[][]{
                {'O', 'E', 'X'},
                {'X', 'E', 'E'},
                {'E', 'E', 'O'}
        };
        int[] distance = new int[]{1, 2, 1, 1};
        MazeRobotDistance mazeRobotDistance = new MazeRobotDistance();
        List<int[]> robots = mazeRobotDistance.solve(maze,distance);
        for(int i=0; i<robots.size(); i++){
            System.out.println(Arrays.toString(robots.get(i)));
        }
    }

    public int[] calcDistance(char[][] maze, int i, int j){
        int[] directions = new int[4];

        //traverse left
        int leftDist=0;
        int col=j;
        while(col >=0 && maze[i][col] != 'X'){
            leftDist++;
            col--;
        }
        directions[0] = leftDist;

        //traverse right
        int rightDist=0;
        int rcol=j;
        while(rcol < maze[0].length && maze[i][rcol] != 'X'){
                rightDist++;
                rcol++;
        }
        directions[1] = rightDist;

        //traverse up
        int upDist=0;
        int row =i;
        while(row >=0 && maze[row][j] != 'X'){
            upDist++;
            row--;
        }
        directions[2] = upDist;

        //traverse bottom
        int bottomDist=0;
        int brow=i;
        while(brow < maze.length && maze[brow][j] != 'X'){
            bottomDist++;
            brow++;
        }
        directions[3] = bottomDist;
        return directions;
    }

    public List<int[]> solve(char[][] maze, int[] distances){
        List<int[]> robots = new ArrayList<>();

        for(int i=0; i<maze.length; i++){
            for(int j=0; j<maze[0].length;j++){

                if(maze[i][j] == 'O'){

                    int[] distFound = calcDistance(maze,i,j);
                    if(distances[0] == distFound[0] && distances[1] == distFound[1] && distances[2] == distFound[2] && distances[3] == distFound[3]){
                        robots.add(new int[]{i,j});
                    }
                }
            }
        }
        return robots;
    }
}
