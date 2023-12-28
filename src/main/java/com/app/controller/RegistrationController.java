package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.StudentRegistration;
import com.app.service.StudentRegistrationService;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
	
	@Autowired
	StudentRegistrationService studentRegistrationService;
	
	List<String> courses; 
	List<String> timings;
	 
	 @ModelAttribute
	 public void preLoad() {
	  courses = studentRegistrationService.getCoursesList();
	  timings=studentRegistrationService.getTimingsList();
	 }
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@GetMapping("/registration")
	public String registrationForm(Model model) {
		StudentRegistration student = new StudentRegistration();
		model.addAttribute("courses", courses);
		model.addAttribute("timings", timings);
		model.addAttribute("student", student);
		return "registration";
	}

	@PostMapping("/savestudent")
	public  String saveStudent(Model model, @Valid StudentRegistration student, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("student", student);
			model.addAttribute("failuremsg", "A problem occured in Student registration.. Please try again");
			return "registration";
		}
		boolean saveStudent = studentRegistrationService.saveStudent(student);
		
		
		if(saveStudent) {
			sendEmail(student.getEmail());
			StudentRegistration newStudent = new StudentRegistration();
			model.addAttribute("timings", timings);
			model.addAttribute("courses", courses);
			model.addAttribute("student", newStudent);
			model.addAttribute("successmsg", "Student registration done and mail sent");
		}
		else {
			model.addAttribute("failuremsg", "A problem occured in Student registration.. Please try again");	
			model.addAttribute("student", student);
		}
		
		return "registration";
		
	}
	public void sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registration Team");
        message.setText("Student Registration done");

        javaMailSender.send(message);
    }
}
