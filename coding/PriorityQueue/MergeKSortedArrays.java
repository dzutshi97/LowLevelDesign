package coding.PriorityQueue;

import java.util.*;

class Solution {

    class Element{
        int value;
        int idx;
        int arrIdx;

        public Element(int value, int idx, int arrIdx){
            this.value= value;
            this.idx=idx;
            this.arrIdx= arrIdx;
        }
    }

    public void mergeKSortedArrays(List<int[]> arrays) {

        Queue<Element> pq = new PriorityQueue<>((e1, e2) -> e1.value - e2.value); //min heap - by default

        for(int i=0;i<arrays.size(); i++){
            int value = arrays.get(i)[0];
            pq.add(new Element(value, 0, i));
        }

        List<Integer> ans = new ArrayList<Integer>();
        while(!pq.isEmpty()){
            Element curr = pq.remove();
            ans.add(curr.value);
            int currIdx = curr.idx;
            int currArrIdx = curr.arrIdx;

            if(currIdx + 1 < arrays.get(currArrIdx).length){
                int[] currArr = arrays.get(currArrIdx);
                int nextVal = currArr[currIdx + 1];
//            OR    int nextVal = arrays.get(currArrIdx)[currIdx + 1];
                pq.add(new Element(nextVal,currIdx + 1, currArrIdx));
            }
        }

        for(int a: ans){
            System.out.println(a+ " ");
        }

    }
}
public class MergeKSortedArrays {

    public static void main(String[] args) {

        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{2, 3, 5, 7, 8};
        int[] c = new int[]{6, 9, 11, 14};

        List<int[]> al = new ArrayList<>();
        al.add(a);
        al.add(b);
        al.add(c);

        Solution s = new Solution();
        s.mergeKSortedArrays(al);
    }
}

//TC?
