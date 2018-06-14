package br.com.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ufc.model.Person;
import br.com.ufc.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public void addPerson(Person person) {
		person.setPassword(new BCryptPasswordEncoder().encode(person.getPassword()));
		personRepository.save(person);
	}
	
	public List<Person> getAllPeople() {
		return personRepository.findAll();
	}
	
	public Person getByID(Long id) {
		return personRepository.getOne(id);
	}
	
	public void rmPerson(Long id) {
		personRepository.delete(id);
	}
	
}
