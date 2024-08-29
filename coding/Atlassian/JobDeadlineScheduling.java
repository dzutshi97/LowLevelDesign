package coding.Atlassian;
import java.util.Arrays;

/**
 * https://www.ambitionbox.com/interviews/atlassian-question/job-scheduling-problem-you-are-given-a-list-of-n-jobs-which-has-to-be-performed-each-job-is-associated-with-a-deadline-and-a-profit-if-the-job-is-completed-before-the-jtzP3fvh
 */
    class Job {
        char id;      // Job ID
        int profit;   // Profit if job is completed before or on deadline
        int deadline; // Deadline of the job

        Job(char id, int profit, int deadline) {
            this.id = id;
            this.profit = profit;
            this.deadline = deadline;
        }
    }

    public class JobDeadlineScheduling {

        public static int maxProfit(Job[] jobs) {
            // Sort jobs in decreasing order of profit
            Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

            // Find the maximum deadline
            int maxDeadline = 0;
            for (Job job : jobs) {
                if (job.deadline > maxDeadline) {
                    maxDeadline = job.deadline;
                }
            }

            // Create an array to keep track of free time slots
            boolean[] slots = new boolean[maxDeadline];

            // Initialize total profit
            int totalProfit = 0;

            // Iterate over the sorted jobs
            for (Job job : jobs) {
                // Find a free slot for this job (starting from the last possible slot)
                for (int j = Math.min(maxDeadline, job.deadline) - 1; j >= 0; j--) {
                    if (!slots[j]) {
                        // Assign the job to this slot
                        slots[j] = true;
                        totalProfit += job.profit;
                        break;
                    }
                }
            }

            return totalProfit;
        }

        public static void main(String[] args) {
            // Example jobs
            Job[] jobs = {
                    new Job('A', 20, 1),
                    new Job('B', 30, 2),
                    new Job('C', 40, 2)
            };

            int maxProfit = maxProfit(jobs);
            System.out.println("Maximum Profit: " + maxProfit);
        }
    }

/**
 * GREEDY Approach:
 * Example with the Allocation Strategy:
 * Consider the three jobs again:
 *
 * Job A: Profit = 20, Deadline = 1
 * Job B: Profit = 30, Deadline = 2
 * Job C: Profit = 40, Deadline = 2
 * Steps:
 * Sort Jobs by Profit:
 *
 * First, sort the jobs by profit in descending order: Job C, Job B, Job A.
 * Schedule Job C:
 *
 * Job C has a deadline of 2. We start by trying to schedule it as late as possible (at t = 2).
 * Since t = 2 is available, we assign Job C to t = 2.
 * Schedule Job B:
 *
 * Job B also has a deadline of 2. We try to schedule it at t = 2, but it’s already taken by Job C.
 * Therefore, we schedule Job B at t = 1, the next available slot before its deadline.
 * Schedule Job A:
 *
 * Job A has a deadline of 1. But since t = 1 is already occupied by Job B, we cannot schedule Job A.
 * Final Schedule:
 * Time t = 1: Job B (Profit = 30)
 * Time t = 2: Job C (Profit = 40)
 * Total Profit:
 * The total profit is 30 (Job B) + 40 (Job C) = 70.
 * Key Insight:
 * By starting the allocation from the latest possible time (the job's deadline), we ensure that we're not unnecessarily blocking time slots that could be used by other jobs with tighter deadlines, leading to a higher overall profit.
 *
 * If you were to start by scheduling jobs as early as possible (from t = 1 onwards), you might end up in a situation where a later job with a high profit cannot be scheduled due to all earlier time slots being filled.
 */
