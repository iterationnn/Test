package com.kgc.spring.bean;

public class HelloSpring {

	private String who;
	
	public void setWho(String who) {
		this.who = who;
	}
	
	public void print() {
		System.out.println("Hello "+  who);
	}
	
}
