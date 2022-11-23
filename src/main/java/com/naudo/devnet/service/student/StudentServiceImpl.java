package com.naudo.devnet.service.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naudo.devnet.dto.course.CourseListDTO;
import com.naudo.devnet.dto.student.StudentHomeDTO;
import com.naudo.devnet.dto.student.StudentListDTO;
import com.naudo.devnet.dto.student.StudentRegisterDTO;
import com.naudo.devnet.dto.user.UserLoginDTO;
import com.naudo.devnet.exceptions.CourseNotFoundException;
import com.naudo.devnet.exceptions.StudentNotFoundException;
import com.naudo.devnet.exceptions.UserNotFoundException;
import com.naudo.devnet.models.Course;
import com.naudo.devnet.models.Student;
import com.naudo.devnet.projection.students.StudentProjection;
import com.naudo.devnet.repository.CourseRepository;
import com.naudo.devnet.repository.StudentRepository;
import com.naudo.devnet.repository.UserRepository;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	private CourseRepository courseRepository;
	private UserRepository userRepository;
	private PasswordEncoder encoder;
	
	public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, CourseRepository courseRepository, PasswordEncoder encoder) {
		this.studentRepository = studentRepository;
		this.userRepository=  userRepository;
		this.courseRepository = courseRepository;
		this.encoder = encoder;
	}

	public UserLoginDTO save(StudentRegisterDTO dto) {
		
		if (userRepository.existsByUsername(dto.username())) 
			throw new UserNotFoundException("Username "+ dto.username() +" is already registered");
		
		
		Student student = new Student(null, dto.username(), dto.password(), dto.name());
		student.setPassword(encoder.encode(dto.password()));
		
		Student studentSaved = studentRepository.save(student);
		
		return new UserLoginDTO(studentSaved.getId());
	}

	public void update(StudentRegisterDTO dto, Long id) {
		
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student "+ id + " was not found"));
		
		if(!dto.username().equals(student.getUsername())) {			
			if (userRepository.existsByUsername(dto.username())) 
				throw new UserNotFoundException("Email "+ dto.username() +" is already registered");
		}
		
		student.setUsername(dto.username());
		student.setName(dto.name());
		student.setPassword(dto.password());
		
		studentRepository.save(student);
	}

	public void delete(Long id) {
		
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student "+ id + " was not found"));
		studentRepository.delete(student);
	}

	public StudentHomeDTO findById(Long id) {
		
		StudentProjection student = studentRepository.findStudentById(id).orElseThrow(() -> new StudentNotFoundException("Student "+ id + " was not found"));
		return new StudentHomeDTO(student.getId(), student.getName(), student.getUsername(), student.getScore());
	}

	public void addCourse(Long idStudent, Long idCourse) {
		
		Student student = studentRepository.findById(idStudent).orElseThrow(() -> new StudentNotFoundException("Student "+ idStudent + " was not found"));
		Course course = courseRepository.findById(idCourse).orElseThrow(() -> new CourseNotFoundException("Course "+ idCourse + " was not found"));
	
		for(Course c : student.getCourses()){
			if(c.equals(course)) {
				throw new CourseNotFoundException("Course was already added");
			}
		}
		
		student.getCourses().add(course);
		course.getStudents().add(student);
		
		courseRepository.save(course);
		studentRepository.save(student);
	}
	
	public void removeCourse(Long idStudent, Long idCourse) {
		
		Student student = studentRepository.findById(idStudent).orElseThrow(() -> new StudentNotFoundException("Student "+ idStudent + " was not found"));
		Course course = courseRepository.findById(idCourse).orElseThrow(() -> new CourseNotFoundException("Course "+ idCourse + " was not found"));
	
		student.getCourses().remove(course);
		course.getStudents().remove(student);
		
		courseRepository.save(course);
		studentRepository.save(student);
	}

	public List<CourseListDTO> findCourses(Long id) {

		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student "+ id + " was not found"));
		List<CourseListDTO> dtos = new ArrayList<>();
		
		for (Course c : student.getCourses()) {
			dtos.add(new CourseListDTO(c.getId(), c.getName(), c.getAddScore()));
		}
		
		return dtos;
	}

	public List<StudentListDTO> findByUsername(String name) {
		
		List<StudentProjection> students = studentRepository.findByUsernameContainingIgnoreCase(name);
		List<StudentListDTO> dtos = new ArrayList<>();
		
		for (StudentProjection p : students) {
			dtos.add(new StudentListDTO(p.getId(), p.getUsername()));
		}
		return dtos;
	}

}
