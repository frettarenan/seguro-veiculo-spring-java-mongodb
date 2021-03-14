package br.com.renanfretta.seguroveiculo.resources;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanfretta.seguroveiculo.dtos.cliente.ClienteInputDTO;
import br.com.renanfretta.seguroveiculo.dtos.cliente.ClienteOutputDTO;
import br.com.renanfretta.seguroveiculo.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@Operation(summary = "findAll", description = "Lista todas os clientes cadastrados")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteOutputDTO.class)))), //
			@ApiResponse(responseCode = "204", description = "Sem resultados"), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@GetMapping
	public ResponseEntity<List<ClienteOutputDTO>> findAll() {
		List<ClienteOutputDTO> list = service.findAll();
		if (list == null || list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}

	@Operation(summary = "findById", description = "Consulta cliente pelo ID")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ClienteOutputDTO.class))), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteOutputDTO> findById(@PathVariable Long id) {
		try {
			ClienteOutputDTO clienteDTO = service.findById(id);
			return ResponseEntity.ok(clienteDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

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
	
	@Operation(summary = "atualizar", description = "Atualiza todo o cadastro do cliente")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ClienteOutputDTO.class))), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteOutputDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteInputDTO clienteInputDTO) {
		ClienteOutputDTO clienteOutputDTO = service.update(id, clienteInputDTO);
		return ResponseEntity.status(HttpStatus.OK).body(clienteOutputDTO);
	}
	
	@Operation(summary = "editarInfomacoesPreenchidas", description = "Edita as informações preenchidas do cadastro do cliente")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ClienteOutputDTO.class))), //
			@ApiResponse(responseCode = "204", description = "Sem conteúdo"), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@PatchMapping(value = "/{id}")
	public ResponseEntity<ClienteOutputDTO> editarInfomacoesPreenchidas(@PathVariable Long id, @Valid @RequestBody ClienteInputDTO clienteInputDTO) {
		ClienteOutputDTO clienteOutputDTO = service.editarInfomacoesPreenchidas(id, clienteInputDTO);
		return ResponseEntity.status(HttpStatus.OK).body(clienteOutputDTO);
	}
	
	@Operation(summary = "deleteById", description = "Deleta cliente pelo ID")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ClienteOutputDTO.class))), //
			@ApiResponse(responseCode = "204", description = "Sem conteúdo"), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso") //
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteOutputDTO> deleteById(@PathVariable Long id) {
		try {
			ClienteOutputDTO clienteDTO = service.deleteById(id);
			return ResponseEntity.ok(clienteDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
