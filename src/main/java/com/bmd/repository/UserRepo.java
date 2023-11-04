package com.bmd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmd.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
		
	
	Optional<User> findByMobileNumber(String mobileNumber);


}
