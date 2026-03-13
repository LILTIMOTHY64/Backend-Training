package exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListIterationWForLoop {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> result = new ArrayList<>();

		for (Integer n : numbers) {
			if (n % 2 == 0) {
				result.add(n * 2);
			}
		}
		for (Integer r : result) {
			System.out.println(r);
		}

	}
}
