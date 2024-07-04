package coding.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

    public static void main(String[] args) {

        int[] a = new int[]{1,3};
        int[] b = new int[]{2,4,6};
        int[] c = new int[]{0, 9, 10, 11};
        List<int[]> ipArrs = new ArrayList<>();
        ipArrs.add(a);
        ipArrs.add(b);
        ipArrs.add(c);
        Solution sol = new Solution();
        int[] res = sol.solve(ipArrs);
        for(int i=0; i<res.length; i++){
            System.out.print(res[i] + " ");
        }
    }
}

class Solution{
    class Element{
        int no;
        int idx;
        int arrIdx;

        public Element(int no, int idx, int arrIdx) {
            this.no = no;
            this.idx = idx;
            this.arrIdx = arrIdx;
        }
    }

    PriorityQueue<Element> pq = new PriorityQueue<>((Element e, Element b) -> e.no - b.no); //min heap
    public int[] solve(List<int[]> arrays){
        int totalLength=0;

        for(int i=0;i<arrays.size(); i++){
            int[] a = arrays.get(i);
            pq.offer(new Element(a[0],0,i));
            totalLength += a.length;
        }

        int[] ans = new int[totalLength];
        int k=0;

        while(!pq.isEmpty()){
            Element curr = pq.poll();
            ans[k++] = curr.no;

            if(curr.idx + 1 < arrays.get(curr.arrIdx).length){
                pq.offer(new Element(arrays.get(curr.arrIdx)[curr.idx + 1],curr.idx+1, curr.arrIdx));
            }
        }
        return ans;
    }
}
//TC?