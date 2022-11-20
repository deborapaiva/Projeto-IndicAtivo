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

import br.com.empiricus.model.ClientePJ;
import br.com.empiricus.service.UserPJService;
import br.com.empiricus.service.UserPJService.UserrPJService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuarios/PJ")
@SecurityRequirement(name = "Bearer Authentication") // Não deixar que alterem a informações sem autenticação
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioPJController {
	
	@Autowired
	private UserrPJService userrPJService;

	@GetMapping
	public List<ClientePJ> getAllUserPJ(){
		return userrPJService.getAllUserPJ();
	}
	

	@GetMapping("{id}")
	public ResponseEntity<ClientePJ> getUserPJById(@PathVariable("id") long clientePJid){
		return new ResponseEntity<ClientePJ>(userrPJService.getUserPJById(clientePJid), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<ClientePJ> updateUserPJ(@PathVariable("id") long id
												,@RequestBody ClientePJ userLoginPJ){
		return new ResponseEntity<ClientePJ>(userrPJService.updateUserPJ(userLoginPJ, id), HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserPJ(@PathVariable("id") long id){
		//DELETANDO DO BD
		userrPJService.deleteUserPJ(id);
		return new ResponseEntity<String>("Cliente Deletado com sucesso!.", HttpStatus.OK);
	}

}
