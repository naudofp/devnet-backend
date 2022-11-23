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

import com.naudo.devnet.dto.course.CourseListDTO;
import com.naudo.devnet.dto.student.StudentHomeDTO;
import com.naudo.devnet.dto.student.StudentListDTO;
import com.naudo.devnet.dto.student.StudentRegisterDTO;
import com.naudo.devnet.dto.user.UserLoginDTO;
import com.naudo.devnet.service.student.StudentService;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/student")
public class StudentController {

	private StudentService service;

	public StudentController(StudentService studentService) {
		this.service = studentService;
	}
	
	@PostMapping
	public ResponseEntity<UserLoginDTO> addStudent(@RequestBody StudentRegisterDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
	}
	
	@PostMapping("/add-course/{idCourse}/{idStudent}")
	public ResponseEntity<String> addCourseOnStudent(@PathVariable(value = "idCourse") Long idCourse, @PathVariable(value = "idStudent") Long idStudent) {
		service.addCourse(idStudent, idCourse);
		return ResponseEntity.status(HttpStatus.CREATED).body("Course successfully added to Student");
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<String> updatedStudent(@RequestBody StudentRegisterDTO dto, @PathVariable(value = "id") Long id) {
		service.update(dto, id);
		return ResponseEntity.status(HttpStatus.OK).body("Student updated successfully");
	}
	
	@DeleteMapping("/remove-course/{idCourse}/{idStudent}")
	public ResponseEntity<String> removeCourseOnStudent(@PathVariable(value = "idCourse") Long idCourse, @PathVariable(value = "idStudent") Long idStudent) {
		service.removeCourse(idStudent, idCourse);
		return ResponseEntity.status(HttpStatus.CREATED).body("Course successfully deleted to Student");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentHomeDTO> getStudent(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<List<CourseListDTO>> getStudentWithCourses(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findCourses(id));
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<StudentListDTO>> getStudentsByName(@PathVariable(value = "name") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByUsername(name));
	}
}
