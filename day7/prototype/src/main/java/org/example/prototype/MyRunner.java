package org.example.prototype;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private final ApplicationContext context;

    public MyRunner(ApplicationContext context) {
        this.context = context;
    }


    @Override
    public void run(String... args) throws Exception {
        FoodOrder order1 = context.getBean(FoodOrder.class);
        FoodOrder order2 = context.getBean(FoodOrder.class);

        order1.serve();
        order2.serve();

        if(order1 == order2){
            System.out.println("Same Object");
        }
        else{
            System.out.println("Different Objects (Prototype)");
        }
    }
}
