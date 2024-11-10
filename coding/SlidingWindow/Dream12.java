package Service;

public class Dream12 {

    public static void main(String[] args) {

//        int[] arr = new int[]{1, 5 , 11, 5}; //true
        int[] arr = new int[]{1,2,3,5}; //false
//        int[] arr = new int[]{1,2,3,4,5,5}; //true
        Dream12 dream12 = new Dream12();
        System.out.println(dream12.solve(arr));
    }

    public boolean solve(int [] arr){

        int sum=0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
        }
        if(sum % 2 != 0){
            return false;
        }
        int targetSum = sum/2;
        System.out.println(targetSum);

        for(int i=0; i<arr.length-1; i++){
            int subsetSum = arr[i];
            if(subsetSum == targetSum){
                return true;
            }
            for(int j=i+1;j<arr.length; j++){
                subsetSum  += arr[j];
                if(subsetSum == targetSum){
                    System.out.println(subsetSum);
                    return true;
                }
            }
        }
        return false;
    }
}
