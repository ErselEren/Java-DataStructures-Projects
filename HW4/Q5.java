public class Q5 {
    /**
     * 
     * @param array char array
     */
    public static void f1(char[] array){
        if(array.length > 3)
            f1(array, 3, 0, array.length-1,0);
    }

    /**
     * Recursively prints all possible configurations
     * @param array char array
     * @param length Length of consecutive blocks which starts from 3
     * @param first Start index
     * @param last End index
     * @param c Counter
     */
    public static void f1(char[] array, int length, int first, int last, int c){
        
        //base case
        if(length==array.length){
            edit_array(array, first+length-1,first, length);
            print_array(array, 0);
            return;
        }  
        
        c=0;
        
        if( first > array.length - length ){ 
            return;
        }   
        else{
            c++;
            edit_array(array, first+length-1,first, length);
            print_array(array, 0);
            f1(array, length, first+length+c,last,c);
            cleararray(array, first, last);
        }
        
        f1(array, length, first+c,last,c);
        
        if(first==0){ 
            cleararray(array, 0, array.length-1);
            f1(array, length+1, 0,array.length,c);
        }
        
    }

    /**
     * Recursively clears 'O' chars from array to next printings
     * @param array Integer array
     * @param i1 start position
     * @param i2 end position
     */
    public static void cleararray(char[] array, int i1 , int i2){
        if(i1>i2) return;
        if(i2>=array.length) i2--;        
        array[i1] = '.';
        cleararray(array, i1+1, i2);
    }


    /**
     * recursively adds 'O' char into array
     * @param array
     * @param size
     * @param first
     * @param length
     */
    public static void edit_array(char[] array, int size, int first, int length){
        if(first>size) return;
        
        if(first<=array.length)
            array[first] = 'O';
        
        edit_array(array, size, first+1, length);
    }

    /**
     * Recursively prints array 
     * @param array
     * @param position
     */
    public static void print_array(char[] array, int position){
        if(position > array.length-1){
            System.out.printf("\n");    
            return;
        } 
        System.out.printf("%c ",array[position]);
        print_array(array, position+1);
    }

    public void test(){
        char[] array = new char[15];
        cleararray(array, 0, 15);
        f1(array);
    }

}
