package exercises;

abstract class AbstractLevelOne {
	abstract void add();

	abstract void div();
}

abstract class AbstractLevelTwo extends AbstractLevelOne {
	void add() {
		System.out.println("Addition logic");
	}
}

public class MultiLvlAbstraction extends AbstractLevelTwo {
	void div() {
		System.out.println("Division logic");
	}

	public static void main(String[] args) {
		MultiLvlAbstraction gg = new MultiLvlAbstraction();
		gg.add();
		gg.div();
	}
}
