package br.com.empiricus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empiricus.model.UserLoginPF;

@Repository
public interface UserPFRepository extends JpaRepository <UserLoginPF, Long>{
	
	public Optional<UserLoginPF> findByCpf(String clientePF);

}
