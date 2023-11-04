package com.bmd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "role_table")
public class Role {

	@Id	
	private int id;
	
	private String name;
	
}
