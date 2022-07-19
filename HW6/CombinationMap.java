public class CombinationMap<K,V> {
    /**Main container */
    private Entry<K, V>[] table;
    
    /**Capacity of container. Note that it should be prime and should
    be increased twice if the load factor is above the threshold. (10 in the example) */
    private static int tablesize;
    
    /**Number of entries which is not null */
    private int used;
    
    /**the largest prime number smaller than 0.8*tablesize (7 in the example) */
    private int Prime_number;

    /** Load factor */
    private double LOAD_THRESHHOLD = 0.75;
    
    /** Helper member field for remove*/
    private int tempIndex;
    
    /** Helper member field for remove*/
    private int indexAtGet;
    
    
    /** Constructor  */
    @SuppressWarnings("unchecked")
    CombinationMap(){
        tablesize = 31;
        used = 0;
        table = (CombinationMap.Entry<K, V>[]) new Entry<?,?>[tablesize]; //allocates table
        
        setPrimeNumber();
    }

    /**  Returns the value associated with the specified key. Returns null if the key is not present */
    @SuppressWarnings("unchecked")
    public V get(Object key){
        System.out.printf("");
        int probe = 1; 
        int hash = get_hash((K) key, probe); //getting hash code
        indexAtGet = -1;
        
        while(true){ //probing by while loop
            if(table[hash]!=null){
                if(((Comparable<K>) key).compareTo(table[hash].key)==0){ // keys at this index are matching
                    indexAtGet = hash;
                    return table[hash].value;
                }    
                else // keys at this index are not matching
                {
                    if(table[hash].index!=-1) //keys are not matching and "next" pointer has index 
                        hash = table[hash].index;
                    else //keys are not matching and "next" pointer has not index 
                        return null;      
                }
            }
            else return null;
            
        }
    }
    
    
    /** checks given number is prime or not */
    private boolean check_Prime(int num){
        boolean flag = false;
        for (int j = 2; j <= num / 2; ++j) {
            // condition for nonprime number
            if (num % j == 0) {
              flag = true;
              break;
            }
        }
        return flag; //number is flag
    }

    /** Finds largest prime number smaller than tablesize */
    private void setPrimeNumber(){
        for(int num = (int) (tablesize*0.8) ;num>0;num--){
            if(check_Prime(num)==false){
                Prime_number = num;
                break;
            } 
        }
    }

    /** Returns true if this table contains no key‐value mappings */
    private boolean isEmpty(){ 
        if(used==0) return true;
        return false;
    }
    
    /** Double hashing  */
    private int get_hash(K key, int i){
        int hash1 = key.hashCode() % tablesize;
        int hash2 = Prime_number - (key.hashCode() % Prime_number);
        int hash = (hash1 + (i * hash2)) % tablesize;
        if(hash < 0) hash+= tablesize;
        
        return hash;
    }

    /** Associates the specified value with the specified key. Returns the previous value
        associated with the specified key, or null if there was no mapping for the key */
    public V put(K key, V value){
        //Hash1 = key % tablesize (10 in our case)
        //Hash2 = Prime_number – (key % Prime_number)
        //Hash function = ( Hash1 + (i * Hash2) ) % tablesize for the ith probe.
        int i = 1;
        int hash = get_hash(key, i);
        
        Entry<K,V> newEntry = new Entry<K,V>(key, value);
        tempIndex = -1;
        
        while(true){
            
            
            if(table[hash] == null){ //this index is empty
                if(tempIndex!=-1) 
                    table[tempIndex].index = hash;
                
                table[hash] = newEntry;
                table[hash].connected = tempIndex;
                used++;
                break;
            }
            else{ //current index is not empty
                
                if(table[hash].index == -1){ // it's next doesn't point any index
                    tempIndex = hash;
                    i++;
                    hash = get_hash(key, i);
                }
                else{ //it's next points an index
                    tempIndex = -1;
                    i++;
                    hash = get_hash(key, i);
                }
            }
        }
        
        double limit = LOAD_THRESHHOLD * tablesize;
        if(limit <= used) rehash();
    
        return value;
    }
    
    
    /** Rehashes table if load */
    @SuppressWarnings("unchecked")
    private void rehash(){
        Entry<K, V>[] newTable = (CombinationMap.Entry<K, V>[]) new Entry<?,?>[tablesize*2];
        Entry<K,V>[] temp = table;
        table = newTable;
        tablesize = tablesize*2;
        used = 0;
        
        for(int i=0;i<temp.length;i++)
            if(temp[i] != null)
                put(temp[i].key, temp[i].value);
            
    }


    /** Removes the mapping for this key from this table if it is present (optional
    operation). Returns the previous value associated with the specified key, or null if
    there was no mapping */
    public V remove(Object key){
        
        get(key);
        if(indexAtGet == -1) return null;

        System.out.printf("\n%d || IndexatGet : %d",key,indexAtGet);
        int deletedIndex = indexAtGet;
        int controlIndex = deletedIndex;
        int temp;
        
        if(table[deletedIndex].index == -1){
            table[deletedIndex] = null;
            
        }
        else{ //silinecek index bağlı
            while(true){
                if(table[ table[controlIndex].index ].index == -1){ //sonraki 
                    temp = table[controlIndex].index;
                    table[controlIndex].index = -1;
                    controlIndex = temp;
                    break;
                }
                else{
                    controlIndex = table[controlIndex].index;
                }
            }

            int pointer = table[deletedIndex].index;
            table[deletedIndex] = table[controlIndex];
            table[deletedIndex].index = pointer;
            table[temp] = null;
        }
         
        return null;
    }
    
    public void print(){
        for(int i=0;i<table.length;i++){
            if(table[i] == null){
                System.out.printf("\n%d-------->NULL ",i);
            }
            else
            System.out.printf("\n%d------->%d | %s | %d ",i,table[i].key, table[i].value, table[i].index);
        }
    }
    
    /** Returns the size of the table */
    public int size(){
        return 0;
    }

    
    /** Contains key‐value pairs for a hash table. */
    protected static class Entry<K, V> implements Comparable<V> {
        
        
        private final K key; /** The key */
        private V value; /** The value */
        private int index;
        private int connected;
        /** Creates a new key‐value pair.
        @param key The key
        @param value The value
        */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            index = -1;
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
            return (String) value;
        }
    }



    
}
