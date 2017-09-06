package com.example.testingIntegrationDemo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testingIntegrationDemo.Entities.Person;

public interface PersonRepository extends JpaRepository<Person, Serializable>{
	public Person findById (Long id);
	public List<Person> findByName (String name);
	public List<Person> findByLastname (String lastname);

}
