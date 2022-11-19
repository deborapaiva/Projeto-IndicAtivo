package br.com.empiricus.seguranca;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.empiricus.model.UserLoginPJ;

public class UserPJDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;

		
	public UserPJDetailsImpl(UserLoginPJ user) {
		this.userName = user.getCnpj();
		this.password = user.getSenha();
	}

	public UserPJDetailsImpl() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {	
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
