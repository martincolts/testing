package com.example.testingIntegrationDemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.repositories.PersonRepository;
import com.example.testingIntegrationDemo.services.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration (classes = {
		PersonTestContextConfig.class ,
		PersonService.class , 
		PersonRepository.class
})
//@WebMvcTest(PersonController.class)
public class TestingIntegrationDemoApplicationTests {

	@Autowired
	private PersonService personService ;	
	
	@Autowired 
	PersonDTO personDTO ;
	
	@Test
	public void testPersonServiceSavePerson() {
		personService.save(personDTO);
		Person personResult = personService.getPersonById(new Long(1));
		Assert.assertEquals(new Long(1),	 personResult.getId());
		Assert.assertEquals(personDTO.getLastname(), personResult.getLastname());
		Assert.assertEquals(personDTO.getName(), personResult.getName());
	}
	
	/*@Test
    public void personControllerShouldReturnPersonDTO() throws Exception {
		
        //wgreetinghen(personServiceMock.save(personDTO)).thenReturn(Person);
        this.mockMvc.perform(post("/savePerson")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Martin")));
    }
*/
}
