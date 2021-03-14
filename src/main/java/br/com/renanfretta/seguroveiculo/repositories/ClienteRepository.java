package br.com.renanfretta.seguroveiculo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.renanfretta.seguroveiculo.entities.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, Long> {

}
