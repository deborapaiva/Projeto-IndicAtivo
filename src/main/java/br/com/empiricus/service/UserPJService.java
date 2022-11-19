package br.com.empiricus.service;



import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.empiricus.model.UserLoginPJ;
import br.com.empiricus.repository.UserPJRepository;




@Service
public class UserPJService {

	@Autowired
	private UserPJRepository repository;
	
	@Autowired
    EmailService emailService;
	
	
	public interface UserrPJService {
		UserLoginPJ saveUserPJ(UserLoginPJ userLoginPJ);//METODO  POST
		List<UserLoginPJ> getAllUserPJ(); //METODO GET ALL (PESQUISA TODA TABELO)
		UserLoginPJ getUserPJById(long id); //METODO GET POR ID
		UserLoginPJ updateUserPJ(UserLoginPJ userLoginPJ, long id); //METODO PUT 
		void deleteUserPJ(long id); //METODO DELETE
	}


	public UserLoginPJ CadastrarUserPJ(UserLoginPJ userLoginPJ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(userLoginPJ.getSenha());
		userLoginPJ.setSenha(senhaEncoder);
		emailService.enviarEmail(userLoginPJ.getEmail(), "Confirmação de cadastro", "O seu cadastro no IndicAtivos Foi realizado com sucesso!");
		return repository.save(userLoginPJ);
	}

	public Optional<UserLoginPJ> Logar(Optional<UserLoginPJ> user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UserLoginPJ> cnpj = repository.findByCnpj(user.get().getCnpj());
		
		if (cnpj.isPresent()) {
			
			if (encoder.matches(user.get().getSenha(), cnpj.get().getSenha())) {
				
				String auth = user.get().getCnpj() + ":" + user.get().getSenha();
				
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(cnpj.get().getNome());

				return user;
			}
		}
		return null;
	}
}