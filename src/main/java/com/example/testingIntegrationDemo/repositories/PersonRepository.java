package com.example.testingIntegrationDemo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.example.testingIntegrationDemo.Entities.Person;

public interface PersonRepository extends JpaRepository<Person, Serializable>{
	public Person findById (Long id);
	public List<Person> findByName (String name);
	public List<Person> findByLastname (String lastname);
	public Person getPersonById(Long id);
	
	@Transactional
	@Modifying
	@Query("update Person u set u.name = :name, u.lastname = :lastname where u.id = :id")
	void updatePersonById (@Param ("name")String name ,@Param("lastname") String lastname,@Param("id") Long id);
}
