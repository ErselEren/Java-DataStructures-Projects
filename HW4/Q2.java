public class Q2 {
    
    /**
     * Returns the number of elements between given upper and lower bound in sorted array by using binary search
     * @param arr Given sorted array
     * @param num1 lower bound
     * @param num2 upper bound
     * @return Returns number of element between these bounds
     */
    public static int Question2(int[] arr, int lower, int upper){
                //because method returns double, we need to cast it to int
        return (int)(Question2(arr,upper,0,arr.length,0) - Question2(arr,lower,0,arr.length,1)); 
    }

    /**
     * Wrapper method to find number of elements 
     * @param arr Given sorted array
     * @param num Bound that will be searched 
     * @param index1 
     * @param index2
     * @param flag indicates upper bound or lower bound
     * @return index of exact position 
     */
    public static double Question2(int[] arr, int num, int index1, int index2, int flag){
        
        if(arr==null) return 0; //if array is null return 0;
        if(num>arr[arr.length-1]) return arr.length - 0.5; //if upper bound is larger than biggest number in array
        if(num<arr[0]) return (-1)*(0.5); //if lower bound is lower than smallest number in array
        
        int mid = (index2+index1)/2; //binary search mid index
        
        if(arr[mid]==num){
            if(flag==1) return mid - 0.5;
            else if(flag==0) return mid+0.5;
        }
        if(arr[mid]>num && mid-1>=0 ){ 
            if(arr[mid-1]>num) // if mid index and one left index of mid is larger than our bound, shift searching position to left
                return Question2(arr, num, index1, mid-1,flag); 
            else if(arr[mid-1]==num && flag==0) return mid - 0.5;
            else if(arr[mid-1]==num && flag==1) return mid - 1.5;
            else return mid - 0.5; //else we found the exact position for that bound
        }
        if(arr[mid]<num && mid+1<=arr.length-1){
            if(arr[mid+1]<num) 
                return Question2(arr, num, mid+1, index2,flag);
            else if(arr[mid+1]==num && flag==0) return mid + 1.5;
            else if(arr[mid+1]==num && flag==1) return mid + 0.5;
            else  return mid + 0.5;  //else we found the exact position for that bound
        }
        
        return -1;
    }

    public void test(){
        int[] arr = {0,3,4,6,10,11,15,17,20,22,30};
        
        for(int i=0;i<arr.length;i++)
            System.out.printf("%d ",arr[i]);

        int number1 = -1;
        int number2 = 35;
        System.out.printf("\n\n\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 0;
        number2 = 30;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);
        
        number1 = 0;
        number2 = 3;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 0;
        number2 = 4;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 3;
        number2 = 3;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 4;
        number2 = 5;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);
        
        number1 = 16;
        number2 = 16;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 15;
        number2 = 17;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 15;
        number2 = 18;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 15;
        number2 = 20;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 30;
        number2 = 30;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 0;
        number2 = 0;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 1;
        number2 = 10;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = 100;
        number2 = 150;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);

        number1 = -30;
        number2 = -6;
        System.out.printf("\nThere are %d numbers -> between %d and %d \n",Question2(arr,number1,number2), number1,number2);
    }
    
}
