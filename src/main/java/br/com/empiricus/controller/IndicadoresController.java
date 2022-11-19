package br.com.empiricus.controller;


import java.util.List;

import br.com.empiricus.model.Indicadores;
import br.com.empiricus.service.IndicadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/Indicadores")
public class IndicadoresController {

    @Autowired
    private IndicadoresService indicadoresService;


    @GetMapping
    public List<Indicadores> getAllIndicIndicadores(){
        return indicadoresService.getAllIndicadores();
    }


    @GetMapping("{id}")
    public ResponseEntity<Indicadores> getIndicadoresById(@PathVariable long indicadoresId){
        return new ResponseEntity<Indicadores>(indicadoresService.getIndicadoresById(indicadoresId), HttpStatus.OK);
    }


    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<Indicadores>> getIndicadoresByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(indicadoresService.getIndicadoresByNome(nome));
    }

 /*   @PostMapping()
    public ResponseEntity<Indicadores> saveIndicadores(@RequestBody Indicadores indicadores){
        return new ResponseEntity<Indicadores>(indicadoresService.saveIndicadores(indicadores), HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    public ResponseEntity<Indicadores> updateIndicadores(@PathVariable("id") long id
            , @RequestBody Indicadores Indicadores){
        return new ResponseEntity<Indicadores>(indicadoresService.updateIndicadores(Indicadores, id), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteIndicadores(@PathVariable("id") long id){

        indicadoresService.deleteIndicadores(id);
        return new ResponseEntity<String>("Indicadores Deletados!.", HttpStatus.OK);
    }
*/

}
