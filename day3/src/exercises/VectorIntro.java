package exercises;



import java.util.Vector;

public class VectorIntro {
	public static void main(String[] args) {

		Vector vector = new Vector();
		
		vector.add("C");
		vector.add("A");
		vector.add("E");
		vector.add("D");
		vector.add("F");
		vector.add(new Integer(101));
		vector.add(null);
		vector.add("A");
		
		System.out.println(vector);
		vector.add(3, "B");
		System.out.println(vector);
		String s1 = (String) vector.get(3);
		System.out.println("sdf  " + s1);
		vector.remove("F");
		System.out.println(vector);
		vector.remove(5);
		System.out.println(vector);
		
		
	}
}
