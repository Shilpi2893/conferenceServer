package com.conference.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conference.entity.speaker.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
