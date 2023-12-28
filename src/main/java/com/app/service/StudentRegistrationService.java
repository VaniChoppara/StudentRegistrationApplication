package com.app.service;

import java.util.List;

import com.app.entity.StudentRegistration;

public interface StudentRegistrationService {

	public boolean saveStudent(StudentRegistration Student);
	public List<String> getCoursesList();
	public List<String> getTimingsList();
 
}
