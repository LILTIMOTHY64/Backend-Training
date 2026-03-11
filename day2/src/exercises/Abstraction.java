package exercises;
abstract class AbstractClass
{
	void add()
	{
		System.out.println("Addition Logic");
	}
	abstract void div();
}
public class Abstraction extends AbstractClass {
	void div()
	{
		System.out.println("Division logic");
	}
	public static void main(String[] args) {
		Abstraction gg = new Abstraction();
		gg.add();
		gg.div();
		}
}

