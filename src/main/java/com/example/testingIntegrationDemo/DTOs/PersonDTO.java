package com.example.testingIntegrationDemo.DTOs;

import com.example.testingIntegrationDemo.Entities.Person;

public class PersonDTO {
	
	private String name ;
	private String lastname ;
	private Long id ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Person toPerson (){
		Person person = new Person () ;
		person.setName (this.name);
		person.setLastname (this.lastname);
		return person ;
	}
	public void setId(Long id) {
		this.id = id ;
		
	}
	
	public Long getId(){
		return id ;
	}

}
