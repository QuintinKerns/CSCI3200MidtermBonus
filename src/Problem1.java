
public class Problem1 {
	public static void main(String args[])  
    { 
		// Tree 1
		int[] tree1nums = new int[] {4, 7, 2, 3, 8, 1, 6, 9, 10, 0};
        BinarySearchTree tree1 = new BinarySearchTree(); 
        
        // Insert identical values
        for (Integer i : tree1nums) {
        	tree1.insert(i);
        }
        System.out.println("Tree 1:");
        tree1.printTree();
        
        // Tree 2
//        int[] tree2nums = new int[] {2, 1, 3}; // Result is exists inside tree 1
        int[] tree2nums = new int[] {3, 1, 2}; // Result is not exist inside tree 1
        BinarySearchTree tree2 = new BinarySearchTree();
        
        // Insert identical values
        for (Integer i : tree2nums) {
        	tree2.insert(i);
        }
        System.out.println("     -----------     \nTree 2:");
        tree2.printTree();
        System.out.println("     -----------     ");
   
        if (tree1.contains(tree2)) 
            System.out.println("Tree 2 is subtree of Tree 1 "); 
        else
            System.out.println("Tree 2 is not a subtree of Tree 1");
        
        final long opCount = tree1.opCounter + tree2.opCounter;
        System.out.println("Operation Count: " + opCount + "\nBig O: 2N");
    } 
}
