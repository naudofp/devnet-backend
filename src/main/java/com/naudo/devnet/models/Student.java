package com.naudo.devnet.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "student")
public class Student extends User{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name_student",nullable = false)
	private String name;
	@Transient
	private Integer score;
	
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "courses_student",
	joinColumns = @JoinColumn(name = "course_fk"),
	inverseJoinColumns = @JoinColumn(name = "student_fk"))
	private List<Course> courses =new ArrayList<>();
	
	
	public Student(Long id, String username, String password, String name) {
		super(id, username, password);
		this.score = calculateScore();
		this.name = name;
	}

	public Student() {}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(courses, other.courses) && Objects.equals(score, other.score);
	}

	public Integer getScore() {
		return calculateScore();
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Integer calculateScore() {
		
		Integer newScore = 0;
		
		for (Course course : courses) {
			newScore += course.getAddScore();
		}
		
		return newScore;  
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
