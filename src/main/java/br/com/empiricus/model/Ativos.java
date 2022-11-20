package br.com.empiricus.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "Ativos")
@AllArgsConstructor
@NoArgsConstructor
public class Ativos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(unique = true)
    private String nome;
    @NotNull
    private Double lucroPorAcao;
    @NotNull
    private Double dividaTotal;
    @NotNull
    private Double caixaEEquivalentes;
    @NotNull
    private Double receitaLiquida;
    @NotNull
    private Double lucroLiquido;
    @NotNull
    private Double lucroOperacionalLiquido;
    @NotNull
    private Double acoesTotais;
    @NotNull
    private Double cotacaoAcao;
    @NotNull
    private Double precoAcao;
    @NotNull
    private Double valorPatrimonialPorAcao;
    @NotNull
    private Double patrimonioLiquido;
    @NotNull
    private Double impostos;
    @NotNull
    private Double juros;
    @NotNull
    private Double depreciacao;
    @NotNull
    private Double amortizacao;
    @NotNull
    private Double dividendo;

    @ManyToOne
    @JsonIgnoreProperties("ativos")
    @Schema(hidden = true)
    private ClientePJ clientePJ;

    @JsonProperty("clientePJ") private void unpackNested(Long clientePJId) {
        this.clientePJ = new ClientePJ(); clientePJ.setId(clientePJId);
    }

    
    
}
