package cmdlineApplication;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class EmployeeManagementSystem {
	private static final String TEXT_FILENAME = "C:/Training/fullstack-course/backend-course/projects/src/cmdlineApplication/Employees.txt";


	static void createEmployee(Scanner sc, ArrayList<Employee> employees) throws InvalidChoice, InvalidDesignation {
		Employee emp = new Employee();

		String name;
		String designation;
		int age;

		while (true) {
			System.out.print("Enter the employee Name (max 2 spaces allowed):");
			name = sc.nextLine();

			int spaceCount = name.length() - name.replace(" ", "").length();

			if (spaceCount <= 2) {
				break;
			} else {
				System.out.println("Invalid Name: Please enter a name with a maximum of 2 spaces.");
			}
		}
		while (true) {
			System.out.print("Enter the employee Age (between 19-60):");
			if (sc.hasNextInt()) {
				age = sc.nextInt();
				sc.nextLine();

				if (age >= 19 && age <= 60) {
					break;
				} else {
					System.out.println("Invalid Age: Age must be between 19 and 60.");
				}
			} else {
				System.out.print("Invalid Input: Please enter a numeric value for age.");
				sc.next();
				sc.nextLine();
			}
		}
		System.out.print("Enter the employee Designation: ");
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

		emp.name = name;
		emp.age = age;
		emp.designation = designation;

		employees.add(emp);

		if (confirm.equals("Y")) {
			createEmployee(sc, employees);
		} else if (confirm.equals("N")) {
			System.out.println("Employee Creation Closed");
		} else {
			throw new InvalidChoice();
		}
	}

	static void displayDetails(ArrayList<Employee> employees) {
		if (employees.isEmpty()) {
			System.out.println("No employees to display.");
			return;
		}
		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) {
			Employee e = iterator.next();
			System.out.println("--------------------");
			System.out.println("Employee's Name: " + e.name);
			System.out.println("Employee's Age: " + e.age);
			System.out.println("Employee's Designation: " + e.designation);
			System.out.println("Employee's Salary: " + e.salary);
			System.out.println("--------------------");
		}
	}

	static void raiseSalary(Scanner sc, ArrayList<Employee> employees) {
		if (employees.isEmpty()) {
			System.out.println("No employees to modify salary for.");
			return;
		}
		System.out.println("Enter the name of the employee whose salary you want to raise:");
		String employeeName = sc.nextLine();

		boolean found = false;

		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) {
			Employee e = iterator.next();

			if (e.name.equals(employeeName)) {
				System.out.println("Enter the percentage of raise (allowed-max: 10%):");
				int raisePercentage = sc.nextInt();
				sc.nextLine();

				if (raisePercentage >= 1 && raisePercentage <= 10) {
					e.salary += (int) (e.salary * 0.01 * raisePercentage);
					System.out.println(e.name + "'s new salary: " + e.salary);
					found = true;
					break;
				} else {
					System.out.println("Max Allowed Raise is of 10%. Please enter a value between 1 and 10.");
				}
			}
		}

		if (!found) {
			System.out.println("Employee with name '" + employeeName + "' not found or raise not Applied.");
		}
	}
	
    static void saveEmployeesToText(ArrayList<Employee> employees, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Employee e : employees) {
                // Format: name,designation,age,salary
                writer.write(e.name + "," + e.designation + "," + e.age + "," + e.salary + "\n");
            }
            System.out.println("Employee data saved to " + filename + " successfully.");
        } catch (IOException ex) {
            System.err.println("Error saving employee data: " + ex.getMessage());
        }
    }

    static ArrayList<Employee> loadEmployeesFromText(String filename) {
        ArrayList<Employee> loadedEmployees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        Employee emp = new Employee();
                        emp.name = parts[0].trim();
                        emp.designation = parts[1].trim();
                        emp.age = Integer.parseInt(parts[2].trim());
                        emp.salary = Integer.parseInt(parts[3].trim());
                        loadedEmployees.add(emp);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line (numeric parsing error): " + line + " - " + e.getMessage());
                    }
                } else {
                    System.err.println("Skipping malformed line (incorrect number of parts): " + line);
                }
            }
            System.out.println("Employee data loaded.");
        } catch (IOException ex) {
            System.err.println("Error loading employee data: " + ex.getMessage());
        }
        return loadedEmployees;
    }


	public static void main(String[] args) {

		boolean programEnd = false;

		Scanner sc = new Scanner(System.in);

		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		employees = loadEmployeesFromText(TEXT_FILENAME);

		while (!programEnd) {

            System.out.println("===============");
            System.out.println("Press 1 to Create Employee details");
            System.out.println("Press 2 to Display Employee details");
            System.out.println("Press 3 to Modify Employee salary");
            System.out.println("Press 4 to Exit");
            System.out.println("===============");

			int choice = -1;
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
				sc.nextLine();
			} else {
				System.out.println("Invalid input. Please enter a number between 1 and 4.");
				sc.next();
				continue;
			}

			try {
				switch (choice) {
				case (1):
					try {
						createEmployee(sc, employees);
					} catch (InvalidChoice e) {
						System.out.println("Continue Choice Exception: " + e.getMessage());
					} catch (InvalidDesignation e) {
						System.out.println("Designation Exception: " + e.getMessage());
					}
					break;
				case (2):
					displayDetails(employees);
					break;
				case (3):
					raiseSalary(sc, employees);
					break;
                case (4):
                	saveEmployeesToText(employees, TEXT_FILENAME);
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
		sc.close();
	}
}
