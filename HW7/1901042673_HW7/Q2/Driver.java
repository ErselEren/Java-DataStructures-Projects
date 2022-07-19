import java.io.PrintStream;

public class Driver {
    public static void main(String []args){
       
        BinarySearchTreeWithRotate<Integer> bst = new BinarySearchTreeWithRotate<Integer>();
        BinarySearchTreeWithRotate<Integer> bst2 = new BinarySearchTreeWithRotate<Integer>();
        BinarySearchTreeWithRotate<Integer> bst3 = new BinarySearchTreeWithRotate<Integer>();

        bst2.add( 6);
        bst2.add( 5);
        bst2.add( 4);
        bst2.add( 3);
        bst2.add( 2);
        bst2.add( 1);
        

        PrintStream output = new PrintStream(System.out);
        System.out.printf("\nBEFORE CONVERTING\n");
        bst2.print(output);

        bst2.convertToAVL();

        System.out.printf("\n\n");
        
        System.out.printf("\nAFTER CONVERTING\n");
        bst2.print(output);

        System.out.printf("\n--------------------------------------------------------------\n");

        bst.add( 100);
        bst.add( 150);
        bst.add( 50);
        bst.add( 10);
        bst.add( 5);
        bst.add( 60);
        bst.add( 20);
        bst.add( 3);
        bst.add( 6);
        bst.add( 25);
        bst.add( 30);
        bst.add( 2);
        bst.add( 0);
        bst.add( 150);
        bst.add( 120);
        bst.add( 110);
        bst.add( 130);
        bst.add( 250);
        
    
        System.out.printf("\nBEFORE CONVERTING\n");
        bst.print(output);
        bst.convertToAVL();
        System.out.printf("\n\n");
        System.out.printf("\nAFTER CONVERTING\n");
        bst.print(output);

        System.out.printf("\n\n");

        System.out.printf("\n--------------------------------------------------------------\n");
        
        bst3.add( 100);
        bst3.add( 200);
        bst3.add( 300);
        bst3.add( 150);
        bst3.add( 125);
        bst3.add( 110);
        bst3.add( 105);
        bst3.add( 400);

        System.out.printf("\nBEFORE CONVERTING\n");
        bst3.print(output);
        bst3.convertToAVL();
        System.out.printf("\n\n");
        System.out.printf("\nAFTER CONVERTING\n");
        bst3.print(output);

        System.out.printf("\n\n");


    }
}

