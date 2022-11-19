package br.com.empiricus.model;

//BY THIAGOSILVA
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "UserLoginPJ")
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginPJ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotNull
	private String email;

	@NotNull
	@Column(name = "cnpj", unique = true)
	@Size(min = 14, max = 14)
	private String cnpj;

	@NotNull
	@Column(name = "telefone")
	private String telefone;

	@NotNull
	@Size(min = 6, max = 100)
	private String senha;

	@OneToMany(mappedBy = "userLoginPJ", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("userLoginPJ")
	private List<Ativos> ativos;

	private String token;
	
}
