package com.riigsoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riigsoft.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
