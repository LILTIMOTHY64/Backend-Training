package exercises;

interface LamdaInterface{
	void m1();
}
public class LamdaExp {
	public static void main(String[] args) {
		Tests t =()->{System.out.println("Lamda Expression");};
		t.m1();
	}
}
