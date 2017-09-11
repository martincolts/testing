package com.example.testingIntegrationDemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.controllers.PersonController;
import com.example.testingIntegrationDemo.services.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PersonController.class)
@WebAppConfiguration
@ContextConfiguration(classes={PersonTestContextConfig.class})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class PersonControllerTest {
	
	private static EmbeddedDatabase db;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private PersonService personService ;
	
	 @Autowired
	 private PersonDTO personDTO;
	 
	 @Autowired private Person person ;
	 

	@MockBean
	private PersonService service;

	/*
	 * @Test public void greetingShouldReturnMessageFromService() throws
	 * Exception { when(service.save(personDTO)).thenReturn(person);
	 * this.mockMvc.perform(get("/demo/postPerson")).andDo(print()).andExpect(
	 * status().isOk()) .andExpect(content().string(containsString("Martin")));
	 * }
	 */
	
	/*@Before
	public void setUp(){
		db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
	}*/

	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	@Sql({"/drop-schema.sql","/data-schema.sql","/data.sql"})
	public void getPersonById() throws Exception {
		personService.save(personDTO);
		this.mockMvc.perform(get("/demo/getPersonById/99")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.name").value("Martin")).andExpect(jsonPath("$.id").value(new Long(99)));
	}
	
	/*@After
    public void tearDown() {
        db.shutdown();
    }*/	
// http://www.baeldung.com/integration-testing-in-spring
	
//https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
}
