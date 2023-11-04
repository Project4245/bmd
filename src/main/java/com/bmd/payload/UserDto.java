package com.bmd.payload;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters !!")
	private String name;

	
	@NotEmpty(message = "Email is required !!")
	@Email
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	@Column(unique = true)
	@Size(min = 4, message = "Username must be min of 4 characters !!")
	private String mobileNumber;

//	@NotEmpty
//	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")
	
//	private String password;

	private String otp;

	
	private Set<RoleDto> roles = new HashSet<>();
	

	private LocalDateTime otpExpiration;
}
