package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Spring Constructor Injection Demo ===");

        // Load Spring ApplicationContext from classpath
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        // Get Department bean
        Department department = context.getBean("department", Department.class);
        System.out.println("Department: " + department);

        // Get Employee bean (which has Department injected via constructor)
        Employee employee = context.getBean("employee", Employee.class);
        System.out.println("Employee: " + employee);

        // Demonstrate that the same Department instance is injected
        System.out.println("Department objects are same instance: " +
                          (employee.getDepartment() == department));

        System.out.println("=== Constructor Injection Successful! ===");
    }
}