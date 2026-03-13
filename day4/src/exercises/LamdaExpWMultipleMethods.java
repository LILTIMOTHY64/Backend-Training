package exercises;

interface LamdaInterface2 {
	void m1();
}
public class LamdaExpWMultipleMethods {
	void m2()
	{
		System.out.println("Normal method");
	}
	public static void main(String[] args) {
		LamdaInterface2 t = () -> {
			System.out.println("Lamda expresssion");
		};
		LamdaExpWMultipleMethods bb = new LamdaExpWMultipleMethods();
		bb.m2();				
		t.m1();
	}
}
