package br.com.renanfretta.seguroveiculo.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.renanfretta.seguroveiculo.dtos.cliente.ClienteInputDTO;
import br.com.renanfretta.seguroveiculo.dtos.cliente.ClienteOutputDTO;
import br.com.renanfretta.seguroveiculo.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ClienteService service;

	@Operation(summary = "salvar", description = "Cadastra um novo cliente")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "201", description = "Recurso criado", content = @Content(schema = @Schema(implementation = ClienteOutputDTO.class))), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@PostMapping
	public ResponseEntity<ClienteOutputDTO> salvar(@Valid @RequestBody ClienteInputDTO clienteInputDTO) {
		ClienteOutputDTO clienteOutputDTO = service.save(clienteInputDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteOutputDTO);
	}

}
