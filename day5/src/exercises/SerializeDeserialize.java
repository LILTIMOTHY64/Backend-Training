package exercises;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    int age;
    int salary;
    String designation;

    // Completed constructor with all fields
    public Student(int id, String name,int age,int salary, String designation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
    }
}

public class SerializeDeserialize {

    static final String FILE_PATH = "C:/Training/fullstack-course/backend-course/day5/src/exercises/students.txt";

    // Serialization - write object to file
    static void serialize() {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            Student s = new Student(101, "Rahul",21, 50000, "Programmer");
            oos.writeObject(s);
            System.out.println("Object Serialized Successfully");
            System.out.println("Stored -> ID: " + s.id + ", Name: " + s.name + ", Age: " + s.age
                    + ", Salary: " + s.salary + ", Designation: " + s.designation);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // De-serialization - read object from file
    static void deserialize() {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Student s = (Student) ois.readObject();
            System.out.println("\nObject Deserialized Successfully");
            System.out.println("ID          : " + s.id);
            System.out.println("Name        : " + s.name);
            System.out.println("Age         : " + s.age);
            System.out.println("Salary      : " + s.salary);
            System.out.println("Designation : " + s.designation);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        serialize();
        deserialize();
    }
}

 