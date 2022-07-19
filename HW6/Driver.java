import java.util.Random;

public class Driver {

    public static boolean check_same(int[] array, int num){
        for(int i=0;i<array.length;i++){
            if(num == array[i]) return true;
        }
        return false;
    }

    public static void testCombination2(){
        CombinationMap<Integer, String> Combmap = new CombinationMap<Integer,String>();

        System.out.printf("\n--------Adding entries---------");
        
        System.out.printf("\n\n\n--------Entries are added---------\n\n");

        Combmap.put(8, "ersel-eight");
        Combmap.put(8, "ersel-eight2");
        Combmap.put(23, "celal-twenty-three");
        Combmap.put(9, "celal-nine");
        Combmap.put(1, "eren-one");
        Combmap.put(3, "ersel-tree");
        Combmap.put(4, "ersel-four");
        Combmap.put(12, "celal-twelve");
        Combmap.put(16, "eren-sixteen");
        Combmap.put(52, "ersel-fifty-two");
        Combmap.put(15, "eren-fifteen");
        
       
        
        System.out.printf("\n-----------Searching existing element------------");
        
        if(Combmap.get(8)==null)
            System.out.printf("\n!-!- There is no entry with this key -!-!\n");
        else
            System.out.printf("\n Value of entry with key '8' is %s\n",Combmap.get(8));
        
        
        
        System.out.printf("\n-----------Searching non-existing element------------");
       
        if(Combmap.get(987)==null)
            System.out.printf("\n!-!- There is no entry with key '987' -!-!\n");
        else
            System.out.printf("\n Value of entry with key '987' is %s\n",Combmap.get(987));
        
        
        
        
        System.out.printf("\n-----------Removing existing element------------");
        
        Object removed = Combmap.remove(15);
        if(removed!=null)
            System.out.printf("\nValue of removed entry with key '15' is %s\n",removed);
        else
            System.out.printf("\nThere is no element with key '15' \n");
        

        
        
        System.out.printf("\n-----------Removing non-existing element------------");
        
        Object removed2 = Combmap.remove(987);
        if(removed2!=null)
            System.out.printf("\nValue of removed entry with key '987' is %s\n",removed2);
        else
            System.out.printf("\nThere is no element with key '987' \n");
        

        
        System.out.printf("\n----------------Printing all values--------------------");
        Combmap.print();
        System.out.printf("\nI put 2 entries with same key, first (8, ersel-eight), then (8,ersel-eight2). But only first one is inserted. Same keys are not allowed.\n");
    }

    public static void testKWHashMap2(){
        KWHashMap<Integer, String> KWmap = new KWHashMap<Integer,String>();

        System.out.printf("\n--------Adding entries---------");
        
        System.out.printf("\n\n\n--------Entries are added---------\n\n");

        KWmap.put(7, "ersel-seven");
        KWmap.put(7, "ersel-seven2");
        KWmap.put(23, "celal-twenty-three");
        KWmap.put(9, "celal-nine");
        KWmap.put(1, "eren-one");
        KWmap.put(3, "ersel-tree");
        KWmap.put(4, "ersel-four");
        KWmap.put(12, "celal-twelve");
        KWmap.put(16, "eren-sixteen");
        KWmap.put(52, "ersel-fifty-two");
        KWmap.put(15, "eren-fifteen");
        
       
        
        System.out.printf("\n-----------Searching existing element------------");
        
        if(KWmap.get(7)==null)
            System.out.printf("\n!-!- There is no entry with this key -!-!\n");
        else
            System.out.printf("\n Value of entry with key '7' is %s\n",KWmap.get(7));
        
        
        
        System.out.printf("\n-----------Searching non-existing element------------");
       
        if(KWmap.get(999)==null)
            System.out.printf("\n!-!- There is no entry with key '999' -!-!\n");
        else
            System.out.printf("\n Value of entry with key '999' is %s\n",KWmap.get(999));
        
        
        
        
        System.out.printf("\n-----------Removing existing element------------");
        
        Object removed = KWmap.remove(23);
        if(removed!=null)
            System.out.printf("\nValue of removed entry with key '23' is %s\n",removed);
        else
            System.out.printf("\nThere is no element with key '23' \n");
        

        
        
        System.out.printf("\n-----------Removing non-existing element------------");
        
        Object removed2 = KWmap.remove(999);
        if(removed2!=null)
            System.out.printf("\nValue of removed entry with key '999' is %s\n",removed2);
        else
            System.out.printf("\nThere is no element with key '999' \n");
        

        
        System.out.printf("\n----------------Printing all values--------------------");
        KWmap.print();
        System.out.printf("\nI put 2 entries with same key, first (7, ersel-seven), then (7,ersel-seven2). But only first one is inserted. Same keys are not allowed.\n");
    }

