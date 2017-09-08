package com.example.testingIntegrationDemo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.repositories.PersonRepository;

@ComponentScan({"com.example.testingIntegrationDemo"})
public class PersonTestContextConfig {

	@Bean
	PersonDTO personDTO (){
		PersonDTO personDTO = new PersonDTO();
		personDTO.setLastname("Lopez");
		personDTO.setName("Martin");
		return personDTO ;
	}
	
	@Bean
	Person person (){
		Person person = new Person ();
		person.setLastname("Lopez");
		person.setName("Lopez");
		return person ;
	}
	
}
