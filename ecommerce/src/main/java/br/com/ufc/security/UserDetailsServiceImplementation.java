package br.com.ufc.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.ufc.model.Person;
import br.com.ufc.repository.PersonRepository;

@Repository
@Transactional
public class UserDetailsServiceImplementation implements UserDetailsService{

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Person person = personRepository.findByLogin(login);
		
		if(person == null) {
			throw new UsernameNotFoundException("Deu merda");
		}
		
		return new User(person.getUsername(),
				person.getPassword(),
				true,
				true,
				true,
				true,
				person.getAuthorities());
	}

}
