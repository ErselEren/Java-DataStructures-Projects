import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Extends the BinarySearchTree by adding the rotate operations.
 * Rotation will change the balance of a search tree while preserving the search tree property.
 * Used as a common base for self-balancing trees.
 * @author Jacob/ Koffman & Wolfgang
 *
 * @param <E> The type of data stored. Must be a Comparable type
 */
@SuppressWarnings("serial")
public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E> {
	
	//Methods
	
	/**
	 * Method to perform a right rotation
	 * pre: root is the root of a binary search tree
	 * post: root.left is the root of a binary search tree
	 * 		 root.left.left is raised one level
	 * 		 root.left.right does not change levels
	 * 		 root.right is lowered one level
	 * 		 the new root is returned
	 * @param root The root of the binary tree to be rotated
	 * @return The new root of the rotated tree
	 */
	public Node<E> rotateLeft(Node<E> root){
		Node<E> temp = root.left;
		root.left = temp.right;
		temp.right = root;
		return temp;
	}
	
	/**
	 * Method to perform a left rotation
	 * pre: root is the root of a binary search tree
	 * post: root.right is the root of a binary search tree
	 * 		 root.right.right is raised one level
	 * 		 root.right.left does not change levels
	 * 		 root.left is lowered one level
	 * 		 the new root is returned
	 * @param root The root of the binary tree to be rotated
	 * @return The new root of the rotated tree
	 */
	public Node<E> rotateRight(Node<E> root){
		Node<E> temp = root.right;
		root.right = temp.left;
		temp.left = root;
		return temp;
	}

	 //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child  
	 public Node<E> doubleWithLeftChild(Node<E> node3)  
	 {  
		 node3.left = rotateRight( node3.left );  
		 return rotateLeft( node3 );  
	 }

	 //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child  
	 public Node<E> doubleWithRightChild(Node<E> node1)  
	 {  
		 node1.right = rotateLeft( node1.right );  
		 return rotateRight( node1 );  
	 }    


	/*Function to find the height(depth) of the tree*/
	private int height( Node<E> root){
		if(root == null ) return 0;

		//Initialising a variable to count the
		//height of tree
		int depth = 0;
	
		
		Queue<Node<E>> q=new LinkedList<Node<E>>();
		
		//Pushing first level element along with null
		q.add(root);
		q.add(null);
		while(!q.isEmpty()){
			Node<E> temp = q.peek();
			q.remove();
		
			//When null encountered, increment the value
			if(temp == null){
				depth++;
			}
			
			//If null not encountered, keep moving
			if(temp != null){
				if(temp.left!=null){
					q.add(temp.left);
				}
				if(temp.right!=null){
					q.add(temp.right);
				}
			}
		
			//If queue still have elements left,
			//push null again to the queue.
			else if(!q.isEmpty()){
				q.add(null);
			}
		}
		return depth;
	}


	public void convertToAVL(){
		convert(root);
	}


	/**
	 * Converting tree to AVL by rotating in preorder traversal
	 * @param node
	 */
	private void convert(Node<E> node) {
		if (node == null)
		  return;
		
		int rotateType = getRotateType(node);
		
		if(rotateType == 0){ //if current node is balanced, first check left then right
			if(node.left != null){
				rotateType = getRotateType(node.left);	
				if(rotateType == 1) node.left = rotateLeft(node.left);
				else if(rotateType == 2) node.left = doubleWithLeftChild(node.left);
				else if(rotateType == 3) node.left = rotateRight(node.left);
				else if(rotateType == 4) node.left = doubleWithRightChild(node.left);
			}

			if(node.right != null){
				rotateType = getRotateType(node.right);	
				if(rotateType == 1) node.right = rotateLeft(node.right);
				else if(rotateType == 2) node.right = doubleWithLeftChild(node.right);
				else if(rotateType == 3) node.right = rotateRight(node.right);
				else if(rotateType == 4) node.right = doubleWithRightChild(node.right);
			}
			
		}
		
		else{ // current node is not balanced, if it is not balanced it must be root 
			if(node == root){
				if(rotateType == 1) root = rotateLeft(node);
				else if(rotateType == 2) root = doubleWithLeftChild(node);
				else if(rotateType == 3) root = rotateRight(node);
				else if(rotateType == 4) root = doubleWithRightChild(node);

				convert(root);
			}
		}
		
		
		// Traverse left
		convert(node.left);
		// Traverse right
		convert(node.right);
	}

	/**
	 * Returns Rotate Type
	 * 1-LL, 2-LR, 3-RR, 4-RL, 0
	 * @param node
	 * @return Integer rotate type
	 */
	public int getRotateType(Node<E> node){
		Node<E> leftChild = node.left;
		Node<E> rightChild = node.right;
		int leftHeight = height(leftChild);
		int rightHeight = height(rightChild);
		int leftleftHeight, leftrightHeight, rightleftHeight, rightrightHeight;

		if(leftHeight - rightHeight > 1){ //Left side is heavy
			
			leftleftHeight = height(node.left.left);
			leftrightHeight = height(node.left.right);
			
			if(leftleftHeight - leftrightHeight > 0) //LL
				return 1;
			else if(leftrightHeight - leftleftHeight > 0) //LR
				return 2;
		}
		else if(rightHeight - leftHeight > 1){ //Right side is heavy
			rightleftHeight = height(node.right.left);
			rightrightHeight = height(node.right.right);
			
			if(rightrightHeight - rightleftHeight > 0) //RR
				return 3;
			else if(rightleftHeight - rightrightHeight > 0) //RL
				return 4;
		}

		return 0;
	}


	/**
	 * Prints tree
	 * @param root
	 * @return
	 */
	private String traversePreOrder(Node<E> root) {

		if (root == null) {
			return "";
		}
	
		StringBuilder sb = new StringBuilder();
		sb.append(root.data);
	
		String pointerRight = "└──";
		String pointerLeft = (root.right != null) ? "├──" : "└──";
	
		traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
		traverseNodes(sb, "", pointerRight, root.right, false);
	
		return sb.toString();
	}

	/**
	 * Helper method
	 * @param sb
	 * @param padding
	 * @param pointer
	 * @param node
	 * @param hasRightSibling
	 */
	private void traverseNodes(StringBuilder sb, String padding, String pointer, Node<E> node, boolean hasRightSibling) {
		if (node != null) {
			sb.append("\n");
			sb.append(padding);
			sb.append(pointer);
			sb.append(node.data);

			StringBuilder paddingBuilder = new StringBuilder(padding);
			if (hasRightSibling) {
				paddingBuilder.append("│  ");
			} else {
				paddingBuilder.append("   ");
			}

			String paddingForBoth = paddingBuilder.toString();
			String pointerRight = "└──";
			String pointerLeft = (node.right != null) ? "├──" : "└──";

			traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
			traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);
		}
	}

	/**
	 * Prints tree
	 * @param os Output stream
	 */
	public void print(PrintStream os) {
		os.print(traversePreOrder(root));
	}

}
