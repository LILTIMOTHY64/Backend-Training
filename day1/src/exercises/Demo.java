package exercises;

public class Demo {

    void Addition(){
        System.out.println("Addition Logic (2+3) = " + (2+3));
    }

    void Division(){
        System.out.println("Division Logic (6/3) = " + (6/3));
    }

    public static void main(String[] args) {
        Demo demoObject = new Demo();
        demoObject.Addition();
        demoObject.Division();
    }
}