package br.com.empiricus.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.empiricus.model.UserLoginPF;
import br.com.empiricus.model.UserLoginPJ;
import br.com.empiricus.repository.UserPFRepository;
import br.com.empiricus.repository.UserPJRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserPFRepository userPFRepository;

	@Autowired
	private UserPJRepository userPJRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username.length() ==  11) {
			Optional<UserLoginPF>userLoginPF = userPFRepository.findByCpf(username);

			if(userLoginPF.isPresent())
				return new UserPFDetailsImpl(userLoginPF.get());
			else
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		else if (username.length() == 14) {
			Optional<UserLoginPJ> userLoginPJ = userPJRepository.findByCnpj(username);

			if(userLoginPJ.isPresent())
				return new UserPJDetailsImpl(userLoginPJ.get());
			else
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}


		return null;
	}


}
