public class Q3 {
    /**
     * 
     * @param arr Unsorted integer array
     * @param target Integer value that we are trying to reach by summing
     */
    public static void Question3(int[] arr, int target){
        if(arr.length>2) 
            Question3(arr, target, 0,1, arr[0]);
        else if(arr[0] == target){
            System.out.printf("\n%d",arr[0]);
        }    
    }

    /**
     * 
     * @param arr Unsorted integer array
     * @param target Integer value that we are trying to reach by summing
     * @param index1 start index
     * @param index2 ending index
     * @param sum Current sum from start index to ending index
     */
    public static void Question3(int[] arr, int target, int index1, int index2, int sum){
        //base case
        if(arr == null) return;
        if(index2>arr.length-1) return;
        
        sum += arr[index2];
        
        if(arr[index1]==target){ //if index1 is on the target, print it and continue searching
            print(arr, index1, index1);
            Question3(arr,target,index1+1,index1+2,arr[index1+1]);
        }
        else if(sum==target){ //if sum reached target, print from index1 to index2 and continues searching
            print(arr, index1, index2);
            Question3(arr, target, index1+1, index1+2, arr[index1+1]);
        }
        else if(sum>target){ //if sum exceeds target, increases index1 by 1 and continues searching
            Question3(arr, target, index1+1, index1+2, arr[index1+1]);
        }
        else if(sum<target){ //if sum is less than target, it adds [index2+1] into sum at next call
            Question3(arr, target, index1, index2+1, sum);
        }

        
    }

    /**
     * Recursively prints from index1 to index2
     * @param arr Integer array
     * @param index1 start position
     * @param index2 end position
     */
    public static void print(int arr[], int index1, int index2){
        if(index1>index2){
            System.out.printf("\n");    
            return;
        } 

        System.out.printf("%d ",arr[index1]);
        print(arr, index1+1, index2);
    }

    public void test(){
        int[] arr = {1,3,5,2,3,2,0,8,4,3,11,6,1,8,2,2,2,2,2,7,7};
        
        System.out.printf("\n-------------------- 7 --------------------\n");
        Question3(arr,7);
        
        System.out.printf("\n-------------------- 10 --------------------\n");
        Question3(arr,10);
        
        System.out.printf("\n-------------------- 12 --------------------\n");
        Question3(arr,12);
        
        System.out.printf("\n-------------------- 14 --------------------\n");
        Question3(arr,14);
        
        System.out.printf("\n-------------------- 9 --------------------\n");
        Question3(arr,9);
        
        System.out.printf("\n-------------------- -1 --------------------\n");
        Question3(arr,-1);
        
        System.out.printf("\n-------------------- 50 --------------------\n");
        Question3(arr,50);
        
        System.out.printf("\n-------------------- 60 --------------------\n");
        Question3(arr,60);

        System.out.printf("\n-------------------- 2 --------------------\n");
        Question3(arr,2);

        System.out.printf("\n-------------------- 1000 --------------------\n");
        Question3(arr,1000);
    }
    
}
