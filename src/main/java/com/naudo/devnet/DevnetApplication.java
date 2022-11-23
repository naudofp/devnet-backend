package com.naudo.devnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.naudo.devnet.models.Course;
import com.naudo.devnet.repository.CourseRepository;

@SpringBootApplication
public class DevnetApplication implements CommandLineRunner {
	
	@Autowired
	private CourseRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DevnetApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void run(String... args) throws Exception{
		if(!repository.existsById(Long.parseLong("1"))) {
			String[] namesCourse = 
				{
				  "Python", "C#", "C++", "JavaScript",
				  "Node.js", "Spring Boot", "Dart", "Flutter",
				  "PHP", "Java", "Go", "SQL", "Ruby",
				  "React", "HTML", "CSS", "React Native",
				  "Angular", "Vue.js", "jQuery", "Svelte",
				  "Bootstrap", "Bulma", "JavaScript",
				  "TypeScript", "Sass", "UI/UX"
				};
			
			Integer[] scoreCourses = 
				{
				  310, 300, 300, 350, 220, 230,
				  250, 180, 300, 360, 300, 150, 280,
				  200, 50, 50, 100, 200, 180, 150, 
				  150, 70, 40, 180, 180, 50, 250
				};
			
					
			for (int i = 0; i < namesCourse.length; i++) {
				repository.save(new Course(null ,namesCourse[i], scoreCourses[i]));
			}
		}
		
	}


}
