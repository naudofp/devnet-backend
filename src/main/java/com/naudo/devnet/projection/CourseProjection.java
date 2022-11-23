package com.naudo.devnet.projection;

import java.util.List;

import com.naudo.devnet.models.Student;

public interface CourseProjection {

	Long getId();
	
	String getName();
	
	List<Student> getStudents();
	
	Integer getAddScore();
}
