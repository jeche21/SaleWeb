package es.sidelab.SaleWeb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	 private UsuarioRepository userRepository;
	
	 @Override
	 public Authentication authenticate(Authentication auth){
		 Usuario user = userRepository.findByEmail(auth.getName());
		 if (user == null) {
			 throw new BadCredentialsException("User not found");
		 }
		 String password = (String) auth.getCredentials();
		 if (!new BCryptPasswordEncoder().matches(password, user.getContraseña())) {
			 throw new BadCredentialsException("Wrong password");
		 }

		 List<GrantedAuthority> roles = new ArrayList<>();
		 for (String role : user.getRol()) {
			 roles.add(new SimpleGrantedAuthority(role));
			 }
		 return new UsernamePasswordAuthenticationToken(user.getEmail(), password, roles);
	 }
	 
	@Override
	public boolean supports(Class<?> authenticationObject) {
		// TODO Auto-generated method stub
		return true;
	}
}
