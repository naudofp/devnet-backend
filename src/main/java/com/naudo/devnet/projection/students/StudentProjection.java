package com.naudo.devnet.projection.students;

import java.util.List;

import com.naudo.devnet.models.Course;

public interface StudentProjection {
	
	Long getId();
	
	String getName();
	
	String getPhone();
	
	String getUsername();
	
	String getPassword();

	Integer getScore();
	
	List<Course> getCourses();
	
}
