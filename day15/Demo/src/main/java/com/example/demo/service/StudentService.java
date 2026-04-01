package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = null;
		
	}
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public List<Student> getStudentByCourse(String course) {
		return studentRepository.findByCourse(course);
	}
	
	public String getWelcomeMessage() {
		return "Welcome Students!";
	}
}
