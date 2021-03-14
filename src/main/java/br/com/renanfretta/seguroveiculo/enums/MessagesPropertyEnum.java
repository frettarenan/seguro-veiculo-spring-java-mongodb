package br.com.renanfretta.seguroveiculo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessagesPropertyEnum {

	ERRO__REGISTRO_NAO_ENCONTRADO_ENTIDADE_CLIENTE("erro.registro-nao-encontrado-entidade-cliente");

	private String key;

}