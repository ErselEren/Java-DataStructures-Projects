import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.BiConsumer;


public class BST<E extends Comparable<E>> implements Iterable<E> {

	/**
	 * Class to encapsulate a tree node.
	 * @param <E> type of data to store
	 */
	private static class Node<E> {

		/** The information stored in this node. */
		private E data;

		/** Reference to the left child. */
		private Node<E> right;

		/** Reference to the right child. */
		private Node<E> left;

		/**
		 * Number of nodes under this node + this node.
		 */
		private int size;

		/**
		 * Constructs a node with given data and no children.
		 * @param data the data to store in this node
		 */
		public Node(E data) {
			this.data = data;
			this.left = this.right = null;
			size = 1;
		}

		/**
		 * Replaces the right child with given one.
		 * @param right new right child
		 */
		public void setRight(Node<E> right) {
			this.right = right;
			int leftSize = this.left != null ? this.left.size : 0;
			int rightSize = this.right != null ? this.right.size : 0;
			size = 1 + leftSize + rightSize;
		}

		/**
		 * Replaces the left child with given one.
		 * @param left new left child
		 */
		public void setLeft(Node<E> left) {
			this.left = left;
			int rightSize = this.right != null ? this.right.size : 0;
			int leftSize = this.left != null ? this.left.size : 0;
			size = 1 + rightSize + leftSize;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	/** Reference to the root of the tree. */
	private Node<E> root;

	/**
	 * Stores a second return value from the recursive add method
	 * that indicates whether the item has been inserted.
	 */
	private boolean addReturn;

	/**
	 * Stores a second return value from the recursive delete and remove
	 * methods that reference the item that was stored in the tree.
	 */
	private E deleteReturn;

	/** Constructs an empty binary tree. */
	public BST() {
		root = null;
	}

	/**
	 * Constructs a binary tree with given node as the root.
	 * @param root root node
	 */
	private BST(Node<E> root) {
		this.root = root;
	}

	/**
	 * Constructs a binary tree with the given data at the root and the two given subtrees.
	 * @param data data in the root
	 * @param leftTree left subtree of the root
	 * @param rightTree right subtree of the root
	 */
	public BST(E data, BST<E> leftTree, BST<E> rightTree) {
		root = new Node<>(data);

		if (leftTree != null)
			root.setLeft(leftTree.root);

		if (rightTree != null)
			root.setRight(rightTree.root);
	}

	/**
	 * Returns the left subtree.
	 * @return the left subtree or null if either the root or the left subtree is null
	 */
	public BST<E> getLeftSubTree() {
		if (root != null && root.left != null)
			return new BST<>(root.left);
		else
			return null;
	}

	/**
	 * Returns the right subtree.
	 * @return the right subtree or null if either the root or the left subtree is null
	 */
	public BST<E> getRightSubTree() {
		if (root != null && root.right != null)
			return new BST<>(root.right);
		else
			return null;
	}

	/**
	 * Returns the data in the root.
	 * @return data in the root
	 */
	public E getData() {
		return root != null ? root.data : null;
	}

	/**
	 * Gives the number of nodes in the tree.
	 * @return size of the tree
	 */
	public int getSize() {
		return root != null ? root.size : 0;
	}

	/**
	 * Returns whether this tree is a leaf.
	 * @return true if the root has no children, false otherwise
	 */
	public boolean isLeaf() {
		return root == null || (root.left == null && root.right == null);
	}

	/**
	 * Finds and returns the data in the node that is equal to target.
	 * @param target target data to find
	 * @return a reference to the data in the node that is equal to target,
	 * 			if no such node is found, returns null
	 */
	public E find(E target) {
		return find(root, target);
	}

	/**
	 * Recursive find method.
	 * @param localRoot the local subtree's root
	 * @param target the object being sought
	 * @return the object, if found, otherwise null
	 */
	private E find(Node<E> localRoot, E target) {
		if (localRoot == null) return null;

		int compResult = target.compareTo(localRoot.data);
		if (compResult == 0) return localRoot.data;
		else if (compResult < 0) return find(localRoot.left, target);
		else return find(localRoot.right, target);
	}

	/**
	 * Inserts item where it belongs in the tree.
	 * @param item data to insert
	 * @return true if item is inserted, false if it is not (already in tree)
	 */
	public boolean add(E item) {
		root = add(root, item);
		return addReturn;
	}

	/**
	 * Recursive add method.
	 * <p/>
	 * post: The data field addReturn is set true if the item is
	 * added to the tree, false if the item is already in the tree.
	 * @param localRoot the local root of the subtree
	 * @param item the object to be inserted
	 * @return the new local root that now contains the inserted item
	 */
	private Node<E> add(Node<E> localRoot, E item) {
		if (localRoot == null) {
			addReturn = true;
			return new Node<>(item);
		}

		int compResult = item.compareTo(localRoot.data);
		if (compResult == 0) {
			addReturn = false;
			return localRoot;
		} else if (compResult < 0) {
			localRoot.setLeft(add(localRoot.left, item));
			return localRoot;
		} else {
			localRoot.setRight(add(localRoot.right, item));
			return localRoot;
		}
	}

	/**
	 * Removes target from tree and returns it.
	 * @param target target data to remove
	 * @return removed data, if found, null otherwise
	 */
	public E delete(E target) {
		root = delete(root, target);
		return deleteReturn;
	}

	/**
	 * Recursive delete method.
	 * <p/>
	 * post: The data field deleteReturn is equal to the
	 * deleted item as it was stored in the tree or null
	 * if the item was not found.
	 * @param localRoot the root of the current subtree
	 * @param item the item to be deleted
	 * @return the modified local root that does not contain the item
	 */
	private Node<E> delete(Node<E> localRoot, E item) {
		if (localRoot == null) {
			deleteReturn = null;
			return null;
		}

		int compResult = item.compareTo(localRoot.data);
		if (compResult < 0) {
			localRoot.setLeft(delete(localRoot.left, item));
			return localRoot;
		} else if (compResult > 0) {
			localRoot.setRight(delete(localRoot.right, item));
			return localRoot;
		} else {
			deleteReturn = localRoot.data;
			if (localRoot.left == null) return localRoot.right;
			else if (localRoot.right == null) return localRoot.left;
			else {
				if (localRoot.left.right == null) {
					localRoot.data = localRoot.left.data;
					localRoot.left = localRoot.left.left;
				} else {
					localRoot.data = findLargestChild(localRoot.left);
				}
				return localRoot;
			}
		}
	}

	/**
	 * Find the node that is the inorder predecessor and
	 * replace it with its left child (if any).
	 * @param parent the parent of possible inorder predecessor
	 * @return the data in the inorder predecessor
	 */
	private E findLargestChild(Node<E> parent) {
		if (parent.right.right == null) {
			E returnValue = parent.right.data;
			parent.setRight(parent.right.left);
			return returnValue;
		} else return findLargestChild(parent.right);
	}

	/**
	 * Performs a recursive preorder traversal on this
	 * tree and applies given consumer for all nodes.
	 * @param consumer object whose accept method specifies the action to be performed on each node
	 */
	public void preOrderTraversal(BiConsumer<E, Integer> consumer) {
		preOrderTraverse(root, 1, consumer);
	}

	/**
	 * Performs a preorder traversal.
	 * @param node the local root
	 * @param depth the depth for indentation
	 * @param consumer an object that instantiates the BiConsumer interface
	 */
	private void preOrderTraverse(Node<E> node, int depth, BiConsumer<E, Integer> consumer) {
		if (node == null) consumer.accept(null, depth);
		else {
			consumer.accept(node.data, depth);
			preOrderTraverse(node.left, depth + 1, consumer);
			preOrderTraverse(node.right, depth + 1, consumer);
		}
	}

	/**
	 * Returns an iterator that traverses inorder sequence.
	 * @return iterator object that points the smallest value.
	 */
	public Iterator<E> iterator() {
		return new InorderIter(root);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		preOrderTraversal((e, depth) -> {
			sb.append(" ".repeat(Math.max(0, depth - 1)));
			sb.append(e);
			sb.append("\n");
		});
		return sb.toString();
	}

	/**
	 * Inner class that provides ascending iteration through nodes.
	 */
	private class InorderIter implements Iterator<E> {

		/**
		 * To perform an inorder iteration, we need to find the leftmost leaf.
		 * To reach that node, we need to visit parent nodes.
		 * This field stores parent nodes to access them later.
		 */
		private final Stack<Node<E>> traversal;

		/**
		 * Constructs an iterator starting from given node.
		 * @param root starting node
		 */
		public InorderIter(Node<E> root) {
			traversal = new Stack<>();
			moveLeft(root);
		}

		/**
		 * Returns {@code true} if the iteration has more elements.
		 * (In other words, returns {@code true} if {@link #next} would
		 * return an element rather than throwing an exception.)
		 *
		 * @return {@code true} if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return !traversal.isEmpty();
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */
		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			Node<E> current = traversal.pop();
			if (current.right != null) moveLeft(current.right);
			return current.data;
		}

		/**
		 * Adds all nodes to the stack from given node until the leftmost node.
		 * @param current starting node
		 */
		private void moveLeft(Node<E> current) {
			while (current != null) {
				traversal.push(current);
				current = current.left;
			}
		}
	}
}
