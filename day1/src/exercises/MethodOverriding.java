package exercises;
class Family{
    void property(){
        System.out.println("Property Obtained");
    }

    void marry(){
        System.out.println("Family Selected Girl");
    }
}
public class MethodOverriding extends Family{
    @Override
    void marry(){
        System.out.println("Campus Selected Girl");
    }
    public static void main(String[] args) {
        MethodOverriding demoObject = new MethodOverriding();
        demoObject.marry();
        demoObject.property();
    }
}
