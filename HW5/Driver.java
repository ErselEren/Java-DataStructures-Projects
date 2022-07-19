public class Driver {
    
    private static void testHeap(){
        // ------------------ Test the BinaryHeap class ------------------
        System.out.printf("\n=====================================================================");
        System.out.printf("\n============================ Heap class =============================");
        System.out.printf("\n=====================================================================");

        System.out.printf("\nCreating new heap and adding elements to it \n");
        MyHeap<Integer> heap1 = new MyHeap<>();
        heap1.offer(80);
        heap1.offer(120);
        heap1.offer(33);
        System.out.println(heap1);

        
        System.out.printf("\nAdding an element that will be the smallest element of tree\n");
        heap1.offer(10);
        System.out.println(heap1);

        System.out.printf("\nAdding other elements.\n");
        heap1.offer(51);
        heap1.offer(0);
        heap1.offer(49);
        heap1.offer(190);
        System.out.println(heap1);

        System.out.printf("\nPolling root.\n");
        System.out.println("Element  which is polled : " + heap1.poll() + "\n");
        System.out.printf("\n--------------- HEAP 1 ---------------\n");
        System.out.println(heap1);

        System.out.printf("\nCreating second heap and adding elements to it.\n");
        MyHeap<Integer> heap2 = new MyHeap<>();
        heap2.offer(235);
        heap2.offer(112);
        heap2.offer(319);
        heap2.offer(13);
        heap2.offer(77);
        heap2.offer(88);
        heap2.offer(139);
        System.out.printf("\n--------------- HEAP 2---------------\n");
        System.out.println(heap2);

        
        MyHeap<Integer> merged = heap1.merge(heap2);
        System.out.printf("\n----------Merge two heap-----------\n");
        System.out.println(merged);

        //there will be 14 elements in merged tree, poll is called 16 times
        System.out.println("------- Polling elements from merged tree until end of the tree -------\n");
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        System.out.println("Removed Element: " + merged.poll());
        
        
        System.out.println(merged);
        

        System.out.printf("\nHeap1 after merging, it is protected\n");
        System.out.println(heap1);
        System.out.printf("\nHeap2 after merging, it is protected\n");
        System.out.println(heap2);

        System.out.printf("\nHeap1 after removing 3 element\n");
        heap1.remove();
        heap1.remove();
        heap1.remove();
        System.out.println(heap1);

        System.out.printf("\nHeap2 after removing 3 element\n");
        heap2.remove();
        heap2.remove();
        heap2.remove();
        System.out.println(heap2);

        System.out.println("\n==========================End Of The Test========================\n");
    }

    private static void testSearchTree(){
        // ------------------ Test the BinarySearchTree class ------------------
        System.out.printf("\n=================================================================");
        System.out.printf("\n================== ArrayBasedBinarySearchTree ===================");
        System.out.printf("\n=================================================================\n");
        

        System.out.printf("\nCreating empty tree and add 3 element.\n");
        ArrayBasedBinarySearchTree<Integer> BST1 = new ArrayBasedBinarySearchTree<>();
        BST1.add(107);
        BST1.add(53);
        BST1.add(148);
        System.out.println(BST1);

        System.out.printf("\nAdding same data(107) to the tree.\n");
        System.out.println("Adding '107' is successful : " + BST1.add(107));


        System.out.printf("\n--------------Delete method-------------");
        System.out.printf("\nRemoving '148' element with no child.\n");
        BST1.delete(148);
        System.out.println(BST1);

    
        System.out.printf("\nAdding another elements to tree.\n");
        BST1.add(-1);
        BST1.add(99);
        BST1.add(78);
        BST1.add(65);
        BST1.add(85);
        BST1.add(100);
        BST1.add(173);
        System.out.println(BST1);

        
        System.out.println("\n---------- find method -----------");
        System.out.println("Finding 99 in tree : " + BST1.find(99));
        System.out.println("Finding 8765 in tree : " + BST1.find(8765));
        

        System.out.printf("\n----------- Contains method ------------");
        if(BST1.contains(99) == true)
            System.out.printf("\n 99 exists in tree");
        else System.out.printf("\n 99 does not exists in tree\n");

        if(BST1.contains(8765) == true)
            System.out.printf("\n 8765 exists in tree");
        else System.out.printf("\n 8765 does not exists in tree\n");


        System.out.printf("\n------ Remove element(99) that has two child.\n");
        System.out.println("Deleted Number: " + BST1.delete(99) + "\n");
        System.out.println(BST1);

        
        System.out.printf("\n---------- Remove the root ------------\n");
        System.out.println("Deleted Number: " + BST1.delete(107) + "\n");
        System.out.println(BST1);
        
    
        System.out.printf("\n=================================================================");
        System.out.printf("\n============================END OF BST===========================");
        System.out.printf("\n=================================================================\n");
    }
    
    public static void main(String[] args){
        
        testSearchTree();
        testHeap();

    }  
}
