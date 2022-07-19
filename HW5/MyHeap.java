import java.util.NoSuchElementException;
import java.util.Stack;
import java.lang.Math;
 
public class MyHeap<E> extends BinaryTree<E> implements Comparable<E>{
    
    
    /**total number of nodes in heap */
    private int number_of_nodes;
    /**helper field for adding removing processes */
    private int target_depth;
    /**helper field for adding removing processes */
    private int max_depth;
    /**helper field for adding removing processes */
    private HeapNode<E> target_node;
    
    
    /**
     * Constructor
     * Takes another Binary Heap and copies it
     * @param other BinaryHeap given by parameter
     */
    MyHeap(MyHeap<E> other){
        MyHeap<E> newHeap1 = new MyHeap<>();

        for(int i=0;i<other.number_of_nodes;i++){
            offer(peek());
            newHeap1.offer(peek());
            poll();
        }
        other.root = newHeap1.root;
    }

    /**
     * No parameter constructor
     */
    MyHeap(){
        number_of_nodes = 0;
    }

    
    /**
     * Constructor
     * Takes data as parameter and makes it root of the heap
     * @param data Root of the heap
     */
    MyHeap(E data){
        HeapNode<E> newNode = new HeapNode<E>(data);
        newNode.parent = null;
        root = newNode;
        number_of_nodes = 1;
    }


    /**
     * Takes another heap as parameter, and merge them. Protects original heaps and returns new one
     * @param other Other Binary Heap to be merged
     * @return returns new heap
     */
    public MyHeap<E> merge(MyHeap<E> other){
        
        MyHeap<E> mergedTree = new MyHeap<E>();
        MyHeap<E> newHeap1 = new MyHeap<E>();
        MyHeap<E> newHeap2 = new MyHeap<E>();
        int n1 = number_of_nodes;
        int n2 = other.number_of_nodes;
        
        
        for(int i=0;i<n1;i++){ // adding this heap to mergedTree temp heap
            mergedTree.offer(peek());
            newHeap1.offer(peek());
            poll();
        }
        
        for(int i=0;i<n2;i++){ // adding other heap to mergedTree and temp heap
            mergedTree.offer(other.peek());
            newHeap2.offer(other.peek());
            other.poll();
        }

        
        
        n1 = newHeap1.number_of_nodes;
        n2 = newHeap2.number_of_nodes;

        for(int i=0;i<n1;i++) //refilling trees
            offer(newHeap1.poll());
        
        for(int i=0;i<n2;i++) //refilling trees
            other.offer(newHeap2.poll());

        
        return mergedTree;
    }

    /**
     * with the help of number of nodes, this methods find maximum depth of tree
     */
    private void set_max_depth(){
        max_depth = 0;
        while(true){
            if( (Math.pow(2, max_depth) <= number_of_nodes) &&  (Math.pow(2, max_depth+1)-1 >= number_of_nodes) )
                break;
            max_depth++;   
        }
    }

    /**
     * Public offer method to insert new node to tree
     * @param item Data to be inserted to tree
     * @return returns true if it is succesfull
     */
    public boolean offer(E item){
        
        if(number_of_nodes == 0){ //if root is null
            HeapNode<E> newNode = new HeapNode<E>(item);
            newNode.parent = null;
            root = newNode;
            number_of_nodes = 1;
        }
        else{//root is not null

            target_depth=0;
            number_of_nodes++;
            while(true){
                if( (Math.pow(2, target_depth) <= number_of_nodes) &&  (Math.pow(2, target_depth+1)-1 >= number_of_nodes) )
                    break;
                target_depth++;   
            }        
            

            target_node = null;
            findAvailableNode((MyHeap.HeapNode<E>) root);
            HeapNode<E> newNode = new HeapNode<E>(item);
            newNode.depth = target_depth;
            
            if(target_node.left == null){
                target_node.left = newNode;
                newNode.parent = target_node;
            }
            else{ 
                target_node.right = newNode;
                newNode.parent = target_node;
            }

            fixTree(newNode); 
        }
        return true;
    }
    
    /**
     * After node is added, this method fix the tree according to min-heap
     * @param addedNode 
     */
    @SuppressWarnings("unchecked")
    private void fixTree(HeapNode<E> addedNode){
        int result;
        while(true){
            if(addedNode.parent == null){
                break;
            }
            result = ((Comparable<E>) addedNode.data).compareTo(addedNode.parent.data);
            if(result < 0){
                E temp = addedNode.parent.data;
                addedNode.parent.data = addedNode.data;
                addedNode.data = temp;
                addedNode = (MyHeap.HeapNode<E>) addedNode.parent;
            }
            else{
                break;
            }
            
        }
    }

    /**
     * This recursive method finds available node to insert new element
     * @param node Starts from root and goes on with childs
     */
    private void findAvailableNode(HeapNode<E> node){
        if(node==null){
            return;
        }
        if(target_node!=null || node.depth > target_depth)
            return;
         
        if ( (node.left == null || node.right == null) && (node.depth+1 == target_depth) ){
            target_node = node;
            return;
        }
        findAvailableNode((MyHeap.HeapNode<E>) node.left);
        findAvailableNode((MyHeap.HeapNode<E>) node.right);

    }

    /**
     * This recursive method finds true node to be replaced with removed node
     * @param root
     */
    private void findRemovedNode(HeapNode<E> root){
        if(root == null ||target_node!=null)
            return;
        
        if(root.depth == max_depth)
            target_node = root;
        
        findRemovedNode((MyHeap.HeapNode<E>) root.right);
        findRemovedNode((MyHeap.HeapNode<E>) root.left);
    }


