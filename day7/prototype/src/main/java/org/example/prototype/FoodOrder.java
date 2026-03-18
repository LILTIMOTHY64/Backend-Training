package org.example.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FoodOrder {
    public FoodOrder(){
        System.out.println("New Food Order Created");
    }

    public void serve(){
        System.out.println("Serving Food...");
    }
}

