package tasks;

import java.util.Scanner;

class Employee{
    String name;
    String designation;
    int age;
    int salary = 13500;

}
public class EmployeeMgmt {
    static void createEmployee(Scanner sc,Employee emp){
        String name;
        String designation;
        int age;

        System.out.println("Enter the employee Name");
        name = sc.next();
        sc.nextLine();
        System.out.println("Enter the employee Age");
        age = sc.nextInt();
        System.out.println("Enter the employee Designation");
        designation = sc.next();
        sc.nextLine();

        System.out.println("Confirm ? (Y/N)");
        String confirm;
        confirm = sc.nextLine();

        if (confirm.equals("Y")) {
            emp.name = name;
            emp.age = age;
            emp.designation = designation;
        }
        else{
            System.out.println("Employee Creation Cancelled");
        }
    }

    static void displayDetails(Employee emp){
        System.out.println("Employee Details:");
        System.out.println("Employee Name: "+emp.name);
        System.out.println("Employee Age: "+emp.age);
        System.out.println("Employee Designation: "+emp.designation);
        System.out.println("Employee Salary: "+emp.salary);
    }

    static void raiseSalary(Scanner sc , Employee emp){
        System.out.println("Enter the amount of raise:");
        int raise = sc.nextInt();
        emp.salary += raise;
        System.out.println("Employees new salary: "+emp.salary);
    }

    static boolean programEnd = false;

    public static void main(String[] args) {
        Employee emp = new Employee();

        while(!programEnd){
            Scanner sc = new Scanner(System.in);

            System.out.println("===============");
            System.out.println("Press 1 to Create Employee details");
            System.out.println("Press 2 to Display Employee details");
            System.out.println("Press 3 to Modify Employee salary");
            System.out.println("Press 4 to Exit");
            System.out.println("===============");

            int choice = sc.nextInt();

            switch (choice){
                case(1):
                    createEmployee(sc,emp);
                    break;
                case(2):
                    displayDetails(emp);
                    break;
                case (3):
                    raiseSalary(sc,emp);
                    break;
                case (4):
                    System.out.println("Exiting Program Byee");
                    programEnd = true;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            }
        }
    }