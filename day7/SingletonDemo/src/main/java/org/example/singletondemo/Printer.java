package org.example.singletondemo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Printer {
    public Printer(){
        System.out.println("Printer Object is Created");
    }

    public void printMessage(){
        System.out.println("Printing Document");
    }
}
