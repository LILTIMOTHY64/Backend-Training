package exercises;


import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
	public static void main(String[] args) {
		try {
			// Step 1: Create FileWriter object in append mode
			FileWriter fw = new FileWriter
					("C:/Training/fullstack-course/backend-course/day5/src/exercises/sample.txt",true);
			// Step 2: Write to the file
			fw.write("Hello World");
			// Step 3: Close FileWriter
			fw.close();
		} catch (IOException e) {
			System.out.println("Error occurred: " + e);
		}
	}
}
