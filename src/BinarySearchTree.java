
public class BinarySearchTree<E extends Comparable<? super E>> {
	private Node root;
	public long opCounter = 0;

	public BinarySearchTree()
	{
		root = null;
	}
	
	public boolean contains(BinarySearchTree<E> subtree) { // +2
		opCounter++; // Run method
		opCounter++; // Return statement
		return this.isSubtree(root, subtree.root);
	}
	
	private boolean isSubtree(Node n1, Node n2) { // 2N + 5
		opCounter++; // Run method
		opCounter++; // Comparison
		if (n1 == null) {
			opCounter++; // Return statement
			return true;
		}
		opCounter++; // Comparison
		if (n2 == null) {
			opCounter++; // Return statement
			return false;
		}
		
		opCounter++; // Run method
		opCounter++; // Comparison
		if (areEqual(n1, n2)) {
			opCounter++; // Return statement
			return true;
		}
		
		opCounter++; // Comparison
		opCounter++; // Return statement
		return isSubtree(n1.left, n2) || isSubtree(n1.right,n2); // 2N; 1 N for each recursive call
	}
	
	private boolean areEqual(Node root1, Node root2) { // N		
		// Return if we hit a base
		
		opCounter++; // Comparison
		opCounter++; // Comparison
		if (root1 == null && root2 == null) {
			opCounter++; // Return statement
			return true;
		}
		opCounter++; // Comparison
		if (root1 == null || root2 == null) {
			opCounter++; // Return statement
			return false;
		}
		
		// Recursive statement
		opCounter++; // Run method
		opCounter++; // Run method
		opCounter++; // Comparison ==
		opCounter++; // Comparison &&
		opCounter++; // Comparison &&
		opCounter++; // Return statement
		return (root1.data == root2.data
				&& areEqual(root1.left, root2.left)
				&& areEqual(root1.right, root2.right));
	}

	public void printTree()
	{
		printTree(root);
	}
	private void printTree(Node current)
	{
		if(current != null)
		{
			String content = "Current:"+current.data.toString();
			if(current.left != null)
			{
				content += "; Left side:"+current.left.data.toString();
			}
			if(current.right != null)
			{
				content += "; Right side:"+current.right.data.toString();
			}
			System.out.println(content);
			printTree(current.left);
			printTree(current.right);

		}
	}
	public void printInOrder()
	{
		System.out.print("In order:");
		printInOrder(root);
		System.out.println();
	}
	private void printInOrder(Node current)
	{
		if(current != null)
		{
			printInOrder(current.left);
			System.out.print(current.data.toString()+",");
			printInOrder(current.right);
		}
	}
	public boolean contains(E val)
	{
		Node result = findNode(val, root);

		if(result != null)
			return true;
		else
			return false;
	}
	private Node findNode(E val, Node current)
	{
		//base cases
		if(current == null)
			return null;
		if(current.data.equals(val))
			return current;

		//recursive cases
		int result = current.data.compareTo(val);
		if(result < 0)
			return findNode(val, current.right);
		else
			return findNode(val, current.left);
	}
	public E findMin()
	{
		Node result = findMin(root);
		if(result == null)
			return null;
		else
			return result.data;
	}
	private Node findMin(Node current)
	{
		while(current.left != null)
		{
			current = current.left;
		}
		return current;
	}
	public E findMax()
	{
		Node current = root;
		while(current.right != null)
		{
			current = current.right;
		}
		return current.data;
	}
	public void insert(E val)
	{
		root = insertHelper(val, root);
	}
	public Node insertHelper(E val, Node current)
	{
		/* for showing steps to insert a given value
		if(val.equals(9) && current != null)
		{
			System.out.println(current.data);
		}
		*/

		if(current == null)
		{
			return new Node(val);
		}
		int result = current.data.compareTo(val);
		if(result < 0)
		{
			current.right = insertHelper(val, current.right);
		}
		else if(result > 0)
		{
			current.left = insertHelper(val, current.left);
		}
		else//update
		{
			current.data = val;
		}
		return current;
	}
	public void remove(E val)
	{
		root = removeHelper(val, root);
	}
	private Node removeHelper(E val, Node current)
	{
		if(current.data.equals(val))
		{
			if(current.left == null && current.right == null)//no children
			{
				return null;
			}
			else if(current.left != null && current.right != null)//two children
			{
				Node result = findMin(current.right);
				result.right = removeHelper(result.data, current.right);
				result.left = current.left;
				return result;
			}
			else//one child
			{
				return (current.left != null)? current.left : current.right;
			}
		}
		int result = current.data.compareTo(val);
		if(result < 0)
		{
			current.right = removeHelper(val, current.right);
		}
		else if(result > 0)
		{
			current.left = removeHelper(val, current.left);
		}
		return current;
	}


	private class Node
	{
		E data;
		Node left, right;
		public Node(E d)
		{
			data = d;
			left = null;
			right = null;
		}
	}

}
