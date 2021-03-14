package br.com.renanfretta.seguroveiculo.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "cliente")
@Data
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = -7785268935833421151L;

	@Id
	@NonNull
	private Long id;
	
	@NonNull
	private String nome;
	
	@NonNull
	@Indexed(unique=true)
	private String cpf;
	
	@NonNull
	private String uf;
	
	@NonNull
	private String cidade;
	
}
