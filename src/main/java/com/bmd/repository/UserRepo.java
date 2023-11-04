package com.bmd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmd.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

	Optional<Users> findByMobileNumber(String mobileNumber);

}
