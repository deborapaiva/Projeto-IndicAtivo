package br.com.empiricus.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Indicadores")
@AllArgsConstructor
@NoArgsConstructor
public class Indicadores extends CalculaIndicadores {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = true, unique = true)
    private String nome;

    
    @NotNull
    private Double pl;
    
    @NotNull
    private Double roe;
    
    @NotNull
    private Double pvpa;
   
    @NotNull
    private Double ev;
   
    @NotNull
    private Double ebitda;
    
    @NotNull
    private Double evebitda;
    
    @NotNull
    private Double dividendYield;
    
    @NotNull
    private Double lpa;

    
}
