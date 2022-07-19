import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//----------------------------------------LIST CLASS----------------------------------------------------
public class LDLinkedList<E> extends AbstractList<E> implements List<E>, Iterable<E> {

    //------LIST FIELDS---------
    
    /**
     * Reference to head node
     */
    private Node<E> head = null;
    
    /**
     * Reference to tail node
     */
    private Node<E> tail = null;
    
    /**
     * Size of linkedlist
     */
    private int size = 0;

    public LinkedList<Node<E>> lazy_linked;

    //----------------------------------------LIST METHODS----------------------------------------------------
    
    
    public LDLinkedList(){
        lazy_linked = new LinkedList<Node<E>>();
    }
    
    private int check_lazy(E e){
        
        for(int i=0;i<lazy_linked.size();i++){
            E obj = lazy_linked.get(i).data;
            if(obj == e){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Inserting given object to given index
     * @param index position of linkedlist where obj will be inserted
     * @param obj object that will be inserted
     */
    public void add(int index, E obj){
        listIterator(index).add(obj);
    }
    
    /**
     * Inserting given object to end of linked list
     * @param e object that will be inserted
     */
    @Override
    public  boolean add(E e) {
        addLast(e);
        return true;
    }

    /**
     * Inserting given object to beginning of linkedlist
     * @param obj object that will be inserted
     */
    public void addFirst(E obj){
        listIterator(0).add(obj);
    }
    
    /**
     * Inserting given object to end of the list
     * @param obj object that will be inserted
     */
    public void addLast(E obj){
        listIterator(size).add(obj);
    }
    

    /**
     * Returns reference of linked list's first element
     */
    public E getFirst(){
        final Node<E> node = head;
        if(node == null) 
            throw new NoSuchElementException();
        return node.data;
    }
    
    /**
     * Returns reference of linked list's last element
     */
    public E getLast(){
        final Node<E> node = tail;
        if(node == null)
            throw new NoSuchElementException();
        return node.data;
    }
    
    /**
     * Unlinking node for removing element
     * @param n 
     */
    private E seperateNode(Node<E> n) {
        Node<E> next = n.next;
        Node<E> prev = n.prev;
        E element = n.data;

        if(prev == null){
            head = next;
        } 
        else{
            prev.next = next;
            n.prev = null;
        }

        if(next == null){
            tail = prev;
        } 
        else{
            next.prev = prev;
            n.next = null;
        }

        //n.data = null;
        size--;
        return element;
    }

    /**
     * It removes given element from linkedlist
     * @param o Object that will be removed
     */
    public boolean remove(Object o){
        if(o != null){ //Object o might be null
            Node<E> temp = head; 
            while(temp != null){ 
                if(o.equals(temp.data)){
                    lazy_linked.add(temp);
                    seperateNode(temp);
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    /**
     * Returns element at given index
     * @param Index index of element that we want to access
     */
    @Override
    public E get(int index) {
        return listIterator(index).next();
    }
    
    /**
     * Returns size of linkedlist
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Returns my own custom iterator class LDListIterator
     */
    @Override
    public Iterator<E> iterator(){return new LDListIterator();}
    
    /**
     * Returns my own custom iterator class LDListIterator
     */
    @Override
    public ListIterator<E> listIterator(int index){
        return new LDListIterator(index);
    }

    
    /**
     * Returns my own custom iterator class LDListIterator
     */
    @Override
    public  ListIterator<E> listIterator() {
        return new LDListIterator();
    }

    @Override
    public E remove(int index) {
        Node<E> temp = head;
            if(index>=0 && index < size){
                for(int i=0;i<index;i++)
                    temp = temp.next;
                remove(temp.data);
            }
            else{
                throw new IndexOutOfBoundsException();
            }
        return null;
    }

    

    //----------------------------------------LDListIterator----------------------------------------------------
    public class LDListIterator implements ListIterator<E>{

        private Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index = 0;

        /**
         * Constructor with parameter, create iterator at given index
         * @param i Index of iterator that will be create at
         */
        public LDListIterator(int i){
            if(i<0||i>size){
                throw new IndexOutOfBoundsException("INVALID INDEX");
            }
            lastItemReturned = null;
            if(i==size){
                index = size;
                nextItem = null;
            }
            else{
                nextItem = head;
                for(index = 0;index < i; index++) 
                    nextItem = nextItem.next;
            }
        }

        /**
         * Constructor with no parameter, creates iterator at head
         */
        public LDListIterator(){
            this(0);
        }

        /**
         * Iterator adding method
         */
        @Override
        public void add(E e) {     
            int lazy_index = check_lazy(e);
            
            if(lazy_index==-1){
                if(head == null){
                    head = new Node<E>(e);
                    tail = head;
                }
                else if(nextItem == head){
                    Node<E> newNode = new Node<>(e);
                    newNode.next = nextItem;
                    nextItem.prev = newNode;
                    head = newNode;
                }
                else if(nextItem == null){
                    Node<E> newNode = new Node<>(e);
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                }
                else{
                    Node<E> newNode = new Node<>(e);
                    newNode.prev = nextItem.prev;
                    nextItem.prev.next = newNode;
                    newNode.next = nextItem;
                    nextItem.prev = newNode;
                }
            }
            else{
                System.out.printf("\n\n_____Lazy Deleting_____");
                if(head == null){
                    head = lazy_linked.get(lazy_index);
                    tail = head;
                }
                else if(nextItem == head){
                    Node<E> newNode = new Node<>(lazy_linked.get(lazy_index).data);
                    newNode.next = nextItem;
                    nextItem.prev = newNode;
                    head = newNode;
                }
                else{
                    Node<E> newNode = lazy_linked.get(lazy_index);
                    newNode.prev = nextItem.prev;
                    nextItem.prev.next = newNode;
                    newNode.next = nextItem;
                    nextItem.prev = newNode;
                }
                lazy_linked.remove(lazy_index);
            }
            
            size++;
            index++;
            lastItemReturned = null;

        }

        
        /**
         * Returns true if node that iterator indicates is not null
         */
        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        /**
         * Returns true if node before that iterator indicates is not null
         */
        @Override
        public boolean hasPrevious() {
           return (size!=0 && nextItem == null) || (nextItem.prev != null);  
        }

        /**
         * Returns element at next node
         */
        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /**
         * Returns index of node that iterator indicates
         */
        @Override
        public int nextIndex() {
            return index;
        }

        /**
         * Returns previous element that iterator indicates
         */
        @Override
        public E previous() {
            if(!hasPrevious()){
                throw new NoSuchElementException();
            }
            if(nextItem == null){
                nextItem = tail;
            }
            else{
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        /**
         * Returns index of previous element 
         */
        @Override
        public int previousIndex() {
            return index-1;
        }

        /**
         * Removes lastItemReturned
         */
        @Override
        public void remove() {
            lazy_linked.add(lastItemReturned);
            
            if(lastItemReturned==null){
                throw new IllegalStateException();
            }

            Node<E> lastNext = lastItemReturned.next;
            seperateNode(lastItemReturned);
            if (nextItem == lastItemReturned)
                nextItem = lastNext;
            else index--;
            
            lastItemReturned = null;
            
        }

        /**
         * Modifies lastItemreturned with "e"
         * @param e Object that will be setted
         */
        @Override
        public void set(E e) {
            if(lastItemReturned == null){
                throw new IllegalStateException();
            }
            lastItemReturned.data = e;
        }
      
    }
    
    //----------------------------------------NODE CLASS----------------------------------------------------
    private static class Node<E> {
        private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        Node(E obj){
            data = obj;
            next = null;
        }

    }




}    


