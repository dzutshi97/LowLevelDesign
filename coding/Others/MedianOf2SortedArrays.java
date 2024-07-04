package coding.Others;

import java.util.List;

//LC: 4. Median of Two Sorted Arrays
public class MedianOf2SortedArrays {
    public static double solve(int[] l1, int[] l2) {
        int i = 0;
        int j = 0;
        int m1 = -1;
        int m2 = -1;

        for (int count = 0; count <= (l1.length + l2.length) / 2; count++) {
            if (i != l1.length && j != l2.length) {
                m2 = m1;
                if (l1[i] < l2[j]) {
                    m1 = l1[i];
                    i++;
                } else if (l2[j] < l1[i]) {
                    m1 = l2[j];
                    j++;
                }
            }
            if (i < l1.length) {
                m1 = l1[i];
                i++;

            } else if (j < l2.length) {
                m1 = l2[j];
                j++;
            }
        }
        if ((l1.length + l2.length) % 2 == 1) {
            return (double) m1;
        } else {
            double ans = (double) m1 + (double) m2;
            return ans / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] a1= {1,3};
        int[] a2 ={2};
        //ans 2.00000
        double ans = solve(a1,a2);
        System.out.println(ans);
    }
}

