package com.abc;

public class DemoVariables {
    int a=19;
    static	int b =20;

    void m1() {
        int a =29;
        System.out.println("this is the method"+a);
    }

    public DemoVariables() {
        int a = 10;
        System.out.println("constrctor"+a);
    }

    public static void main(String[] args) {
        DemoVariables demoObject = new DemoVariables();
        demoObject.m1();
        System.out.println(demoObject.a);
        System.out.println(DemoVariables.b);

    }
}
