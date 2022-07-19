import java.io.PrintStream;

public class Driver<E> {
    

    @SuppressWarnings("unchecked")
    public static void main(String []args){
        
        PrintStream output = new PrintStream(System.out);
        ConvertToBST q1 = new ConvertToBST<>();
        Integer [] myArray1 = new Integer[6];
        BinaryTree<Integer> BT =  new BinaryTree<Integer>();

        myArray1[0] = 30;
        myArray1[1] = 4;
        myArray1[2] = 100;
        myArray1[3] = 72;
        myArray1[4] = 14;
        myArray1[5] = 10;
        
        
        //Randomly adding datas. Values are not important
        BT.root = new BinaryTree.Node<Integer>(7);
        BT.root.right = new BinaryTree.Node<Integer>(3);
        BT.root.left = new BinaryTree.Node<Integer>(10);
        BT.root.left.left = new BinaryTree.Node<Integer>(25);
        BT.root.left.right = new BinaryTree.Node<Integer>(30);
        BT.root.left.left.left = new BinaryTree.Node<Integer>(40);

        System.out.printf("\n BinaryTree \n");
        BT.print(output);
        
        BinarySearchTree<Integer> BST = q1.CombineArrayAndBT(BT,myArray1);
        
        System.out.printf("\n\n Array \n");
        for(int i=0;i<myArray1.length;i++) System.out.printf(" %d ",myArray1[i]);
        
        System.out.printf("\n\n BinarySearchTree \n");
        BST.print(output);

        System.out.printf("\n\n");

        
        

    }
}
