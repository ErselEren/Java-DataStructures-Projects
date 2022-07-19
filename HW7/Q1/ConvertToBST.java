import java.util.Stack;

@SuppressWarnings("unchecked")
public class ConvertToBST<E extends Comparable<E> > {
    QuickSort sortInstance = new QuickSort<>();

    
    @SuppressWarnings("unchecked")
    public BinarySearchTree<E> CombineArrayAndBT(BinaryTree<E> BT, E[] array){
        Stack<E> stack = new Stack<E>();
        BinarySearchTree<E> BST = new BinarySearchTree<E>();
        BinaryTree.Node<E> newRoot = cloneBinaryTree(BT.root);

        sortInstance.sort(array); //sort array
        
        for(int i=array.length-1;i>=0;i--) stack.push(array[i]); //push array to stack
        
        BST.root = newRoot; 
        
        BST.inOrderTraversePassArray(BST.root, stack);
        
        return BST;
    }

    /**
     * Recursive function to clone a binary tree  
     * @param root
     * @return
    */
    public static <E> BinaryTree.Node<E> cloneBinaryTree(BinaryTree.Node<E> root)
    {
        // base case
        if (root == null){
            return null;
        }
 
        // create a new node with the same data as the root node
        BinaryTree.Node<E> root_copy = new BinaryTree.Node<E>(root.data);
 
        // clone the left and right subtree
        root_copy.left = cloneBinaryTree(root.left);
        root_copy.right = cloneBinaryTree(root.right);
 
        // return cloned root node
        return root_copy;
    }
}
