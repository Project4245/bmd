package com.bmd.model;

import lombok.Builder;

@Builder
public class JwtResponse {

	private String jwtToken;

	private String username;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public JwtResponse() {
	}

	public JwtResponse(String jwtToken, String Mobile_No) {
		super();
		this.jwtToken = jwtToken;
		this.username = Mobile_No;
	}

	@Override
	public String toString() {
		return "JwtResponse [jwtToken=" + jwtToken + ", Mobile No=" + username + "]";
	}

}
