package org.example.constructorinjection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private final EmployeeService service;

    public MyRunner(EmployeeService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        service.display();
    }
}
