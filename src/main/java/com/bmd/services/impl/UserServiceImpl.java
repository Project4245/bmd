package com.bmd.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bmd.config.AppConstants;
import com.bmd.entity.Role;
import com.bmd.entity.User;
import com.bmd.exceptions.ResourceNotFoundException;
import com.bmd.payload.UserDto;
import com.bmd.repository.RoleRepo;
import com.bmd.repository.UserRepo;
import com.bmd.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	
	
	
	public boolean isMobileNumberRegistered(String mobileNumber) {
        return userRepo.findByMobileNumber(mobileNumber) != null;
    }

    public void registerUser(User user) {
    	userRepo.save(user);
    }

    public void storeOtp(String mobileNumber, String otp, LocalDateTime otpExpiration) {
        User user = userRepo.findByMobileNumber(mobileNumber).get();
        user.setOtp(otp);
        user.setOtpExpiration(otpExpiration);
        userRepo.save(user);
    }

    public User getUserByMobileNumber(String mobileNumber) {
        return userRepo.findByMobileNumber(mobileNumber).get();
    }
	
	
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		user.setName(userDto.getName());
		user.setMobileNumber(userDto.getMobileNumber());
		user.setOtp(userDto.getOtp());
		user.setEmail(userDto.getEmail());

		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto findByMobileNumber(String mobileNumber) {
		User user = userRepo.findByMobileNumber(mobileNumber).get();
		return userToDto(user);
	}

	
	@Override
	public UserDto registerNewUser(UserDto userDto,int rId) {

		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		
//		 user.setOtp(this.passwordEncoder.encode(user.getOtp()));

		// roles
		
//		int id = 501;
		
		if(rId == AppConstants.DOCTOR) {
			Role role = this.roleRepo.findById(AppConstants.DOCTOR).get();
//			 role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

			user.getRoles().add(role);

			User newUser = this.userRepo.save(user);
			return this.modelMapper.map(newUser, UserDto.class);

		}
		else if(rId == AppConstants.PATIENT) {
			Role role = this.roleRepo.findById(AppConstants.PATIENT).get();
//			 role = this.roleRepo.findById(AppConstants.ADMIN_USER).get();

			user.getRoles().add(role);

			User newUser = this.userRepo.save(user);
			return this.modelMapper.map(newUser, UserDto.class);

		}
		else
		{
			System.out.println("Please Enter some value");

			return null;
		}
	}	

}
