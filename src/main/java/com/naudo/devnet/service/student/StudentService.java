package com.naudo.devnet.service.student;

import java.util.List;

import com.naudo.devnet.dto.course.CourseListDTO;
import com.naudo.devnet.dto.student.StudentHomeDTO;
import com.naudo.devnet.dto.student.StudentListDTO;
import com.naudo.devnet.dto.student.StudentRegisterDTO;
import com.naudo.devnet.dto.user.UserLoginDTO;

public interface StudentService {

	UserLoginDTO save(StudentRegisterDTO dto);
	
	void update(StudentRegisterDTO dto, Long id);
	
	void delete(Long id);
	
	void addCourse(Long idStudent, Long idCourse);
	
	 void removeCourse(Long idStudent, Long idCourse);
	
	StudentHomeDTO findById(Long id);
	
	List<CourseListDTO> findCourses(Long id);

	List<StudentListDTO> findByUsername(String name);
}
