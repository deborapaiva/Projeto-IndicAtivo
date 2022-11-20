package br.com.empiricus.controller;


import br.com.empiricus.model.Ativos;
import br.com.empiricus.service.AtivosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/Ativos")
@Tag(name = "Ativos", description = "Controller para requisições Ativos -> variáveis para cálculo")
public class AtivosController {

    @Autowired
    private AtivosService ativosService;


    @GetMapping
    @Operation(
    		summary = ("Apresentação Ativos"),
    		description = ("Apresenta os ativos cadastrados pela empresa responsável"),
    		tags = {"Ativos"}, 
    		responses = {
    				@ApiResponse(description = "Online", responseCode = "200", 
    						content = @Content(mediaType = "application/json",
    						array = @ArraySchema(schema = @Schema(implementation = Ativos.class))
    						)) }
    		)
    public List<Ativos> getAllAtivos(){
        return ativosService.getAllAtivos();
    }


    @GetMapping("{id}")
    public ResponseEntity<Ativos> getAtivosById(@PathVariable long id){
        return new ResponseEntity<Ativos>(ativosService.getAtivosById(id), HttpStatus.OK);
    }


    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<Ativos>> getAtivosByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(ativosService.getAtivosByNome(nome));
    }

    @PostMapping()
    public ResponseEntity<Ativos> saveAtivos(@RequestBody Ativos ativos){
        return new ResponseEntity<Ativos>(ativosService.saveAtivos(ativos ), HttpStatus.CREATED);

    }

    @PutMapping("{nome}")
    public ResponseEntity<Ativos> updateAtivos(@PathVariable("nome") String nome
            ,@RequestBody Ativos ativos){
        return new ResponseEntity<Ativos>(ativosService.updateAtivosByNome(ativos, nome), HttpStatus.OK);
    }


    @DeleteMapping("{nome}")
    public ResponseEntity<String> deleteAtivos(@PathVariable("nome") String nome){

        ativosService.deleteAtivos(nome);
        return new ResponseEntity<String>("Ativos Deletados!.", HttpStatus.OK);
    }


}
