package exercises;

import java.util.TreeMap;

public class TreeMapRev {
	public static void main(String[] args) {
		// Create a TreeMap
		TreeMap map = new TreeMap();

		// Add key/value to the TreeMap
		//TreeMap stores keys in sorted order
		map.put("CCC",new Integer(103));
		map.put("EEE",new Integer(105));
		map.put("BBB",new Integer(102));
		map.put("AAA",new Integer(101));
		map.put("DDD",new Integer(104));
		System.out.println(map);
	}
}