    /**
     * Removes root of the tree by using poll() method
     * Throws exception
     * @return Returns data of root of the heap
     */
    public E remove(){
        E result = poll();
        if(result == null)
            throw new NoSuchElementException();
        else return result; 
    }

    /**
     * Removes root of the tree
     * @return Returns data of root of the tree
     */
    public E poll(){
        
        if(root==null){
            return null;
        } 
        
        E temp = root.data;
        
        if(number_of_nodes==1){
            temp = root.data;
            number_of_nodes--;
            root = null;
        }
        else{
            set_max_depth();
            target_node = null;
            findRemovedNode((MyHeap.HeapNode<E>) root);
            
            root.data = target_node.data;
            HeapNode<E> tempNode = (MyHeap.HeapNode<E>) target_node.parent;
            if(tempNode.right == target_node) tempNode.right = null;
            else tempNode.left = null;
                
            
            fixAfterRemove();
            number_of_nodes--;  
        }
        return temp;
    }

    /**
     * Returns root of the tree without removing
     * @return Data of root of tree
     */
    public E peek(){
        if(root == null)
            return null;
        else return root.data;
    }
    
    /**
     * Returns data of root without removing it
     * @return Data of root
     * @throws Exception
     */
    public E element() throws Exception{
        if(root == null){
            throw new Exception("\nEMPTY TREE");
        }
        return root.data;   
    }
    
    /**
     * Returns true if tree is empty, else false
     * @return True at empty, else false
     */
    public boolean isEmpty(){
        if(root == null) return true;
        return false;
    }

    /**
     * Fixing tree in this method after element is removed and replaced by another node
     */
    @SuppressWarnings("unchecked")
    private void fixAfterRemove(){
        HeapNode<E> tempNode = (MyHeap.HeapNode<E>) root;
        E tempData;
        while(true){
            if(tempNode.left != null && tempNode.right == null){ //right child is null
                if(((Comparable<E>) tempNode.data).compareTo(tempNode.left.data) > 0){
                    tempData = tempNode.data;
                    tempNode.data = tempNode.left.data;
                    tempNode.left.data = tempData;
                    tempNode = (MyHeap.HeapNode<E>) tempNode.left;
                }
                else break;
                
            }
            else if(tempNode.right != null && tempNode.left == null){ //left chidl is null
                if(((Comparable<E>) tempNode.data).compareTo(tempNode.right.data) > 0){
                    tempData = tempNode.data;
                    tempNode.data = tempNode.right.data;
                    tempNode.right.data = tempData;
                    tempNode = (MyHeap.HeapNode<E>) tempNode.right;
                }
                else break;
            }
            else if(tempNode.left == null && tempNode.right == null){ // end of the tree
                break;
            }
            else{ //either of the child is not empty
                 if(((Comparable<E>) tempNode.left.data).compareTo(tempNode.right.data) < 0){
                     //left is larger
                     if(((Comparable<E>) tempNode.data).compareTo(tempNode.left.data) > 0){
                        tempData = tempNode.data;
                        tempNode.data = tempNode.left.data;
                        tempNode.left.data = tempData;
                        tempNode = (MyHeap.HeapNode<E>) tempNode.left;
                     }
                     else break;
                 }
                 else{
                     //right is larger
                     if(((Comparable<E>) tempNode.data).compareTo(tempNode.right.data) > 0){
                        tempData = tempNode.data;
                        tempNode.data = tempNode.right.data;
                        tempNode.right.data = tempData;
                        tempNode = (MyHeap.HeapNode<E>) tempNode.right;
                     }
                     else break;
                 }
            }

        }
    }

    /**
     * Checks given element is in the tree or not by using stack container
     * @param item
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean contains(E item){
        
        Stack<Node<E>> stack = new Stack<Node<E>>(); // create an empty stack
        Node<E> currentNode = root; // start from the root node (set current node to the root node)
    
        while (!stack.empty() || currentNode != null){ // if the current node is null and the stack is also empty, we are done
        
            // if the current node exists, push it and move to its left child
            if (currentNode != null){
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            else{
                // if the current node is null, pop an element set the current node to its right child
                currentNode = stack.pop();
                if(((Comparable<E>) currentNode.data).compareTo(item) == 0)
                    return true;
                currentNode = currentNode.right;
            }
        }
        
        return false;
    }
 

    @Override
    public int compareTo(E o) {
        return 0;
    }

    /**
     * Returns complete string of tree by using recursive preOrderPrint method
     * @return Data of each node
     */
    public String toString(){
        if(number_of_nodes!=0){
            HeapNode<E> node = (MyHeap.HeapNode<E>) root;
            StringBuilder sb = new StringBuilder();
            preOrderPrint(node, node.depth, sb);
            return sb.toString();
        }
        return "\nEMPTY TREE\n";
    }

    /**
     * Recursive traversing method, appends data of each node into Stringbuilder instance
     * @param root
     * @param depth
     * @param sb
     */
    private void preOrderPrint(BinaryTree.Node<E> root, int depth, StringBuilder sb) {

        for(int i=0;i<depth;i++) 
            sb.append("  ");
        
        if(root == null) 
            sb.append("null\n");
        
        else{
            sb.append(root.toString());
            sb.append("\n");
            preOrderPrint(root.left, depth+1, sb);
            preOrderPrint(root.right, depth+1, sb);
        }
    }

    /**
     * HeapNode class that we extend from Node class in BinaryTree class
     * It has additional fields
     */
    protected static class HeapNode<E> extends Node<E>{

        protected Node<E> parent;
        private int depth;
        public HeapNode(E data) {
            super(data);
            parent = null;
        }

    }


}
