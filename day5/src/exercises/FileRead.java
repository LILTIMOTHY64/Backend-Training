package exercises;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRead {
	public static void main(String[] args) {

		try {
			// Step 1: Create FileReader object
			FileReader fr = new FileReader("C:/Training/fullstack-course/backend-course/day5/src/exercises/sample.txt");
			int i;
			System.out.println("Reading data from file:");
			// Step 2: Read character by character
			while ((i = fr.read()) != -1) {
				System.out.print((char) i);
			}
			// Step : Close the file
			fr.close();
			
		} catch (IOException e) {
			System.out.println("Error occurred: " + e);
		}
	}
}
