package com.example.testingIntegrationDemo.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;

@Entity
public class Person {
	
	@Id
	@GeneratedValue
	private Long id ;
	
	private String name ;
	private String lastname ;
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
	public Long getId() {
		return id;
	}
	public PersonDTO toPersonDTO() {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setLastname(lastname);
		personDTO.setName(name);
		personDTO.setId(id);
		return personDTO;
	}
	

}
