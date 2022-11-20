package br.com.empiricus.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.empiricus.model.ClientePJ;
import br.com.empiricus.repository.UserPJRepository;


@Service
public class UserPJService {

	@Autowired
	private UserPJRepository repository;
	
	@Autowired
    EmailService emailService;
	
	
	public interface UserrPJService {
		ClientePJ saveUserPJ(ClientePJ userLoginPJ);//METODO  POST
		List<ClientePJ> getAllUserPJ(); //METODO GET ALL (PESQUISA TODA TABELO)
		ClientePJ getUserPJById(long id); //METODO GET POR ID
		ClientePJ updateUserPJ(ClientePJ userLoginPJ, long id); //METODO PUT 
		void deleteUserPJ(long id); //METODO DELETE
	}


	public ClientePJ CadastrarUserPJ(ClientePJ userLoginPJ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(userLoginPJ.getSenha());
		userLoginPJ.setSenha(senhaEncoder);
		emailService.enviarEmail(userLoginPJ.getEmail(), "Confirmação de cadastro", "O seu cadastro no IndicAtivos Foi realizado com sucesso!");
		return repository.save(userLoginPJ);
	}

}