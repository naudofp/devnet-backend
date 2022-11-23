package com.naudo.devnet.service.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.naudo.devnet.dto.course.CourseInfoDTO;
import com.naudo.devnet.dto.course.CourseListDTO;
import com.naudo.devnet.dto.course.CourseRegisterDTO;
import com.naudo.devnet.exceptions.CourseNotFoundException;
import com.naudo.devnet.models.Course;
import com.naudo.devnet.projection.CourseProjection;
import com.naudo.devnet.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	private CourseRepository courseRepository; 
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<CourseListDTO> save(CourseRegisterDTO dto) {
		
		Course course = new Course(null, dto.name(), dto.scoreCourse());
		courseRepository.save(course);
		
		return findAll();
	}
	
	public void update(CourseRegisterDTO dto, Long id) {
		
		Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course "+ id + " was not found"));
		
		course.setName(dto.name());
		course.setAddScore(dto.scoreCourse());
		
		courseRepository.save(course);
	}

	public void delete(Long id) {
		Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course "+ id + " was not found"));
		courseRepository.delete(course);
	}

	public CourseInfoDTO findById(Long id) {

		Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course "+ id + " was not found"));
		List<String> names = new ArrayList<>();
		
		return new CourseInfoDTO(course.getId(), course.getName(), course.getAddScore(), names);
	}

	public List<CourseListDTO> findAll() {
		
		List<Course> courses = courseRepository.findAll();
		List<CourseListDTO> dtos = new ArrayList<>();
		
		for (Course e : courses) {
			dtos.add(new CourseListDTO(e.getId(), e.getName(), e.getAddScore()));
		}
		
		return dtos;
	}

	public List<CourseListDTO> findByName(String name) {

		List<CourseListDTO> dtos = new ArrayList<>();
		
		for (CourseProjection e : courseRepository.findCourseByNameContainsIgnoreCase(name)) {
			dtos.add(new CourseListDTO(e.getId(), e.getName(), e.getAddScore()));
		}
		
		return dtos;
	}
}
