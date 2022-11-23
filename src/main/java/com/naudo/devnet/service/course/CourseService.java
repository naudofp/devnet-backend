package com.naudo.devnet.service.course;

import java.util.List;

import com.naudo.devnet.dto.course.CourseInfoDTO;
import com.naudo.devnet.dto.course.CourseListDTO;
import com.naudo.devnet.dto.course.CourseRegisterDTO;

public interface CourseService {

	List<CourseListDTO> save(CourseRegisterDTO dto);

	void update(CourseRegisterDTO dto, Long id);
	
	void delete(Long id);
	
	CourseInfoDTO findById(Long id);
	
	List<CourseListDTO> findAll();
	
	List<CourseListDTO> findByName(String name);
}
