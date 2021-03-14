package br.com.renanfretta.seguroveiculo.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "sequence")
@Data
public class Sequence implements Serializable {

	private static final long serialVersionUID = -1884542716552065224L;

	@Id
    private String id;
	
    private long sequence;
	
}
