package br.com.renanfretta.seguroveiculo.entities;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "cliente")
@Data
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = -7785268935833421151L;

	@Id
	@NotNull
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Indexed(name = "unique_cpf_cliente", unique=true)
	@CPF
	private String cpf;
	
	@NotNull
	private String uf;
	
	@NotNull
	private String cidade;
	
}
