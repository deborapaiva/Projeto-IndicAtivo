package br.com.empiricus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empiricus.model.UserLoginPJ;

@Repository
public interface UserPJRepository extends JpaRepository <UserLoginPJ, Long>{
	
	public Optional<UserLoginPJ> findByCnpj(String clientePJ);

}
