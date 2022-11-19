package br.com.empiricus.service;




import br.com.empiricus.model.Ativos;
import br.com.empiricus.model.Indicadores;

import java.util.List;

public interface AtivosService {


    List<Ativos> getAllAtivos();
    List<Ativos> getAtivosByNome(String nome);
    Ativos getAtivosById(long id);
    Ativos saveAtivos(Ativos ativos);
    Ativos updateAtivosByNome(Ativos cliente,String nome);
    void deleteAtivos(String nome);
}
