package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Person;
import br.com.ufc.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping("/insertPerson")
	public ModelAndView formPerson() {
		ModelAndView mv = new ModelAndView("formPerson");
		mv.addObject("person", new Person());
		return mv;
	}
	
	@PostMapping("/add")
	public ModelAndView addPerson(Person person) {
		personService.addPerson(person);
		
		ModelAndView mv = new ModelAndView("redirect:/person/list");
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView getPeople() {
		List<Person> people = personService.getAllPeople();
		
		ModelAndView mv = new ModelAndView("people");
		mv.addObject("allPeople", people);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView updatePerson(@PathVariable Long id) {
		Person person = personService.getByID(id);
		
		ModelAndView mv = new ModelAndView("formPerson");
		mv.addObject("person", person);
		return mv;
	}
	
	@RequestMapping("/remove/{id}")
	public ModelAndView removePerson(@PathVariable Long id) {
		personService.rmPerson(id);
		
		ModelAndView mv = new ModelAndView("redirect:/person/list");
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
}
