package br.com.empiricus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empiricus.model.ClientePF;
import br.com.empiricus.model.ClientePJ;
import br.com.empiricus.service.UserPFService;
import br.com.empiricus.service.UserPJService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/cadastrar")

public class CadastroController {
	
	
	@Autowired
	private UserPJService userPJService;
	
	@Autowired
	private UserPFService userPFService;
	
	//PJ
	@PostMapping("/cnpj")
	public ResponseEntity<ClientePJ> CadastroUserPJ(@RequestBody ClientePJ userLoginPJ) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(userPJService.CadastrarUserPJ(userLoginPJ));
	}
	
	//PF	
	@PostMapping("/cpf")
	public ResponseEntity<ClientePF> CadastrarUserPF(@RequestBody ClientePF userLoginPF) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(userPFService.CadastrarUserPF(userLoginPF));
	}	
	
	

}
