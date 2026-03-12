package exercises;

import java.util.TreeSet;
import java.util.Iterator;

public class TreeSetIntro {
	
	public static void main(String[] args) {
		
		// create a TreeSet
		TreeSet set = new TreeSet();

		System.out.println("1.Initial size of TreeSet before adding elements: "	+ set.size());
		System.out.println("2.TreeSet is empty before adding values: "	+ set.isEmpty());
		System.out.println("3.Contents of TreeSet before adding values: " + set);
		

		// adding elements or values to the TreeSet
		set.add("C");
		set.add("A");
		set.add("E");
		set.add("B");
		set.add("D");
		set.add("F");

		// cannot add duplicates, dissimilar and null values in TreeSet
	//	set.add("A");
	//	set.add(new Integer(10));
	//	set.add(null);

		System.out.println("4.Size of TreeSet after adding elements: " + set.size());
		System.out.println("5.TreeSet is empty after adding values: " + set.isEmpty());

		// display the contents of TreeSet
		System.out.println("6.Contents of TreeSet after adding values: " + set);

		// check whether an element is present or not
		boolean b1 = set.contains("E");
		System.out.println("7.Value E is present: " + b1);

		boolean b2 = set.contains("R");
		System.out.println("8.Value R is present: " + b2);

		// Remove an element from the TreeSet
		set.remove("B");

		System.out.println("9.Size of TreeSet after deletion: " + set.size());
		System.out.println("10.Contents of TreeSet after deletion: " + set);
		
		// Iterate through the treeSet using iterator
		Iterator iterator = set.iterator();
		
		System.out.print("11.Iterate through the TreeSet; ");
		while(iterator.hasNext()) {
			Object e = iterator.next();
			System.out.print(e + " ");
		}
		System.out.println();

		// delete all the elements in the TreeSet
		set.clear();
		System.out.println("12.Size of TreeSet after clearing: " + set.size());
		System.out.println("13.Contents of TreeSet after clearing: " + set);
	}
}
