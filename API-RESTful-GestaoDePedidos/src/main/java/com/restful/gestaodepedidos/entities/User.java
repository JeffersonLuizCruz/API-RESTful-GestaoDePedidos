package com.restful.gestaodepedidos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.restful.gestaodepedidos.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */



@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "user_access")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 100, nullable = false, unique = true)
	private String email;
	
	/**
	 * Nota: Notação depreciada
	 * @Getter(onMethod = @__(@JsonIgnore)) -> Ignora o atributo durante a serialização.
	 * @Setter(onMethod = @__(@JsonProperty) -> Deserializa o atributo json para objeto java. 
	 * 
	 * Substituída:
	 * 
	 *  @Getter(onMethod_=@JsonIgnore)
	 *  @Setter(onMethod_=@JsonProperty)
	 * */
	@Setter(onMethod_=@JsonProperty)
	@Getter(onMethod_=@JsonIgnore)
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Getter(onMethod_=@JsonIgnore)
	@OneToMany(mappedBy = "owner") 
	private List<Request> request = new ArrayList<>();
	
	@Getter(onMethod_=@JsonIgnore)
	@OneToMany(mappedBy = "owner")
	private List<RequestStage> stages = new ArrayList<>();

}