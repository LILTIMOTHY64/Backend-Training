package exercises;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayListIntro {
	
public static void main(String[] args) {
	
	ArrayList arrayList = new ArrayList();
	
	// Add elements to the arrayList
	arrayList.add("A");
	arrayList.add("B");
	arrayList.add("C");
	arrayList.add("D");
	
	System.out.println("Elements added to the ArrayList:");
	System.out.println(arrayList);
	
	// Remove elements from the arrayList
	System.out.println("Element at index 1 removed from the ArrayList:");
	arrayList.remove(1);
	System.out.println(arrayList);
	
	// Iterate with the help of Iterator
	Iterator iterator = arrayList.iterator();
	System.out.print("1.Iterator: ");
	while (iterator.hasNext()) {
		Object e = iterator.next();
		System.out.print(e + " ");
	}
	System.out.println();
	
	// Iterate with list iterator in Forward Direction
	ListIterator listIterator = arrayList.listIterator();
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
