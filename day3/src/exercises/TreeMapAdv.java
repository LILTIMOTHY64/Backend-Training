package exercises;

import java.util.TreeMap;

public class TreeMapAdv {
	public static void main(String[] args) {
		// Create a TreeMap
		TreeMap customerMap = new TreeMap();
		System.out.println("1.TreeMap is empty before adding vales: " + customerMap.isEmpty());
		System.out.println("2.Size of TreeMap before adding values: " + customerMap.size());

		// Add key/value to the TreeMap
		customerMap.put("Kumar", new Double(11.11));
		customerMap.put("Ravi", new Double(22.22));
		customerMap.put("Amogh", new Double(33.33));
		customerMap.put("Vinay", new Double(44.44));
		customerMap.put("Balaji", new Double(55.55));

		System.out.println("3.TreeMap is empty after adding values: " + customerMap.isEmpty());
		System.out.println("4.Size of TreeMap after adding values: " + customerMap.size());

		// using same key will update or replace the value
		customerMap.put("Ravi", new Double(99.99));

		// can add different type of value in TreeMap
		customerMap.put("Praveen", "PRAVEEN");

		// can put null value in TreeMap
		customerMap.put("Hello", null);

		// cannot add null key in TreeMap
		// tm.put(null, "CLUSTER");

		// cannot add different type of keys in TreeMap
		// tm.put(new Integer(10),new Integer(20));

		System.out.println(customerMap);

		// getting a value using a key
		Object e = customerMap.get("Kumar");
		System.out.println("5.Value of Kumar is: " + e);

		Object e1 = customerMap.get("Ravi");
		System.out.println("Value of Ravi is: " + e1);

		Object e2 = customerMap.get("Tom");
		System.out.println("Value of Tom is: " + e2);

		boolean b = customerMap.containsKey("Ravi");
		boolean b1 = customerMap.containsKey("Rama");
		System.out.println("Key Ravi is present: " + b);
		System.out.println("Key Ram is present: " + b1);

		System.out.println(customerMap.containsValue("33.33"));
		System.out.println(customerMap.containsValue(new Double(33.33)));

		// deleting
		customerMap.remove("Vinay");
		System.out.println("Values in TreeMap after deleting: " + customerMap);

		customerMap.clear();
		System.out.println("Values in TreeMap after clearing: " + customerMap);

	}
}
