package exercises;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class StreamRead {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream
					("C:/Training/fullstack-course/backend-course/day5/src/exercises/students.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			StudentStream s=(StudentStream) ois.readObject();
			System.out.println("Deserialized ID: "+ s.id);
			System.out.println("Deserialized Name: "+s.name);
			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
