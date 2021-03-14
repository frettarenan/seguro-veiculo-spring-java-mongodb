package br.com.renanfretta.seguroveiculo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renanfretta.seguroveiculo.repositories.ApoliceRepository;

@Service
public class ApoliceService {
	
	@Autowired
    private ApoliceRepository repository;

}
