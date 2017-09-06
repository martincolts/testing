package com.example.testingIntegrationDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.services.PersonService;

@RestController
@RequestMapping (value ="/demo")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value="/postPerson" ,method=RequestMethod.POST)
	public PersonDTO save (@RequestBody PersonDTO personDTO){
		Person person = personService.save(personDTO);
		return person.toPersonDTO();
	}

}
