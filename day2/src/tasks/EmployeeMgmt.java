package tasks;

import java.util.Scanner;

class Employee {
	String name;
	String designation;
	int age;
	int salary;

}

class InvalidChoice extends Exception {
	public InvalidChoice() {
		super("Invalid Choice Selected");
	}
}

class InvalidDesignation extends Exception {
	public InvalidDesignation() {
		super("Invalid Designation Entered");
	}
}

public class EmployeeMgmt {
	static void createEmployee(Scanner sc, Employee emp) throws InvalidChoice, InvalidDesignation {
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
		if (designation.equals("Programmer")) {
			emp.salary = 20000;
		} else if (designation.equals("Tester")) {
			emp.salary = 15000;
		} else if (designation.equals("Manager")) {
			emp.salary = 50000;
		} else {
			throw new InvalidDesignation();
		}
		System.out.println("Continue ? (Y/N)");
		String confirm;
		confirm = sc.nextLine();

		if (confirm.equals("Y")) {
			emp.name = name;
			emp.age = age;
			emp.designation = designation;
			createEmployee(sc, emp);
		} else if (confirm.equals("N")) {
			emp.name = name;
			emp.age = age;
			emp.designation = designation;
			System.out.println("Employee Creation Closed");
		} else {
			throw new InvalidChoice();
		}

	}

	static void displayDetails(Employee emp) {
		System.out.println("Employee Details:");
		System.out.println("Employee Name: " + emp.name);
		System.out.println("Employee Age: " + emp.age);
		System.out.println("Employee Designation: " + emp.designation);
		System.out.println("Employee Salary: " + emp.salary);
	}

	static void raiseSalary(Scanner sc, Employee emp) {
		System.out.println("Enter the amount of raise:");
		int raise = sc.nextInt();
		emp.salary += raise;
		System.out.println("Employees new salary: " + emp.salary);
	}

	public static void main(String[] args) {
		
		boolean programEnd = false;

		Employee emp = new Employee();

		Scanner sc = new Scanner(System.in);

		while (!programEnd) {

			System.out.println("===============");
			System.out.println("Press 1 to Create Employee details");
			System.out.println("Press 2 to Display Employee details");
			System.out.println("Press 3 to Modify Employee salary");
			System.out.println("Press 4 to Exit");
			System.out.println("===============");

			int choice = sc.nextInt();

			try {
				switch (choice) {
				case (1):
					try {
						createEmployee(sc, emp);
					} catch (InvalidChoice e) {
						System.out.println("Continue Choice Exception: " + e.getMessage());
					} catch (InvalidDesignation e) {
						System.out.println("Designation Exception: " + e.getMessage());
					}
					break;
				case (2):
					displayDetails(emp);
					break;
				case (3):
					raiseSalary(sc, emp);
					break;
				case (4):
					System.out.println("Exiting Program Byee");
					programEnd = true;
					break;
				default:
					throw new InvalidChoice();
				}
			} catch (InvalidChoice e) {
				System.out.println("Menu Choice Exception: " + e.getMessage());
			}
		}
	}
}