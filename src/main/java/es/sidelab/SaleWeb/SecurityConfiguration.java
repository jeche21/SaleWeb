package es.sidelab.SaleWeb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		//Paginas Publicas
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/loggin").permitAll();
		http.authorizeRequests().antMatchers("/nuevoUsuario.html").permitAll();
		
		//Paginas Privadas
		http.authorizeRequests().anyRequest().authenticated();
		
		//Loggin
		http.formLogin().loginPage("/");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("contraseña");
		http.formLogin().defaultSuccessUrl("/tienda");
		http.formLogin().failureUrl("/loggin");
		
		//Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
	}
	
	//Si queremos poner los administradores
		@Override
		 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			//Unico usuario de la pagina.
			auth.inMemoryAuthentication().withUser("admin").password("pass").roles("ADMIN");

	}
}
