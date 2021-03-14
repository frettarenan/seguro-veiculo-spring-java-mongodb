package br.com.renanfretta.seguroveiculo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "apolice")
@Data
public class Apolice implements Serializable {

	private static final long serialVersionUID = 6577231332327703638L;

	@Id
	@NotNull
	private Long id;
	
	@NotNull
	private Long numero;
	
	@NotNull
	@DBRef
	private Cliente cliente;
	
	@NotNull
	private Date vigenciaInicio;
	
	@NotNull
	private Date vigenciaFim;
	
	@NotNull
	private String placaVeiculo;
	
	private Double valor;
	
}
