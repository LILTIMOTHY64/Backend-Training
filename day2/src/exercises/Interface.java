package exercises;

interface interface1 {
	abstract void add();

	abstract void div();

}

public class Interface implements interface1 {
	public void add() {
		System.out.println("Addition logic");
	}

	public void div() {
		System.out.println("Division logic");
	}

	public static void main(String[] args) {
		Interface gg = new Interface();
		gg.add();
		gg.div();
	}
}
