import java.util.LinkedList;

public class KWHashMap<K,V>{
    
    private int size;
    private BinarySearchTree< Entry<K, V> >[] table;
    private int capacity;
    
    
    @SuppressWarnings("unchecked")
    KWHashMap(){
        capacity = 31;
        size = 0;
        table = new BinarySearchTree[capacity];
        
        for(int i=0;i<capacity;i++) 
            table[i] = new BinarySearchTree<Entry<K, V>>();
        
    }

    // Returns the value associated with the specified key. Returns null if the key is not present
    @SuppressWarnings("unchecked")
    public V get(Object key){
        int index = key.hashCode() % table.length;
        if(index<0) index+=table.length;
        
        Entry<K,V> target = new Entry<K,V>((K)key, null);
        
        if(table[index].find(target)==null) return null;
        else return table[index].find(target).value;
        
    }

    //Returns true if this table contains no key‐value mappings
    boolean isEmpty(){
        if(size==0) return true;
        return false;
    }

    //Associates the specified value with the specified key. Returns the previous value
    //associated with the specified key, or null if there was no mapping for the key
    public V put(K key, V value){
        
        double limit = ((4*capacity)/5);
        if(limit<=size) 
            rehash();
        
        int index = key.hashCode() % table.length;
        
        if(index<0) 
            index+=table.length;
        
        Entry<K,V> item = new Entry<K,V>(key, value);
        
        //same key check
        if(table[index].find(item)!=null) return null;
        
        table[index].add(item);
        size++;
        return value;
    }
    

    @SuppressWarnings("unchecked")
    private void rehash(){
        capacity = capacity*2;
        BinarySearchTree< Entry<K, V> >[] newTable = new BinarySearchTree[capacity];
        for(int i=0;i<capacity;i++) newTable[i] = new BinarySearchTree<Entry<K, V>>();

        LinkedList< Entry<K, V> > list = new LinkedList< Entry<K, V> >();
        
        for(int i=0;i<table.length;i++){
            if(table[i] != null){
                table[i].iterate(list);
            }    
        }

        fillTable(newTable, list);
        
    }

    public void print(){
        LinkedList< Entry<K, V> > list = new LinkedList< Entry<K, V> >();
        
        for(int i=0;i<table.length;i++){
            table[i].iterate(list);
        }

        for(int i=0;i<list.size();i++)
            System.out.printf("\n----->%s",list.get(i).value);
        
    }

    private void fillTable(BinarySearchTree< Entry<K, V> >[] newTable, LinkedList< Entry<K, V> > list){
        Entry<K,V> tempEntry;
        int index;
        int listSize = list.size();
        for(int i=0;i<listSize;i++){
            tempEntry = list.poll();
            index = tempEntry.key.hashCode() % newTable.length;
        
            if(index<0) 
                index+=newTable.length;
            
            newTable[index].add(tempEntry);
            //System.out.printf("\nnWwe table : %d | index : %d",newTable.length,index);
        }
        
        table = newTable;        
    }

    

    //Removes the mapping for this key from this table if it is present (optional
    //operation). Returns the previous value associated with the specified key, or null if
    //there was no mapping
    @SuppressWarnings("unchecked")
    public V remove(Object key){
        int index = key.hashCode() % table.length;
        if(index<0) 
                index+=table.length;

        Entry<K,V> target = new Entry<K,V>((K) key, null);
        
        V val = (V) table[index].delete(target);
        
        if(val==null) return null;
        else return (V) val;

    }

    //Returns the size of the table
    public int size(){
        return size;
    }

   

    /** Contains key‐value pairs for a hash table. */
    protected static class Entry<K, V> implements Comparable<V> {
        
        
        private final K key; /** The key */
        private V value; /** The value */
        
        /** Creates a new key‐value pair.
        @param key The key
        @param value The value
        */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
       
       
        /** Retrieves the key.
        @return The key
        */
        public K getKey() {
            return key;
        }
       
        
        /** Retrieves the value.
        @return The value
        */
        public V getValue() {
            return value;
        }
        
        
        /** Sets the value.
        @param val The new value
        @return The old value
        */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
        
        
        @Override
        @SuppressWarnings("unchecked")
        public int compareTo(Object o) {
            
            Entry<K, V> temp = (Entry<K,V>)o;
            int result = ((Comparable<K>) temp.key).compareTo(key);
            return result;
        }


        public String toString(){
            //System.out.printf("\nTOSTRING");
            //value'ların stringi buradan return ediliyor
            return (String) value;
        }
    }


        

    //binary search tree tutan arraylist olacak


}