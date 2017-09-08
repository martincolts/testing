package com.example.testingIntegrationDemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.repositories.PersonRepository;
import com.example.testingIntegrationDemo.services.PersonService;

@RunWith(SpringRunner.class)
@ContextConfiguration (classes = {
		PersonTestContextConfig.class ,
		PersonService.class , 
		PersonRepository.class
})
//@WebMvcTest(PersonController.class)
@SpringBootTest
public class TestingIntegrationDemoApplicationTests {

	@Autowired
	private PersonService personService ;	
	
	@Autowired 
	PersonDTO personDTO ;
	
	@Test
	public void testPersonServiceSavePerson() {
		personService.save(personDTO);
		Person personResult = personService.getPersonById(new Long(4));
		Assert.assertEquals(new Long(4),	 personResult.getId());
		Assert.assertEquals(personDTO.getLastname(), personResult.getLastname());
		Assert.assertEquals(personDTO.getName(), personResult.getName());
	}

	@Sql ("/data.sql")
	@Test
	public void testPersonServiceFindById (){
		Person person = personService.getPersonById(new Long(1));
		Assert.assertEquals("Lopez", person.getLastname());
	}
}
