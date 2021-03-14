package br.com.renanfretta.seguroveiculo.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renanfretta.seguroveiculo.configs.MessagesProperty;
import br.com.renanfretta.seguroveiculo.configs.OrikaMapper;
import br.com.renanfretta.seguroveiculo.dtos.apolice.ApoliceInputDTO;
import br.com.renanfretta.seguroveiculo.dtos.apolice.ApoliceInputEditarDTO;
import br.com.renanfretta.seguroveiculo.dtos.apolice.ApoliceOutputDTO;
import br.com.renanfretta.seguroveiculo.dtos.consultaapolice.ConsultaApoliceOutputDTO;
import br.com.renanfretta.seguroveiculo.entities.Apolice;
import br.com.renanfretta.seguroveiculo.enums.MessagesPropertyEnum;
import br.com.renanfretta.seguroveiculo.enums.SequenceEnum;
import br.com.renanfretta.seguroveiculo.repositories.ApoliceRepository;

@Service
public class ApoliceService {
	
	@Autowired
	private MessagesProperty messagesProperty;

	@Autowired
	private OrikaMapper orikaMapper;
	
	@Autowired
    private ApoliceRepository repository;
	
	@Autowired
	private SequenceService sequenceService;

	public List<ApoliceOutputDTO> findAll() {
		List<Apolice> list = repository.findAll();
		List<ApoliceOutputDTO> listDTO = orikaMapper.mapAsList(list, ApoliceOutputDTO.class);
		return listDTO;
	}

	public ApoliceOutputDTO findById(Long id) {
		Apolice apolice = repository.findById(id).orElseThrow(() -> new NoSuchElementException(messagesProperty.getMessage(MessagesPropertyEnum.ERRO__REGISTRO_NAO_ENCONTRADO_ENTIDADE_APOLICE)));
		ApoliceOutputDTO dto = orikaMapper.map(apolice, ApoliceOutputDTO.class);
		return dto;
	}

	public ApoliceOutputDTO save(ApoliceInputDTO apoliceInputDTO) {
		Apolice apolice = orikaMapper.map(apoliceInputDTO, Apolice.class);
		apolice.setId(sequenceService.getNextSequence(SequenceEnum.APOLICE_ID_SEQ));
		apolice.setNumero(sequenceService.getNextSequence(SequenceEnum.APOLICE_NUMERO_SEQ));
		apolice = repository.save(apolice);
		ApoliceOutputDTO apoliceOutputDTO = findById(apolice.getId());
		return apoliceOutputDTO;
	}

	public ApoliceOutputDTO deleteById(Long id) {
		Apolice apolice = repository.findById(id).orElseThrow(() -> new NoSuchElementException(messagesProperty.getMessage(MessagesPropertyEnum.ERRO__REGISTRO_NAO_ENCONTRADO_ENTIDADE_APOLICE)));
		repository.delete(apolice);
		ApoliceOutputDTO dto = orikaMapper.map(apolice, ApoliceOutputDTO.class);
		return dto;
	}

	public ApoliceOutputDTO editarInfomacoesPreenchidas(Long id, ApoliceInputEditarDTO apoliceInputEditarDTO) {
		Apolice apolice = repository.findById(id).orElseThrow(() -> new NoSuchElementException(messagesProperty.getMessage(MessagesPropertyEnum.ERRO__REGISTRO_NAO_ENCONTRADO_ENTIDADE_APOLICE)));
		
		if (apoliceInputEditarDTO.getVigenciaInicio() != null)
			apolice.setVigenciaInicio(apoliceInputEditarDTO.getVigenciaInicio());
		
		if (apoliceInputEditarDTO.getVigenciaFim() != null)
			apolice.setVigenciaFim(apoliceInputEditarDTO.getVigenciaFim());
		
		if (apoliceInputEditarDTO.getPlacaVeiculo() != null)
			apolice.setPlacaVeiculo(apoliceInputEditarDTO.getPlacaVeiculo());
		
		if (apoliceInputEditarDTO.getValor() != null)
			apolice.setValor(apoliceInputEditarDTO.getValor());
		
		apolice = repository.save(apolice);
		
		ApoliceOutputDTO apoliceOutputDTO = findById(apolice.getId());
		
		return apoliceOutputDTO;
	}
	
	public ApoliceOutputDTO update(Long id, ApoliceInputEditarDTO apoliceInputEditarDTO) {
		Apolice apoliceBD = repository.findById(id).orElseThrow(() -> new NoSuchElementException(messagesProperty.getMessage(MessagesPropertyEnum.ERRO__REGISTRO_NAO_ENCONTRADO_ENTIDADE_APOLICE)));
		
		Apolice apolice = orikaMapper.map(apoliceInputEditarDTO, Apolice.class);
		apolice.setId(apoliceBD.getId());
		apolice.setNumero(apoliceBD.getNumero());
		apolice.setCliente(apoliceBD.getCliente());
		
		apolice = repository.save(apolice);
		ApoliceOutputDTO apoliceOutputDTO = findById(apolice.getId());
		
		return apoliceOutputDTO;
	}

	public ConsultaApoliceOutputDTO consultaPeloNumero(Long numero) {
		Apolice apolice = repository.findByNumero(numero).orElseThrow(() -> new NoSuchElementException(messagesProperty.getMessage(MessagesPropertyEnum.ERRO__REGISTRO_NAO_ENCONTRADO_ENTIDADE_APOLICE_NUMERO)));
		ConsultaApoliceOutputDTO dto = orikaMapper.map(apolice, ConsultaApoliceOutputDTO.class);
		return dto;
	}

}
