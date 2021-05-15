package com.riigsoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riigsoft.model.Student;
import com.riigsoft.service.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private IStudentService service;

	// 1. Student Registration Form
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("student", new Student());

		return "StudentRegister";
	}

	// 2. Save Student into DB
	@PostMapping("/save")
	public String save(@ModelAttribute Student student, Model model) {
		Integer id = service.saveStudent(student);
		String message = "Student saved with '" + id + "' successfuly...";
		fetchAll(model);
		model.addAttribute("message", message);

		// return "StudentData";
		return "redirect:../student/all";
	}

	// 3. Get All Students
	@GetMapping("/all")
	public String fetchAll(Model model) {
		List<Student> list = service.getAllStudents();
		model.addAttribute("list", list);

		return "StudentData";
	}

	// 4. Delete Student from DB
	@GetMapping("/delete/{id}")
	public String remove(@PathVariable Integer id, Model model) {
		String message = "";
		if (service.isStudentExist(id)) {
			try {
				service.deleteStudent(id);
				message = "Student with '" + id + "' deleted successfully...";
			} catch (DataIntegrityViolationException dive) {
				message = "Student with '" + id + "' does not deleted current in use...";
				dive.printStackTrace();
			}
		} else {
			message = "Student with '" + id + "' does not exist.";
		}

		fetchAll(model);
		model.addAttribute("message", message);

		return "redirect:../all";
	}

	// 5. show Edit Form
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Integer id, Model model) {
		Optional<Student> opt = service.getOneStudent(id);
		String page = "";
		if (opt.isPresent()) {
			Student student = opt.get();
			page = "EditStudent";
			model.addAttribute("student", student);
		} else {
			page = "redirect:../all";
		}
		
		return page;
	}

	// 6. Update Student

	@PostMapping("/update")
	public String update(@ModelAttribute Student student, Model model) {
		service.updateStudent(student);
		String message = "Student with '" + student.getId() + "' updated successfully ...";
		fetchAll(model);
		model.addAttribute("message", message);

		// return "StudentData";
		return "redirect:../student/all";
	}

	// 7. View one Student
	@GetMapping("/view/{id}")
	public String getOne(@PathVariable Integer id, Model model) {
		Optional<Student> opt = service.getOneStudent(id);
		String page = "";
		if (opt.isPresent()) {
			Student student = opt.get();
			page = "ViewStudent";
			model.addAttribute("student", student);
		} else {
			page = "redirect:../all";
		}
		
		return page;

	}
}
