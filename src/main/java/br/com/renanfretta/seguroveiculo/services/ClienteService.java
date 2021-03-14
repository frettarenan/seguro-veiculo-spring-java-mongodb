package br.com.renanfretta.seguroveiculo.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renanfretta.seguroveiculo.configs.MessagesProperty;
import br.com.renanfretta.seguroveiculo.configs.OrikaMapper;
import br.com.renanfretta.seguroveiculo.dtos.cliente.ClienteInputDTO;
import br.com.renanfretta.seguroveiculo.dtos.cliente.ClienteOutputDTO;
import br.com.renanfretta.seguroveiculo.entities.Cliente;
import br.com.renanfretta.seguroveiculo.enums.MessagesPropertyEnum;
import br.com.renanfretta.seguroveiculo.enums.SequenceEnum;
import br.com.renanfretta.seguroveiculo.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private MessagesProperty messagesProperty;

	@Autowired
	private OrikaMapper orikaMapper;

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private SequenceService sequenceService;

	public ClienteOutputDTO findById(Long id) {
		Cliente cliente = repository.findById(id).orElseThrow(() -> new NoSuchElementException(messagesProperty.getMessage(MessagesPropertyEnum.ERRO__REGISTRO_NAO_ENCONTRADO_ENTIDADE_CLIENTE)));
		ClienteOutputDTO dto = orikaMapper.map(cliente, ClienteOutputDTO.class);
		return dto;
	}

	public ClienteOutputDTO save(ClienteInputDTO clienteInputDTO) {
		Cliente cliente = orikaMapper.map(clienteInputDTO, Cliente.class);
		cliente.setId(sequenceService.getNextSequence(SequenceEnum.CLIENTE_ID_SEQ));
		cliente = repository.save(cliente);
		ClienteOutputDTO clienteOutputDTO = findById(cliente.getId());
		return clienteOutputDTO;
	}

}
