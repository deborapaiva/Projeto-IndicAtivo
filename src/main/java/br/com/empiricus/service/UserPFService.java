package br.com.empiricus.service;



import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.empiricus.model.ClientePF;
import br.com.empiricus.repository.UserPFRepository;


@Service
public class UserPFService {

	@Autowired
	private UserPFRepository repository;
	
	
	@Autowired
    EmailService emailService;
	
	public interface UserrPFService {
	ClientePF saveUserPF(ClientePF userLoginPF);//METODO  POST
	List<ClientePF> getAllUserPF(); //METODO GET ALL (PESQUISA TODA TABELO)
	ClientePF getUserPFById(long id); //METODO GET POR ID
	ClientePF updateUserPF(ClientePF userLoginPF, long id); //METODO PUT 
	void deleteUserPF(long id); //METODO DELETE
	}

	public ClientePF CadastrarUserPF(ClientePF userLoginPF) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(userLoginPF.getSenha());
		userLoginPF.setSenha(senhaEncoder);
		emailService.enviarEmail(userLoginPF.getEmail(), "Confirmação de cadastro", "O seu cadastro no IndicAtivos Foi realizado com sucesso!");
		return repository.save(userLoginPF);
	}

}