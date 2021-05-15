package com.riigsoft.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.riigsoft.model.Student;
import com.riigsoft.repo.StudentRepository;
import com.riigsoft.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository repo;
	@Override
	@Transactional
	public Integer saveStudent(Student s) {
		
		return repo.save(s).getId();
	}

	@Override
	@Transactional
	public void updateStudent(Student s) {
		repo.save(s);

	}

	@Override
	@Transactional
	public void deleteStudent(Integer id) {
		repo.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Student> getOneStudent(Integer id) {
	
		return repo.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> getAllStudents() {
		
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isStudentExist(Integer id) {
		return repo.existsById(id);
	}
}

