package com.riigsoft.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="student_tab")
public class Student {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String sname;
	private String gender;
	
	private String saddr;
	
	private String course;
}
