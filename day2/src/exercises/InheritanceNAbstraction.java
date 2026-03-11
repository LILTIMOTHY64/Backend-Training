package exercises;

interface InterfaceForAbstraction {
	abstract void add();
	abstract void div();
}

abstract class AbstractClass2 implements InterfaceForAbstraction {
	public void add() {
		System.out.println("Addition logic");
	}
}

public class InheritanceNAbstraction extends AbstractClass2 {
	public void div() {
		System.out.println("Division logic");
	}

	public static void main(String[] args) {
		InheritanceNAbstraction gg = new InheritanceNAbstraction();
		gg.add();
		gg.div();
	}
}