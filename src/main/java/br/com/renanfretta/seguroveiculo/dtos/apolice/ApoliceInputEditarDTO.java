package br.com.renanfretta.seguroveiculo.dtos.apolice;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApoliceInputEditarDTO implements Serializable {

	private static final long serialVersionUID = -4124866844291364878L;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vigenciaInicio;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vigenciaFim;

	private String placaVeiculo;

	private Double valor;

}
