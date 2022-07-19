public class ArrayBasedBinarySearchTree<E> extends BinaryTree<E> implements SearchTree<E> {
    /*
        Arr[(i-1)/2]	Returns the parent node
        Arr[(2*i)+1]	Returns the left child node
        Arr[(2*i)+2]	Returns the right child node
    */

    
    /** our tree container*/
    private E[] myArray;
    
    /**size of the array */
    private int size;
   

    /**
     * Helper method to printing tree recursively
     * @param str String that we append nodes
     * @param index current index which is printed
     * @param count Counter
     */
    private void preOrderPrint(StringBuilder str, int index, int count){
        if(index < myArray.length){
            for(int i=0;i<count;i++){
                str.append(" ");
            }
            str.append(myArray[index]+"\n");
            if(myArray[index]!=null)
                preOrderPrint(str, (2*index) + 1, count+1); //goes to left child
            if(myArray[index]!=null)
                preOrderPrint(str, (2*index) + 2, count+1); //goes to right child
        }
    }

    /**
     * Constructor
     * Starts size from 3
     */
    @SuppressWarnings("unchecked")
    ArrayBasedBinarySearchTree(){
        size = 3; //initial size
        myArray = (E[]) new Object[size];

        for(int i=0;i<size;i++) 
            myArray[i] = null; 
    }
    
    
    /**
    * Inserts item where it belongs in the tree. Returns true if item is inserted; false
      if it isn’t (already in tree)
      @param item Data that we want to insert tree
    */
    @Override
    public boolean add(E item) {
        
        if(myArray[0] == null){ //if root is null
            myArray[0] = item;
            return true;
        }
        
        int index = findPlace(item); //find available place for item
        if(index!=-1){
            myArray[index]=item;
            if(2*index+1>size)
                reallocateArray();
            return true;
        }
        
        return false;
    }

    
    /**
     * Returns an index for given item to add
     * @param item Data that we search
     * @return Returns index of given data
     */
    @SuppressWarnings("unchecked")
    private int findPlace(E item){

        int i=0;
        int result;
        if(contains(item)==true) //same element cant be exist at the same time
            return -1;

        
        while(i<size){
            if(myArray[i] == null) return i;

            result = ((Comparable<E>) myArray[i]).compareTo(item);
            
            if(result > 0)
                i = (2*i)+1;
            else if(result < 0)
                i = (2*i)+2;

        }
        
        
        reallocateArray();
        return i;
    }

    
    /**
     * Reallocates size of the array by 2*size + 1 
     */
    @SuppressWarnings("unchecked")
    public void reallocateArray(){
        
        int newCapacity = (2*size)+1;
        E[] newArray = (E[]) new Object[newCapacity];
        
        for(int i=0;i<size;i++) //copying array 
            newArray[i] = myArray[i];
        
        size = newCapacity;
        myArray = newArray;

    }


    
    /**
     * Finds index of given item
     * @param item Data that we search
     * @return Returns index of given data
     */
    @SuppressWarnings("unchecked")
    private int findIndex(E item){

        int result = -1;
        for(int i=0;i<size;i++){
            
            if(myArray[i]!=null)
                result = ((Comparable<E>) myArray[i]).compareTo(item);
              
            if(result==0) return i;
        }

        return -1;
    }


    
    
    /**
     * Returns true if target is found in the tree
     * @param target Data that we search
     */
    @Override
    public boolean contains(E target) {  
        if(findIndex(target)!=-1) return true;
        return false;
    }

    
    
    /**
     * Returns a reference to the data in the node that is equal to target. If no such
        node is found, returns null
        @param target Data that we search
        @return Returns item that we search
     */
    @Override
    public E find(E target) {
        int index = findIndex(target);
        if(index!=-1) return myArray[index];
        return null;
    }

    
    
    /**
     * Removes target (if found) from tree and returns it; otherwise, returns null
     * @param target Data that we search
     */
    @Override
    public E delete(E target) {
        //first finds index of element that we want to delete
        //check first left and right child
        //if there is no child, make it null
        //if there is a child, shift it
        //if there is two child, find mostLeft and replace it

        int indexOfTarget = findIndex(target);
        
        if(indexOfTarget!=-1 ){
            E temp = myArray[indexOfTarget];
            int indexOfReplaced = 0;

            if( myArray[2*indexOfTarget+1] != null && myArray[2*indexOfTarget+2] == null ){      //there is a child at left
                myArray[indexOfTarget] = myArray[2*indexOfTarget+1];
                myArray[2*indexOfTarget+1] = null;
            }
            else if( myArray[2*indexOfTarget+1] == null && myArray[2*indexOfTarget+2] != null ){ //there is a child at left
                myArray[indexOfTarget] = myArray[2*indexOfTarget+2];
                myArray[2*indexOfTarget+2] = null;
            }
            else if( myArray[2*indexOfTarget+1] == null && myArray[2*indexOfTarget+2] == null ){ //no child
                myArray[indexOfTarget] = null;
            }
            else if( myArray[2*indexOfTarget+1] != null && myArray[2*indexOfTarget+2] != null ){ //two child
                indexOfReplaced = findMostLeft(indexOfTarget);
                myArray[indexOfTarget] = myArray[indexOfReplaced];
                myArray[indexOfReplaced] = null;
            }
            
            return temp; // returns deleted element
        }
        return null;
    }


    /**
     * Returns index of findMostLeft node of tree
     * @param index index of element to be deleted
     * @return return index
     */
    private int findMostLeft(int index){
        int nextIndex = index;
        while(true){
            nextIndex = (nextIndex*2)+1;
            if(nextIndex > size){
                break;
            }
            if(myArray[nextIndex]==null){
                break;
            }
        }
        nextIndex = (nextIndex-1)/2;
        return nextIndex;
    }


    /**
     * Recursive method to print tree. Appends data of each node into Stringbuilder
     * @param index current index
     * @param counter
     * @param str StringBuilder instance
     */
    private void preOrderTraverse(StringBuilder str,int index,int count){
        if(index < myArray.length){
            for(int i=0;i<count;i++) str.append("  ");
            
            str.append(myArray[index] + "\n");
            if(myArray[index]!= null) preOrderTraverse(str,2*index + 1, count+1);
            if(myArray[index]!= null) preOrderTraverse(str,2*index + 2, count+1);
        }
    }

    /**
     * Calls preOrderTraverse to print tree
     * @return Returns complete string of tree
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(sb,0, 0);
        return sb.toString();
    }

    
    /**
     * Removes target (if found) from tree and returns true; otherwise, returns false
     */
    @Override
    public boolean remove(E target) {
        if(delete(target) == null)
            return false;
        else return true;
    }

    
}
