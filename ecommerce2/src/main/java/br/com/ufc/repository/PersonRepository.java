package br.com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufc.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	Person findByLogin(String login);
	
}
