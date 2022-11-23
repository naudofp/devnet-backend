package com.naudo.devnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naudo.devnet.models.Student;
import com.naudo.devnet.projection.students.StudentProjection;
import com.naudo.devnet.projection.students.StudentWithCoursesProjection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Optional<StudentProjection> findStudentById(Long id);
	
	Optional<StudentWithCoursesProjection> findStudentWithCoursesById(Long id);
	
	List<StudentProjection> findByUsernameContainingIgnoreCase(String username);	
}
