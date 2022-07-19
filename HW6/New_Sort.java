public class New_Sort<T> {
    int MIN;
    int MAX;
    T temp;
    
    public void sort(T[] array){
        MAX = 0;
        MIN = 0;

        new_sort(array, 0, array.length-1);
    }
    
    public void print(T[] array){
        for(int i=0;i<array.length;i++){
            System.out.printf("  | %d | ",array[i]);
        }
    }

    public void new_sort(T[] array, int head, int tail){
        if(head > tail) 
            return;
            
        min_max_finder(array,head,tail);
        swap(array, head , MIN);

        
        if(MAX == head){ MAX = MIN;}
        
        min_max_finder(array,head,tail);
        swap(array, tail, MAX);
            
        MIN = head+1;
        MAX = tail-1;
        new_sort(array, head+1, tail-1);
    
    }

    private void swap(T[] array, int index1, int index2){
        T temp = array[index1]; 
        array[index1] = array[index2]; 
        array[index2] = temp; 
    }

    @SuppressWarnings("unchecked")
    private void min_max_finder(T[] array, int head, int tail){

        if(head>tail || head>=array.length ) return;
    
        //minimum
        if( ((Comparable<T>)array[head]).compareTo(array[MIN]) <= 0)
            MIN = head;
        
        //maximum
        if( ((Comparable<T>)array[head]).compareTo(array[MAX]) >= 0)
            MAX = head;
        
        min_max_finder(array, head+1, tail);
    }


}

