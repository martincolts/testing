package com.example.testingIntegrationDemo;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.controllers.PersonController;
import com.example.testingIntegrationDemo.services.PersonService;

@WebMvcTest(PersonController.class)
@ContextConfiguration(classes={
		PersonTestContextConfig.class
})
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PersonDTO personDTO;
	@Autowired
	private Person person ;

    @MockBean
    private PersonService service;
/*
    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.save(personDTO)).thenReturn(person);
        this.mockMvc.perform(get("/demo/postPerson")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Martin")));
    }*/
}
