package com.naudo.devnet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naudo.devnet.dto.user.UserHolderDTO;
import com.naudo.devnet.dto.user.UserLoginDTO;
import com.naudo.devnet.service.user.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

	private UserService service;

	public UserController(UserService userService) {
		this.service = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<UserLoginDTO> addStudent(@RequestBody UserHolderDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.login(dto));
	}
}
