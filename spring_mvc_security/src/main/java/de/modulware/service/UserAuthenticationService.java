package de.modulware.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.modulware.dao.BenutzerDao;
import de.modulware.domain.Benutzer;

@Service
public class UserAuthenticationService implements AuthenticationProvider {

	@Autowired
	private BenutzerDao benutzerDao;
	
	public void setBenutzerDao(BenutzerDao benutzerDao) {
		this.benutzerDao = benutzerDao;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		Benutzer benutzer = benutzerDao.findByAlias(token.getName());
		if (benutzer == null
				|| !benutzer.getPassword().equals(new Md5PasswordEncoder().encodePassword((String)token.getCredentials(), null))) {
			throw new UsernameNotFoundException("Invalid credential");
		}

		return new UsernamePasswordAuthenticationToken(benutzer,
				benutzer.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority(benutzer
						.getRole().toString())));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
