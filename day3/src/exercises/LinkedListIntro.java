package exercises;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

public class LinkedListIntro {
	
public static void main(String[] args) {
	
	LinkedList linkedList = new LinkedList();
	
	// Add elements to the arrayList
	linkedList.add("1");
	linkedList.add("2");
	linkedList.add("3");
	linkedList.add("4");
	
	System.out.println("Elements added to the LinkedList:");
	System.out.println(linkedList);
	
	// Remove elements from the arrayList
	System.out.println("Element at index 2 removed from the LinkedList:");
	linkedList.remove(2);
	
	System.out.println(linkedList);
	
	// Iterate with the help of Iterator
	Iterator iterator = linkedList.iterator();
	System.out.print("1.Iterator: ");
	while (iterator.hasNext()) {
		Object e = iterator.next();
		System.out.print(e + " ");
	}
	System.out.println();
	
	// Iterate with list iterator in Forward Direction
	ListIterator listIterator = linkedList.listIterator();
	System.out.print("2.ListIterator forward: ");
	while (listIterator.hasNext()) {
		Object e = listIterator.next();
		System.out.print(e + " ");
	}
	System.out.println();
	
	// Iterate with list iterator in Backward Direction
	System.out.print("3.ListIterator backward: ");
	while (listIterator.hasPrevious()) {
		Object e = listIterator.previous();
		System.out.print(e + " ");
	}
	System.out.println();
}

}
