package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.StudentRegistration;
import com.app.repository.StudentRegistrationRepo;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService{
	
	@Autowired
	StudentRegistrationRepo studentRegistrationRepo;
	
	@Override
	public boolean saveStudent(StudentRegistration Student) {
		StudentRegistration savedStudent = studentRegistrationRepo.save(Student);
		return savedStudent.getId()!= null?  true:  false;
	}

	@Override
	public List<String> getCoursesList() {
		// TODO Auto-generated method stub
		return studentRegistrationRepo.getCourses();
	}

	@Override
	public List<String> getTimingsList() {
		// TODO Auto-generated method stub
		return studentRegistrationRepo.getTimings();
	}

}
