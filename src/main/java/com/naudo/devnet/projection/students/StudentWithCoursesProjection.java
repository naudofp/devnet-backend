package com.naudo.devnet.projection.students;

import java.util.List;

import com.naudo.devnet.models.Course;

public interface StudentWithCoursesProjection {

	Long getId();
	
	List<Course> getCourses();
}
