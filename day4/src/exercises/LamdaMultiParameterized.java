package exercises;

interface MultiParamFunc {
	void m1(int a, int b);
}
public class LamdaMultiParameterized {
	public static void main(String[] args) {
	MultiParamFunc i = (a,b)->System.out.println("The sum of a and b is "+(a+b));
	i.m1(2, 3);
	}

}