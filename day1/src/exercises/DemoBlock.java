package exercises;

public class DemoBlock {

    {
        System.out.println("Heello block");
    }

    static {
        System.out.println("static Heello block");
    }

    public static void main(String[] args) {
        DemoBlock demo = new DemoBlock();

    }
}
