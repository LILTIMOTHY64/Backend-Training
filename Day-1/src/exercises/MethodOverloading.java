package exercises;

public class MethodOverloading {

    void method1(){
        System.out.println("Hello");
    }

    void method1(String x){
        System.out.println("Hello " + x );
    }

    public static void main(String[] args) {
        MethodOverloading demoObject = new MethodOverloading();

        demoObject.method1();
        demoObject.method1("Keerthi");
    }
}
