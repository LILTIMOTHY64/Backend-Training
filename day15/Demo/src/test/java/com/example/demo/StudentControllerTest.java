package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.config.SecurityConfig;
import com.example.demo.controller.StudentController;
import com.example.demo.entities.Student;
import com.example.demo.service.StudentService;

@WebMvcTest(StudentController.class)
@Import(SecurityConfig.class)
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private StudentService studentService;
	
	@Test
	void testGetAllStudents() throws Exception {
		when(studentService.getAllStudents())
			.thenReturn(List.of(new Student(1L, "Rahul", "Java")));
		mockMvc.perform(get("/students"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name").value("Rahul"))
			.andExpect(jsonPath("$[0].course").value("Java"));
	}
	
}