    public static void testKWHashMap(int size){
        System.out.printf("\nKWHASHMAP");
        Random rand = new Random();
        int random_int;

        long start;
        long end;
        long elapsedTime;

        long average[] = new long[100];
        
        //100 elemanlı  0'dan 999'a kadar değişen değerli int arrayi
        int random_array[] = new int[size];
        for(int i=0;i<size;i++){ //100 elemanlı KWMap
            random_int = rand.nextInt(size*size);
            if(check_same(random_array,random_int)==true)
                i--;
            else
                random_array[i] = random_int;
        }



        //100 tane KWMap oluşturulacak hepsinin tek tek değerleri ölçülüp double average[] arrayine kaydedilecek
        for(int j = 0;j<100;j++){
            
           
            KWHashMap<Integer, String> KWmap100 = new KWHashMap<Integer,String>();
            
            start = System.nanoTime();
            
            for(int i=0;i<size;i++) //100 elemanlı KWMap
                KWmap100.put(random_array[i], "ersel");
            

            end = System.nanoTime();
            elapsedTime = end - start;
            
            average[j] = elapsedTime;
        }
        
        int toplam = 0;
        for(int i=0;i<100;i++) toplam = (int) average[i];
        
        
        System.out.printf("\n Size : %d  ||| Average time : %d\n",size, toplam/100);
    }

    public static void testCombination(int size){
        
        Random rand = new Random();
        int random_int;

        long start;
        long end;
        long elapsedTime;

        long average[] = new long[100];
        
        //100 elemanlı  0'dan 999'a kadar değişen değerli int arrayi
        int random_array[] = new int[size];
        for(int i=0;i<size;i++){ //100 elemanlı KWMap
            random_int = rand.nextInt(size*3);
            if(check_same(random_array,random_int)==true)
                i--;
            else
                random_array[i] = random_int;
        }



        //100 tane KWMap oluşturulacak hepsinin tek tek değerleri ölçülüp double average[] arrayine kaydedilecek
        for(int j = 0;j<100;j++){
            
           
            CombinationMap<Integer, String> KWmap100 = new CombinationMap<Integer,String>();
            
            start = System.nanoTime();
            
            for(int i=0;i<size;i++) //100 elemanlı KWMap
                KWmap100.put(random_array[i], "ersel");
            

            end = System.nanoTime();
            elapsedTime = end - start;
            
            average[j] = elapsedTime;

            
        }
        


        int toplam = 0;
        for(int i=0;i<100;i++) toplam = (int) average[i];
        
        
        System.out.printf("\n Size : %d  ||| Average time : %d\n",size, toplam/100);


    }

    public static void testMergeSort(){
        System.out.printf("\n MERGE_SORT");
        Integer[] array = {120,1,-1,33,99,100,65};
        for(int i=0;i<7;i++){
            System.out.printf("\n %d ",array[i]);
        }
        System.out.printf("\n-----------------------------------------\n");

        QuickSort<Integer> sort = new QuickSort<Integer>();
        sort.sort(array);

        for(int i=0;i<7;i++){
            System.out.printf("\n %d ",array[i]);
        }
        System.out.printf("\n");
    }

    public static void testQuickSort(){
        System.out.printf("\n QUICK_SORT");
        Integer[] array = {120,1,-1,33,99,100,65};
        for(int i=0;i<7;i++){
            System.out.printf("\n %d ",array[i]);
        }
        System.out.printf("\n-----------------------------------------\n");

        QuickSort<Integer> sort = new QuickSort<Integer>();
        sort.sort(array);

        for(int i=0;i<7;i++){
            System.out.printf("\n %d ",array[i]);
        }
        System.out.printf("\n");
    }

    public static void testnew_sort(){
        System.out.printf("\n NEW_SORT");
        Integer[] array = {120,1,-1,33,99,100,65};
        for(int i=0;i<7;i++){
            System.out.printf("\n %d ",array[i]);
        }
        System.out.printf("\n-----------------------------------------\n");

        New_Sort<Integer> sort = new New_Sort<Integer>();
        sort.sort(array);

        for(int i=0;i<7;i++){
            System.out.printf("\n %d ",array[i]);
        }
        System.out.printf("\n");
    }

