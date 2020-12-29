package com.restful.gestaodepedidos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restful.gestaodepedidos.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	/*@Quary: essa é uma sintaxe chamada Jpql. A letra 'u' faz parte 
	 * da sintaxe. A sintaxe 'email = ?1' e [password = ?2]faz referencia a uma consulta 
	 * em ordem sequencial do paramentro login(String email, String password) sendo 'String email'
	 * do paramétro a primeira ordem[email = ?1] enquanto o paramétro 'String password'
	 * compõe a segunda ordem da consulta.
	 * 
	 * NOTA: Note que na sintaxe @Query o 'user' está em letra menúscula pois faz referência
	 * a tabela do banco de dados.
	 * * 
	 * */
	
	/*@Query: A query deve obdeser a ordem implicita dos paramétros com segue abaixo:
	 * */
	@Query("SELECT u FROM user u WHERE email = ?1 AND password = ?2")
	public Optional<User> login(String email, String password);
								//index 1  , index 2

}
