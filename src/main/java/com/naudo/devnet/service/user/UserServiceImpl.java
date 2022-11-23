package com.naudo.devnet.service.user;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naudo.devnet.dto.user.UserHolderDTO;
import com.naudo.devnet.dto.user.UserLoginDTO;
import com.naudo.devnet.exceptions.UserNotFoundException;
import com.naudo.devnet.models.User;
import com.naudo.devnet.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	private UserRepository repository;
	private PasswordEncoder encoder;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
		this.repository = userRepository;
		this.encoder = encoder;
	}

	public UserLoginDTO login(UserHolderDTO dto) {

		User user = repository.findByUsername(dto.username()).orElseThrow(() -> new UserNotFoundException("User was not found with username: " + dto.username()));
		
		if(!encoder.matches(dto.password(), user.getPassword()))
			throw new UserNotFoundException("Password incorrect");	
		
		return new UserLoginDTO(user.getId());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username)
				.orElseThrow(() -> new ServiceException("User was not found with username:" + username));
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
