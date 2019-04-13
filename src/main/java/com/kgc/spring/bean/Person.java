package com.kgc.spring.bean;

import org.springframework.stereotype.Component;

@Component("person")
public class Person {

	private String name = "佚名";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
