package br.com.renanfretta.seguroveiculo.dtos.cliente;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClienteOutputDTO implements Serializable {

	private static final long serialVersionUID = -623730930736609093L;

	private Long id;

	private String nome;

	private String cpf;

	private String uf;

	private String cidade;

}
