package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.StudentRegistration;

public interface StudentRegistrationRepo extends JpaRepository<StudentRegistration, Integer> {

	@Query(value="select course from courses", nativeQuery= true)
	public List<String> getCourses();
	
	@Query(value="select timing from timings", nativeQuery= true)
	public List<String> getTimings();



}
