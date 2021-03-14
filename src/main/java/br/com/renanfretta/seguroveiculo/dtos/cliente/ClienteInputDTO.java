package br.com.renanfretta.seguroveiculo.dtos.cliente;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClienteInputDTO implements Serializable {

	private static final long serialVersionUID = -2686338143298920917L;
	
	private String nome;
	
	private String cpf;
	
	private String uf;
	
	private String cidade;

}