    public static void testMergeSort2(int size){
        System.out.printf("\n MERGE_SORT");
        Integer[] array = new Integer[size];
        Random rand = new Random();     
        long start;
        long end;
        long elapsedTime;
        long average[] = new long[100];

        for(int i=0;i<size;i++){
            array[i] = rand.nextInt(size*size);
        }


        MergeSort<Integer> sort = new MergeSort<Integer>();

        //100 tane array
        for(int j=0;j<100;j++){

            for(int i=0;i<size;i++){
                array[i] = rand.nextInt(size);
            }


            start = System.nanoTime();
                sort.sort(array);
            end = System.nanoTime();
            elapsedTime = end - start;
            average[j] = elapsedTime;
            

        }


        

        int toplam = 0;
        for(int i=0;i<100;i++) toplam = (int) average[i];
        System.out.printf("\n Size : %d  ||| Average time : %d\n",size, toplam/100);
    }
    
    public static void testnew_sort2(int size){
        System.out.printf("\n NEW_SORT");
        Integer[] array = new Integer[size];
        Random rand = new Random();
        int random_int;        
        long start;
        long end;
        long elapsedTime;
        long average[] = new long[100];

        for(int i=0;i<size;i++){
            array[i] = rand.nextInt(size*size);
        }


        New_Sort<Integer> sort = new New_Sort<Integer>();

        //100 tane array
        for(int j=0;j<100;j++){

            for(int i=0;i<size;i++){
                array[i] = rand.nextInt(size);
            }


            start = System.nanoTime();
                sort.sort(array);
            end = System.nanoTime();
            elapsedTime = end - start;
            average[j] = elapsedTime;
            

        }


        

        int toplam = 0;
        for(int i=0;i<100;i++) toplam = (int) average[i];
        System.out.printf("\n Size : %d  ||| Average time : %d\n",size, toplam/100);
    }
    
    public static void testQuickSort2(int size){
        System.out.printf("\n QUICK_SORT");
        Integer[] array = new Integer[size];
        Random rand = new Random();     
        long start;
        long end;
        long elapsedTime;
        long average[] = new long[100];

        for(int i=0;i<size;i++){
            array[i] = rand.nextInt(size*size);
        }


        QuickSort<Integer> sort = new QuickSort<Integer>();

        //100 tane array
        for(int j=0;j<100;j++){

            for(int i=0;i<size;i++){
                array[i] = rand.nextInt(size);
            }


            start = System.nanoTime();
                sort.sort(array);
            end = System.nanoTime();
            elapsedTime = end - start;
            average[j] = elapsedTime;
            

        }


        int toplam = 0;
        for(int i=0;i<100;i++) toplam = (int) average[i];
        System.out.printf("\n Size : %d  ||| Average time : %d\n",size, toplam/100);
    }

    
    public static void main(String[] args){
        System.out.printf("\n\n\n=============================================================================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n======================================= KWHashMap ===========================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n=============================================================================================\n\n");
        
        testKWHashMap2();
        testKWHashMap(100);
        testKWHashMap(1000);
        testKWHashMap(10000);

        System.out.printf("\n\n\n\n=============================================================================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n======================================= CombinationMap ======================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n=============================================================================================");

        testCombination2();
        testCombination(100);
        testCombination(1000);
        testCombination(10000);
        

        System.out.printf("\n\n\n\n=============================================================================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n======================================= MERGE_SORT ======================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n=============================================================================================");
        testMergeSort();
        testMergeSort2(100);
        testMergeSort2(1000);
        testMergeSort2(10000);
        
        System.out.printf("\n\n\n\n=============================================================================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n======================================= QUICK_SORT ======================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n=============================================================================================");
        testQuickSort();
        testQuickSort2(100);
        testQuickSort2(1000);
        testQuickSort2(10000);


        System.out.printf("\n\n\n\n=============================================================================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n======================================= NEW_SORT ======================================");
        System.out.printf("\n=============================================================================================");
        System.out.printf("\n=============================================================================================");
        testnew_sort();
        testnew_sort2(100);
        testnew_sort2(1000);
        testnew_sort2(10000);
        
        
    }   



}
