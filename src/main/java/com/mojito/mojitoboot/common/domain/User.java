package com.mojito.mojitoboot.common.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

	/**
	 * 
	 */  
	
	private static final long serialVersionUID = 1L;

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer id;
	private String name;
}
