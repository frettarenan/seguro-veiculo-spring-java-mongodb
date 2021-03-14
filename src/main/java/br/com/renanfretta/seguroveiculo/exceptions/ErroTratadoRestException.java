package br.com.renanfretta.seguroveiculo.exceptions;

import lombok.Getter;

@Getter
public class ErroTratadoRestException extends Exception {

	private static final long serialVersionUID = -8726863693195553646L;

	public ErroTratadoRestException(String message) {
		super(message);
	}

}