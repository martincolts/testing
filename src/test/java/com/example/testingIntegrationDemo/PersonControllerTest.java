package com.example.testingIntegrationDemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.testingIntegrationDemo.DTOs.PersonDTO;
import com.example.testingIntegrationDemo.Entities.Person;
import com.example.testingIntegrationDemo.repositories.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional	
public class PersonControllerTest {

	//Required to Generate JSON content from Java objects
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	//Test RestTemplate to invoke the APIs.

	@Autowired 
	private TestRestTemplate restTemplate ;

	@Autowired
	private PersonRepository personRepository ;
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

	@Test
	public void testCreatePerson() throws JsonProcessingException{

		//Building the Request body data
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("name", "Martin");
		requestBody.put("lastname", "Lopez");

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		//Creating http entity object with request body and headers
		HttpEntity<String> httpEntity =
				new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

		//Invoking the API
		Map<String, Object> apiResponse =
				restTemplate.postForObject("/demo/postPerson", httpEntity, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		/*String message = apiResponse.get("message").toString();
	    assertEquals("Book created successfully", message);
	    String bookId = ((Map<String, Object>)apiResponse.get("book")).get("id").toString();*/

		/*assertNotNull(bookId);*/

		//Fetching the Book details directly from the DB to verify the API succeeded
		Person dto = personRepository.findById(new Long(1));
		assertEquals("Martin", dto.getName());
		assertEquals(new Long(1), dto.getId());

		//Delete the data added for testing


	}

	@Test
	public void getPersonByIdTest () {

		Person p1 = new Person ();
		p1.setLastname("Martin");
		p1.setName("Martin");
		personRepository.save(p1);
		Person p2 = new Person ();
		p2.setLastname("Martin2");
		p2.setName("Martin2");
		personRepository.save(p2);
		Map<String, Object> apiResponse = restTemplate.getForObject("/demo/getPersonById/1", Map.class);

		String a = "dsfs";
		a.length();
		//Assert the response from the API
		String name = apiResponse.get("name").toString();
		assertEquals("Martin", name);
		Long id = Long.parseLong((apiResponse.get("id").toString()));

		assertEquals(new Long(1), id);


	}

	@Test
	public void getAllPersons () {
		Person p1 = new Person ();
		p1.setLastname("Martin");
		p1.setName("Martin");
		personRepository.save(p1);
		Person p2 = new Person ();
		p2.setLastname("Martin2");
		p2.setName("Martin2");
		personRepository.save(p2);
		ResponseEntity<String> apiResponse = restTemplate.exchange("/demo/getAllPersons", 
				HttpMethod.GET, null, String.class);
		String rates = apiResponse.getBody();

		assertNotNull(rates);
	}

	/*@After
    public void tearDown() {
        db.shutdown();
    }*/	
	// http://www.baeldung.com/integration-testing-in-spring

	//https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
}
