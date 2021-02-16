package com.restful.gestaodepedidos.security;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restful.gestaodepedidos.constant.SecurityConstants;
import com.restful.gestaodepedidos.dto.UserLoginResponseDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */


/**
 * Criação do token.
 */
@Component
public class JwtManager {
	
	public UserLoginResponseDto createToken(String email, List<String> roles) {
		Calendar calendar = Calendar.getInstance();//Busca a data atual.
		//Apartir da data atual adicione mais 5 dias para expiração do token.
		calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAYS);
		String jwt = Jwts.builder()
				.setSubject(email)// Identifica qual entidade o token pertence; Quem o criou.
				.setExpiration(calendar.getTime()) //Data de expiração.
				.claim(SecurityConstants.JWT_ROLE_KEY, roles)//Informação da Claim
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())//Assinatura e os byts da chave secreta.
				.compact(); //Cria os token
		
		Long expire = calendar.getTimeInMillis();// transforma os dias em milesegundos.
		
		return new UserLoginResponseDto(jwt, expire, SecurityConstants.JWT_PROVIDER);
	}
	
	//Métdo que valida o token.
	public Claims parseToken(String jwt) {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstants.API_KEY.getBytes()) //analisa a chave do token
				.parseClaimsJws(jwt)
				.getBody();
		
		return claims;
				
	}

}
