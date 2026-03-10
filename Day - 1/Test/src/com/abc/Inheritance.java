package com.abc;
class GrandParent{
    void GPmethod(){
        System.out.println("Grand Parent Method");
    }
}

class Parent extends GrandParent{
    void Pmethod(){
        System.out.println("Parent Method");
    }
}
public class Inheritance extends Parent{
    void method(){
        System.out.println("Child Method");
    }

    public static void main(String[] args) {
        Inheritance demoObject = new Inheritance();
        demoObject.method();
        demoObject.Pmethod();
        demoObject.GPmethod();
    }
}
