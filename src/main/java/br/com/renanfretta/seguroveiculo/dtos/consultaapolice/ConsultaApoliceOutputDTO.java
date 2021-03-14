package br.com.renanfretta.seguroveiculo.dtos.consultaapolice;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.renanfretta.seguroveiculo.entities.Cliente;
import lombok.Data;

@Data
public class ConsultaApoliceOutputDTO implements Serializable {

	private static final long serialVersionUID = 1948412561036445417L;

	private Long id;

	private Long numero;

	private Cliente cliente;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vigenciaInicio;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vigenciaFim;

	private String placaVeiculo;

	private Double valor;
	
	public boolean isVencida() {
		return new Date().after(vigenciaFim);
	}
	
	public long getQuantidadeDiasVencimento() {
		long diffInMillies;
		if (isVencida())
			diffInMillies = Math.abs(vigenciaFim.getTime() - new Date().getTime());
		else
			diffInMillies = Math.abs(new Date().getTime() - vigenciaFim.getTime());
		
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}

}
