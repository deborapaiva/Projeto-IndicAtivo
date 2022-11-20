package br.com.empiricus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empiricus.model.ClientePF;
import br.com.empiricus.service.UserPFService;
import br.com.empiricus.service.UserPFService.UserrPFService;

@RestController
@RequestMapping("/usuarios/PF")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioPFController {
	
	@Autowired
	private UserPFService userPFService;
	
	@Autowired
	private UserrPFService userrPFService;
	
	
/*
	@PostMapping("/logar/cpf")
	public ResponseEntity<UserLoginPF> Autentication(@RequestBody Optional<UserLoginPF> user){
		return userPFService.Logar(user)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.build());
	}*/

	//--> Aqui adicionamos um Optional de usuarios que estava trazendo valores nulos
	@PostMapping("/cadastrar/cpf")
	public ResponseEntity<ClientePF> CadastrarUserPF(@RequestBody ClientePF userLoginPF) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(userPFService.CadastrarUserPF(userLoginPF));
	}	
	
	/*
	 		@PostMapping()
		public ResponseEntity<ClientePF> saveUser(@RequestBody ClientePF userLoginPF){
			return new ResponseEntity<ClientePF>(userrPFService.saveUserPF(userLoginPF), HttpStatus.CREATED);
			
		}
		*/
		
		@GetMapping
		public List<ClientePF> getAllUser(){
			return userrPFService.getAllUserPF();
		}
		
		
		@GetMapping("{id}")
		public ResponseEntity<ClientePF> getUserById(@PathVariable("id") long clientePFid){
			return new ResponseEntity<ClientePF>(userrPFService.getUserPFById(clientePFid), HttpStatus.OK);
		}
		
		
		@PutMapping("{id}")
		public ResponseEntity<ClientePF> updateUserPF(@PathVariable("id") long id
													,@RequestBody ClientePF userLoginPF){
			return new ResponseEntity<ClientePF>(userrPFService.updateUserPF(userLoginPF, id), HttpStatus.OK);
		}
		
		
		@DeleteMapping("{id}")
		public ResponseEntity<String> deleteUserPF(@PathVariable("id") long id){
			//DELETANDO DO BD
			userrPFService.deleteUserPF(id);
			return new ResponseEntity<String>("Cliente Deletado com sucesso!.", HttpStatus.OK);
		}

}
