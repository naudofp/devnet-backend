package com.naudo.devnet.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.naudo.devnet.exceptions.CourseNotFoundException;
import com.naudo.devnet.exceptions.StudentNotFoundException;
import com.naudo.devnet.exceptions.UserNotFoundException;

public class CustomControllerAdvice {

	 // USER
	 
	 @ExceptionHandler(UserNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception exception) {
		 return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage()));
	 }

	 // STUDENT
	 
	 @ExceptionHandler(StudentNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleStudentNotFoundException(Exception exception) {
		 return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage()));
	 }

	 // COURSE
	 
	 @ExceptionHandler(CourseNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleCourseNotFoundException(Exception exception) {
		 return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage()));
	 }
}
