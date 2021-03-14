package br.com.renanfretta.seguroveiculo.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "apolice")
@Data
public class Apolice implements Serializable {

	private static final long serialVersionUID = 6577231332327703638L;

	@Id
	@NonNull
	private Long id;
	
	@NonNull
	private Long numero;
	
	@NonNull
	private Cliente cliente;
	
	@NonNull
	private Date vigenciaInicio;
	
	@NonNull
	private Date vigenciaFim;
	
	@NonNull
	private String placaVeiculo;
	
	private Double valor;
	
}
