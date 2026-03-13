package exercises;

interface ParamFunc {
	int m1(int x);
}

public class LamdaParameterized {
	public static void main(String[] args) {
		ParamFunc i = x -> x * x;
		System.out.println("The square of the input is: " + i.m1(5));
	}
}