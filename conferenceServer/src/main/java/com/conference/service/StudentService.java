package com.conference.service;

import java.io.IOException;
import java.util.List;

import com.conference.entity.speaker.Student;

public interface StudentService {
	
	//saving student choice
	public Student saveStudent( Student student );
	
	//saving student choice linking with user
	public Student saveStudentWithUser( String student, Long userId ) throws IOException;
	
	public List<Student> getAllStudent();

}
