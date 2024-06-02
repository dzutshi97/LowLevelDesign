package coding.Atlassian.EntryExit;

import java.util.*;

    public class MaxUniqueElements {
        private int maxUniqueCount;

        public int findMaxUnique(List<List<Integer>> vectors) {
            maxUniqueCount = 0;
            backtrack(vectors, 0, new HashSet<>());
            return maxUniqueCount;
        }

        private void backtrack(List<List<Integer>> vectors, int index, Set<Integer> currentSet) {
            if (index == vectors.size()) {
                maxUniqueCount = Math.max(maxUniqueCount, currentSet.size());
                return;
            }

            for (int num : vectors.get(index)) {
                if (currentSet.add(num)) {
                    backtrack(vectors, index + 1, currentSet);
                    currentSet.remove(num);
                }
            }

            // Consider the case of not taking any number from the current vector
            backtrack(vectors, index + 1, currentSet);
        }

        public static void main(String[] args) {
            MaxUniqueElements solution = new MaxUniqueElements();
            List<List<Integer>> vectors = new ArrayList<>();
//            vectors.add(Arrays.asList(1, 2, 3));
            vectors.add(Arrays.asList(1));
            vectors.add(Arrays.asList(2, 3));
            System.out.println("Maximum number of unique elements: " + solution.findMaxUnique(vectors));
        }
    }

