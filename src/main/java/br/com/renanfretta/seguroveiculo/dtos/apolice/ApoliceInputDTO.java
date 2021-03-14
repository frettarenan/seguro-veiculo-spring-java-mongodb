package br.com.renanfretta.seguroveiculo.dtos.apolice;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.renanfretta.seguroveiculo.entities.Cliente;
import lombok.Data;

@Data
public class ApoliceInputDTO implements Serializable {

	private static final long serialVersionUID = 6213305072159828727L;

	private Cliente cliente;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vigenciaInicio;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vigenciaFim;

	private String placaVeiculo;

	private Double valor;

}
