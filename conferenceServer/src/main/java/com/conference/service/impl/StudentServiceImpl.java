package com.conference.service.impl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conference.entity.User;
import com.conference.entity.speaker.Student;
import com.conference.repo.StudentRepository;
import com.conference.repo.UserRepository;
import com.conference.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public Student saveStudent(Student student) {	
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudent() {
		return this.studentRepository.findAll();
	}

	@Override
	public Student saveStudentWithUser(String student, Long userId) throws IOException {
		//USER
		User userThatwasInDb = this.userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("User with this id was not in the db");
        });
		
		//STUDENT
		Student stud = new Student();
		
		stud = objectMapper.readValue(student, Student.class);
		
		stud.getIsParticipation();
		stud.getArrivalDate();
		stud.getDepartureDate();
		stud.getIsFinancialNeed();
		stud.getIsAccomodation();
		stud.getAccomodation();
		stud.getPoster();
		stud.getSession();
		
		stud.setUserId(userId);
		
		return studentRepository.save(stud);
	}

}
