package com.naudo.devnet.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_course", length = 50, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.REMOVE)
	private List<Student> students;
	
	@Column(name = "score_course")
	private Integer addScore;
	
	public Course(Long id, String name, Integer addScore) {
		this.id = id;
		this.name = name;
		this.students = new ArrayList<>();
		this.addScore = addScore;
	}
	
	public Course() {}
	
	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(students, other.students);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Integer getAddScore() {
		return addScore;
	}
	public void setAddScore(Integer addScore) {
		this.addScore = addScore;
	}
}
