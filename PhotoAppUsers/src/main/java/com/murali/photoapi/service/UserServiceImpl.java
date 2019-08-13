package com.murali.photoapi.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murali.photoapi.model.UserDto;
import com.murali.photoapi.model.UserEntity;
import com.murali.photoapi.model.UserRepository;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDto createUser(UserDto userDto) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userEntity.setEncryptedPassword("test");
		
		userRepository.save(userEntity);
		
		UserDto userdto = modelMapper.map(userEntity, UserDto.class);
		
		return userdto;
	}

}
