package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findByCourse(String course);
}
