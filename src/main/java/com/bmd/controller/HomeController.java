package com.bmd.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmd.model.JwtRequest;
import com.bmd.model.User;
import com.bmd.service.UserService;
import com.bmd.utils.LoginUser;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private UserService userService;

	
	
	
    @PostMapping("/sendOtp")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
       	
    	
//    	LoginUser db = new LoginUser();
//    	String otp = db.getPassword();
    	userService.registerUser(user);
    	return new ResponseEntity<User>(user, HttpStatus.CREATED);

        
//        return ResponseEntity.ok("OTP is :: "+otp+"User registered successfully.");
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/test")
	public String test() {
		return "Api is working";
	}

	@GetMapping(value = "/users")
	public List<User> getUser() {
		System.out.println("Getting users");
		return userService.getUsers();
	}

	@GetMapping(value = "/currentUser")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}

//	@PostMapping("/create")
//	public User createUser(@RequestBody User user) {
//		
//		return userService.createUser(user);
//	}
	
	
}
