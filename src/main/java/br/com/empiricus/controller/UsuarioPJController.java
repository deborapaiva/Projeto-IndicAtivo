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

import br.com.empiricus.model.UserLoginPJ;
import br.com.empiricus.service.UserPJService;
import br.com.empiricus.service.UserPJService.UserrPJService;

@RestController
@RequestMapping("/usuarios/PJ")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioPJController {
	
	@Autowired
	private UserPJService userPJService;
	
	@Autowired
	private UserrPJService userrPJService;

	@PostMapping("/logar/cnpj")
	public ResponseEntity<UserLoginPJ> Autentication(@RequestBody Optional<UserLoginPJ> user){
		return userPJService.Logar(user)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.build());
	}

	//--> Aqui adicionamos um Optional de usuarios que estava trazendo valores nulos
	@PostMapping("/cadastrar/cnpj")
	public ResponseEntity<UserLoginPJ> CadastroUserPJ(@RequestBody UserLoginPJ userLoginPJ) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(userPJService.CadastrarUserPJ(userLoginPJ));
	}	
	

	@PostMapping()
	public ResponseEntity<UserLoginPJ> saveUserPJ(@RequestBody UserLoginPJ userLoginPJ){
		return new ResponseEntity<UserLoginPJ>(userrPJService.saveUserPJ(userLoginPJ), HttpStatus.CREATED);
		
	}

	@GetMapping
	public List<UserLoginPJ> getAllUserPJ(){
		return userrPJService.getAllUserPJ();
	}
	

	@GetMapping("{id}")
	public ResponseEntity<UserLoginPJ> getUserPJById(@PathVariable("id") long clientePJid){
		return new ResponseEntity<UserLoginPJ>(userrPJService.getUserPJById(clientePJid), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<UserLoginPJ> updateUserPJ(@PathVariable("id") long id
												,@RequestBody UserLoginPJ userLoginPJ){
		return new ResponseEntity<UserLoginPJ>(userrPJService.updateUserPJ(userLoginPJ, id), HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserPJ(@PathVariable("id") long id){
		//DELETANDO DO BD
		userrPJService.deleteUserPJ(id);
		return new ResponseEntity<String>("Cliente Deletado com sucesso!.", HttpStatus.OK);
	}

}
