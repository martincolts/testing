package com.example.testingIntegrationDemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.repositories.PersonRepository;

@Service 
public class PersonService {
	
	@Autowired
	PersonRepository personRepository ;
	
	public Person save (PersonDTO personDTO){
		return personRepository.save (personDTO.toPerson());
	}
	public Person getPersonById (Long id){
		return personRepository.findById(id);
	}
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

}
