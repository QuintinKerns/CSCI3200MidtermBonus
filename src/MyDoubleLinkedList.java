import java.util.ArrayList;
import java.util.Collections;


public class MyDoubleLinkedList<E> {
	private Node start, end;
	private int currentCount;
	public MyDoubleLinkedList()
	{
		start = null;
		end = null;
		currentCount = 0;
	}
	
	//
	public void reverseSegments(int setSize){
		/*
		 * N: nodeArray length
		 * S: setSize
		 * Time Complexity (Worst Case): -2S+12N+((4N^2N)/S)+20
		 * Big(O): N^2
		 */
		long opCnt = 0;
		// Special Case: setSize = 0 or 1
		if (setSize == 0 || setSize == 1) {
			opCnt++;
			return; // 1: Comparison
		}
		
		// Turn double linked list into an ArrayList // 4N + 2
		ArrayList<E> nodeArray = new ArrayList<E>(currentCount); // 1: Assignment
		opCnt++;
		for (int i = 0; i < currentCount; i++){ // 5N + 1
			nodeArray.add(start.value); // N: Run method
			start = start.next; // N: Assignment
			opCnt = 5 * opCnt + 1;
		}
		start = end = null; // 2: Assignment
		opCnt += 2;
		System.out.println("nodeArray: " + nodeArray);
		
		// Special Case: setSize is not a factor of nodeArray length.
		ArrayList<E> tail = null; // 1: Assignment
		int remainder = nodeArray.size() % setSize; // 2: Assignment and Math
		opCnt += 3;
		if (remainder != 0) { // 1 + 3 + N - setSize + N - (N - setSize)
			tail = new ArrayList<E>(nodeArray.subList(nodeArray.size() - remainder, nodeArray.size())); // 3
			opCnt += 3;
			Collections.reverse(tail); // N - setSize: Worst Case
			opCnt += tail.size();
			nodeArray = new ArrayList<E>(nodeArray.subList(0, nodeArray.size() - remainder)); // 1 + (N - (N - setSize))
			opCnt += 2;
		}
		opCnt++;
		System.out.println("After Special Case: ");
		System.out.println("nodeArray: " + nodeArray);
		System.out.println("tail: " + tail);
		
		int nodeArrayMinusOne = nodeArray.size() - 1; // 2 Assignment and Math
		opCnt += 2;
		
		// Chop up nodeArray into setSize long sections, reverse those small sections, and stitch it back to get
		for (int i = 0; i < nodeArrayMinusOne; i += setSize) { // 4N^2N / setSize + 1
			ArrayList<E> reverseMe = new ArrayList<E>(nodeArray.subList(i, i + setSize)); // 3
			opCnt += 3;
			Collections.reverse(reverseMe); // N / setSize
			opCnt += reverseMe.size();
			System.out.println(i + ": " + reverseMe);
			for (E obj : reverseMe) { // 2N / setSize
				this.add(obj); // N
				opCnt += 4;
			}
			opCnt++;
		}
		opCnt++;
		if (remainder != 0) // 2(N - setSize) + 2: Worst Case
			for (E obj : tail) { // N - setSize + 1
				this.add(obj); // N - setSize
				opCnt += 4;
		}
		opCnt++;	
		opCnt++;
		
		System.out.println("Operation Count: " + opCnt);
	}
	
	public void printList()
	{
		Node current = start;
		while(current != null)
		{
			System.out.println(current.value);
			current = current.next;
		}
	}
	public void printListRev()
	{
		Node current = end;
		while(current != null)
		{
			System.out.println(current.value);
			current = current.prev;
		}
	}
	public void add(E val)//O(1)
	{
		Node newItem = new Node(val);

		//if list is empty
		if(start == null)
		{
			start = newItem;
			end = start;//only item in list means end = start
			currentCount++;
		}
		//if list has items
		else
		{
			end.next = newItem;//end -> newItem
			newItem.prev = end;//end <- newItem
			end = newItem;
			currentCount++;
		}
	}
	public void insert(E val, int index)
	{
		if(index < 0)
		{
			index = 0;
		}
		if(index >= currentCount)//insert at end is same as add
		{
			this.add(val);
		}
		else
		{
			Node newItem = new Node(val);
			if(index == 0)//special case, changing start variable
			{
				newItem.next = start;//current list comes after new item
				start.prev = newItem;
				start = newItem;//new item is first in list
			}
			else
			{
				Node current = start;
				for(int i = 1; i < index; i++)
				{
					current = current.next;
				}
				//System.out.println(current.value);

				//current == before at this point
				//current/before <-> index <-> after
				//1 <-> 2 <-> 3
				//goal
				//before <-> new <-> index <-> after
				//1 <-> new <-> 2 <-> 3
				newItem.next = current.next;//new -> index
				current.next.prev = newItem;//new <- index
				current.next = newItem;//before -> new
				newItem.prev = current;//before <- new
			}
			currentCount++;
		}
	}
	public void delete(int index)
	{
		if(index >= 0 && index < currentCount)
		{
			if(index==0)//deal with special case
			{
				start = start.next;
				if(start != null)//in case list just became empty
				{
					start.prev = null;
				}
				else
				{
					end = null;
				}
			}
			else if(index == currentCount -1)
			{
				end = end.prev;
				if(end != null)
				{
					end.next = null;
				}
				else
				{
					start = null;
				}
			}
			else
			{
				Node current = start;
				for(int i = 1; i < index; i++)//find item before the one being deleted
				{
					current = current.next;
				}
				current.next = current.next.next;
				//current <-> deleteMe <-> restoflist
				//current -> restoflist
				//current <- deleteMe <- restoflist
				if(current.next != null)//incase we deleted the last item
				{
					current.next.prev = current;//current <- restoflist
				}
			}
			currentCount--;
		}
	}
	public E get(int index)//O(N)//could be improved to O(N/2) by starting from start/end depending on index
	{
		if(index >= 0 && index < currentCount)
		{
			Node current = start;
			for(int i = 0; i < index; i++)
			{
				current = current.next;
			}
			//current = node at the index
			return current.value;
		}
		else
		{
			return null;
		}
	}

	private class Node
	{
		E value;
		Node next, prev;
		public Node(E v)
		{
			value = v;
			next = null;//no node after this one
			prev = null;
		}
	}
}
