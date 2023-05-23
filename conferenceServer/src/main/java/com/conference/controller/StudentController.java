package com.conference.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conference.entity.speaker.Student;
import com.conference.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/")
	public Student saveStudentChoice( @RequestBody Student student ) {
		return studentService.saveStudent(student);
	}
	
	@PostMapping("/{userId}")
	public Student saveStudent( @RequestBody String student, @PathVariable("userId") Long userId ) throws IOException {
		return studentService.saveStudentWithUser(student, userId);
	}
	
	//Getting all student
	@GetMapping("/")
	public List<Student> getAllStudent() {
		return this.studentService.getAllStudent();
	}

}
