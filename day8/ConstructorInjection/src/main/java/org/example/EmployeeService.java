package org.example;

public class EmployeeService {
    private final String serviceName;

    public EmployeeService(String serviceName) {
        this.serviceName = serviceName;
        System.out.println("EmployeeService created with name: " + serviceName);
    }

    public void processEmployee(Employee employee) {
        System.out.println(serviceName + " processing: " + employee.getName() +
                          " from " + employee.getDepartment().getName());
    }

    public String getServiceName() {
        return serviceName;
    }
}
