package br.com.renanfretta.seguroveiculo.dtos.apolice;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.renanfretta.seguroveiculo.entities.Cliente;
import lombok.Data;

@Data
public class ApoliceOutputDTO implements Serializable {

	private static final long serialVersionUID = -4117985022797155748L;

	private Long id;

	private Long numero;

	private Cliente cliente;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vigenciaInicio;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vigenciaFim;

	private String placaVeiculo;

	private Double valor;

}
