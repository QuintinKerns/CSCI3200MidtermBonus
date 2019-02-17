
public class Problem2 {
	public static void main(String[] args){
		MyDoubleLinkedList<Integer> list;
		int[] testCase = new int[]{1, 2, 3, 4, 5, 6};
		int[] reversals = new int[]{3,2,6};
		for (int i : reversals) {
			list = new MyDoubleLinkedList<Integer>();
			System.out.println("Test Case " + i + ":");
			for (int j : testCase) list.add(j);
			list.reverseSegments(i);
			list.printList();
		}
	}
}
