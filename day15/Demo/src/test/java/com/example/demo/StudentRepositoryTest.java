package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Student;
import com.example.demo.repository.StudentRepository;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    void testFindByCourse() {
        studentRepository.save(new Student("Amit", "Java"));
        studentRepository.save(new Student("Neha", "Python"));
        studentRepository.save(new Student("Ravi", "Java"));
        List<Student> students = studentRepository.findByCourse("Java");
        assertEquals(2, students.size());
    }
}
