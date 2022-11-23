package com.naudo.devnet.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naudo.devnet.dto.course.CourseInfoDTO;
import com.naudo.devnet.dto.course.CourseListDTO;
import com.naudo.devnet.dto.course.CourseRegisterDTO;
import com.naudo.devnet.service.course.CourseService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/course")
public class CourseController {

	private CourseService service;
	
	public CourseController(CourseService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<List<CourseListDTO>> addCourse(@RequestBody CourseRegisterDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updatedCourse(@RequestBody CourseRegisterDTO dto, @PathVariable(value = "id") Long id) {
		service.update(dto, id);
		return ResponseEntity.status(HttpStatus.OK).body("Course updated successfully");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletedCourse(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
	}
	
	@GetMapping
	public ResponseEntity<List<CourseListDTO>> getAllCourses() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseInfoDTO> getCourse(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<CourseListDTO>> getCourseByName(@PathVariable(value = "name") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
	}
}
