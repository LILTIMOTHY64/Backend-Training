package exercises;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class StudentStream implements Serializable {
	int id;
	String name;

	public StudentStream(int id, String name) {
		this.id = id;
		this.name = name;
	}
}

public class StreamWrite {
	public static void main(String[] args) {
		try {
			StudentStream s = new StudentStream(101,"Raahul");
			FileOutputStream fos = new FileOutputStream("C:/Training/fullstack-course/backend-course/day5/src/exercises/students.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(s);
			oos.close();
			fos.close();
			System.out.println("Object Serialized Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
