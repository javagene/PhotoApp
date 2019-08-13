package com.murali.photoApp.api.users;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murali.photoapi.model.CreateUserRequestModel;
import com.murali.photoapi.model.CreateUserResponseModel;
import com.murali.photoapi.model.UserDto;
import com.murali.photoapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/status/check")
	public String status() {
		System.out.println("Users Status check");
		return "Working on port :: "+env.getProperty("local.server.port");
	}

	
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails) {
		System.out.println("Create User");
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createUser =  userService.createUser(userDto);
		
		CreateUserResponseModel returnValue = modelMapper.map(createUser, CreateUserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}
}
