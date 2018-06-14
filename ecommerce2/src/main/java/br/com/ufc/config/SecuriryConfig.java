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
public class SecuriryConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImplementation userDetailsServiceImplementation;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/beautymakeup").permitAll() // Permito todo mundo acessar /inicio
		.antMatchers("/product/**").permitAll() // ALTERAR DEPOIS DOS TESTES
		.antMatchers("/person/**").permitAll()
		//.antMatchers("/person/insertPerson").hasRole("USER") //Somente pessoa com papel "USER" acessa /pessoa/formulario
		//.antMatchers("/person/add").hasAnyRole("USER","ADMIN") // Pessoa com papel "USER" ou "ADMIN" acessa /pessoa/salvar
		//.antMatchers("/person/list").hasRole("ADMIN") // /pessoa/listar todo mundo pode acessar
		
		.anyRequest().authenticated() // o resto precisa está autenticado
		
		.and()
		.formLogin()
		.loginPage("/person/login") // Esse é o controller que chama nosso formulario
		.permitAll() //permitir acesso para essa url "entrar"
		
		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
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
		web.ignoring().antMatchers("/css/**", "/js/**","/img/**"); // ignora e permite uri's com esses arquivos
	}
	
}
