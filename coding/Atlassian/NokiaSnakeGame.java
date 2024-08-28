package coding.Atlassian;
import java.util.*;

/**
 * - Snake game - Q: https://leetcode.com/discuss/interview-question/1645336/atlassian-onsite-backend-se-winter-2021
 *     - Snake game - Soln: https://wentao-shao.gitbook.io/leetcode/data-structure/353.design-snake-game
 *     - https://gist.github.com/abhishekhandacse/09365d5cf33fb0f28d015239c874005a
 *     - **Design a snake game - https://www.ambitionbox.com/interviews/atlassian-question/design-question-snake-game-lqczwhpf**
 * -
 *     - Q1: Use a 2D array to represent the game board
 *     - Keep track of the snake's position and direction
 *     - Handle user input to change the snake's direction
 *     - Update the snake's position and check for collisions with walls or itself
 *     - Add food to the board and handle eating it to grow the snake
 *     - Keep track of the score and display it to the player
 *     - Used proper data structure to store the pointers of the snake
 *     -
 *     - Used proper LLD and Machine coding conventions to determine snake is alive or dead after every step
 *     -
 *     - Dry run with few test cases at the end of the coding
 *     -
 *         - Asked interviewer for help when I needed
 *         - Q2:
 *         - Design a [snake game](https://leetcode.com/problems/design-snake-game/). There were few changes which were result of me asking clarifying question. e.g. if snake goes out of the boundary it should wrap in from the opposite side of the screen.I had to write unit tests to check the solution. The interviewer made a few incorrect assumptions/gave wrong hints which I corrected for him.
 *
 *             Follow up questions:
 *
 *         - Can this code be optimized - Yes, I chose a linked list to represent the snake, where as a Deque should have been better choice.
 *         - How would you take care of concurrent button presses, i.e if someone presses up and right at the same time - Synchronization and related keywords
 *     - link for above Q2 -> [https://leetcode.com/discuss/interview-experience/2495755/Atlassian-or-SDE-2-(P4)-or-Bengaluru-or-Aug-2022-Offer](https://leetcode.com/discuss/interview-experience/2495755/Atlassian-or-SDE-2-(P4)-or-Bengaluru-or-Aug-2022-Offer)
 *
 */

public class NokiaSnakeGame {

    public static void main(String[] args) {

        int[][] food = new int[][]{{1,0}, {1,2}};
        SnakeGame snakeGame = new SnakeGame(food, 5, 5);
        System.out.println(snakeGame.solve('U')); //-1
        System.out.println(snakeGame.solve('D')); // 1 food cell
        System.out.println(snakeGame.solve('R')); // 1
        System.out.println(snakeGame.solve('R')); // 2 food cell
    }
}

    class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class SnakeGame {
        LinkedList<Node> snake;
        int[][] food;
        int score;
        int height;
        int width;
        Set<Node> vis;

        public SnakeGame(int[][] food, int height, int width) {
            this.snake = new LinkedList<>(); //= new LinkedList<>();//imp. Do not have it as List<> =... only LinkedList on left side of =
            this.food = food;
            this.score = 0;
            this.height = height;
            this.width = width;
            this.vis = new HashSet<>();

            this.snake.add(new Node(0, 0)); //initialize snake at cell 0
            this.vis.add(new Node(0, 0));
        }


        public int solve(Character direction) {

            Node head = snake.peekFirst();
            assert head != null;
            Node next = new Node(head.x, head.y);

            if (direction.equals('U')) {
                next.x--;

            } else if (direction.equals('D')) {
                next.x++;

            } else if (direction.equals('L')) {
                next.y--;

            } else if (direction.equals('R')) {
                next.y++;
            }

            if (next.x < 0 || next.y >= height || next.y < 0 || next.x >= width) {
                return -1;
            }
            /**
             *  //Follow up question -  Wrap around logic
             *     if (next.x < 0) {
             *         next.x = height - 1;  // Move to the bottom of the grid
             *     } else if (next.x >= height) {
             *         next.x = 0;  // Move to the top of the grid
             *     }
             *     if (next.y < 0) {
             *         next.y = width - 1;  // Move to the right side of the grid
             *     } else if (next.y >= width) {
             *         next.y = 0;  // Move to the left side of the grid
             *     }
             */
            if (vis.contains(next)) {
                System.out.println("already visited cell");
                return -1;
            }

            // food - x1, y1
            if (score < food.length && next.x == food[score][0] && next.y == food[score][1]) {
                snake.addFirst(next);
                vis.add(next);
                score++;
            } else {
                snake.addFirst(next);
                Node n = snake.removeLast();
                vis.remove(n);
                vis.add(next);
            }
            return score;
        }
    }

