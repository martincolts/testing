package com.example.testingIntegrationDemo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.services.PersonService;

@RestController
@RequestMapping(value = "/demo")
public class PersonController {

	@Autowired
	PersonService personService;

	@RequestMapping(value = "/postPerson", method = RequestMethod.POST)
	public PersonDTO save(@RequestBody PersonDTO personDTO) {
		Person person = personService.save(personDTO);
		return person.toPersonDTO();
	}

	@RequestMapping(value = "/getAllPersons", method = RequestMethod.GET)
	public List<PersonDTO> getAllPersons() {
		List<Person> persons = personService.getAllPersons();
		List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
		for (Person p : persons) {
			PersonDTO personDTO = new PersonDTO();
			personDTO.setId(p.getId());
			personDTO.setLastname(p.getLastname());
			personDTO.setName(p.getName());
			personDTOs.add(personDTO);
		}
		return personDTOs;
	}

	@RequestMapping(value = "/getPersonById/{id}", method = RequestMethod.GET)
	public PersonDTO getPersonById(@PathVariable Long id) {
		Person person = personService.getPersonById(id);
		person = new Person ();
		person.setLastname("Lopez");
		person.setName("Martin");
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(person.getId());
		personDTO.setName(person.getName());
		personDTO.setLastname(person.getLastname());
		return personDTO;
	}
}
