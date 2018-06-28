package br.com.ufc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ufc.security.UserDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImplementation userDetailsServiceImplementation;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/").permitAll()
		.antMatchers("/product/list").permitAll()
		.antMatchers("/person/insertPerson").permitAll()
		.antMatchers("/person/add").permitAll()
		.antMatchers("/person/list").hasRole("ADMIN")
		.antMatchers("/person/remove").hasRole("ADMIN")
		.antMatchers("/product/insertProduct").hasRole("ADMIN")
		.antMatchers("/product/add").hasRole("ADMIN")
		.antMatchers("/product/remove").hasRole("ADMIN")
		.antMatchers("/shoppingcart/addProduct").hasRole("USER")
		.antMatchers("/shoppingcart/visualize").hasRole("USER")
		.antMatchers("/shoppingcart/remove").hasRole("USER")
		.antMatchers("/shoppingcart/buy").hasRole("USER")
		.antMatchers("/shoppingcart/purchaseshistoric").hasRole("USER")

		
		.anyRequest().authenticated() // only authenticated
		.and()
		.formLogin()
		.loginPage("/person/login") // Esse é o controller que chama nosso formulario
		.permitAll() //permitir acesso para essa url "entrar"
		
		.and()
		.logout()
		.logoutSuccessUrl("/person/login?logout") // logout sucesso
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsServiceImplementation).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**"); // ignora e permite uri's com esses arquivos
	}
	
}
