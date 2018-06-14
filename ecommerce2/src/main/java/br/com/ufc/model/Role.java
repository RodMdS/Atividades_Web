package br.com.ufc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	
	@Id
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private List<Person> people;
	
	@Override
	public String getAuthority() {
		return this.role;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
