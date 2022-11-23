package com.naudo.devnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naudo.devnet.models.Course;
import com.naudo.devnet.projection.CourseProjection;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	Optional<CourseProjection> findCourseById(Long id);
	
	List<CourseProjection> findCourseByNameContainsIgnoreCase(String name);
	
}
