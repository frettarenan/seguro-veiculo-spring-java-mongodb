package br.com.renanfretta.seguroveiculo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.renanfretta.seguroveiculo.entities.Apolice;

public interface ApoliceRepository extends MongoRepository<Apolice, Long> {

}
