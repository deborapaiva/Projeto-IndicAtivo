package br.com.empiricus.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private UserLoginPJ userLoginPJ;

    @JsonProperty("userLoginPJ") private void unpackNested(Long userLoginPJId) {
        this.userLoginPJ = new UserLoginPJ(); userLoginPJ.setId(userLoginPJId);
    }
    
    
}
