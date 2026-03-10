package com.abc;

public class DemoConstructor {

    public DemoConstructor() {
        System.out.println("constrctor");
    }

    void m1(int x) {
        System.out.println("sdfsdf");
    }

    public static void main(String[] args) {
        DemoConstructor demo = new DemoConstructor();
        demo.m1(3);

    }
}
