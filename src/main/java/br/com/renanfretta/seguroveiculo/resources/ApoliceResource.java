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

import br.com.renanfretta.seguroveiculo.dtos.apolice.ApoliceInputDTO;
import br.com.renanfretta.seguroveiculo.dtos.apolice.ApoliceInputEditarDTO;
import br.com.renanfretta.seguroveiculo.dtos.apolice.ApoliceOutputDTO;
import br.com.renanfretta.seguroveiculo.services.ApoliceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/apolice")
public class ApoliceResource {

	@Autowired
	private ApoliceService service;

	@Operation(summary = "findAll", description = "Lista todas os apólices cadastrados")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ApoliceOutputDTO.class)))), //
			@ApiResponse(responseCode = "204", description = "Sem resultados"), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@GetMapping
	public ResponseEntity<List<ApoliceOutputDTO>> findAll() {
		List<ApoliceOutputDTO> list = service.findAll();
		if (list == null || list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}

	@Operation(summary = "findById", description = "Consulta apólice pelo ID")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ApoliceOutputDTO.class))), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<ApoliceOutputDTO> findById(@PathVariable Long id) {
		try {
			ApoliceOutputDTO apoliceDTO = service.findById(id);
			return ResponseEntity.ok(apoliceDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "salvar", description = "Cadastra um novo apólice")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "201", description = "Recurso criado", content = @Content(schema = @Schema(implementation = ApoliceOutputDTO.class))), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@PostMapping
	public ResponseEntity<ApoliceOutputDTO> salvar(@Valid @RequestBody ApoliceInputDTO apoliceInputDTO) {
		ApoliceOutputDTO apoliceOutputDTO = service.save(apoliceInputDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(apoliceOutputDTO);
	}
	
	@Operation(summary = "atualizar", description = "Atualiza todo o cadastro do apólice")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ApoliceOutputDTO.class))), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<ApoliceOutputDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ApoliceInputEditarDTO apoliceInputEditarDTO) {
		ApoliceOutputDTO apoliceOutputDTO = service.update(id, apoliceInputEditarDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apoliceOutputDTO);
	}
	
	@Operation(summary = "editarInfomacoesPreenchidas", description = "Edita as informações preenchidas do cadastro do apólice")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ApoliceOutputDTO.class))), //
			@ApiResponse(responseCode = "204", description = "Sem conteúdo"), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@PatchMapping(value = "/{id}")
	public ResponseEntity<ApoliceOutputDTO> editarInfomacoesPreenchidas(@PathVariable Long id, @Valid @RequestBody ApoliceInputEditarDTO apoliceInputEditarDTO) {
		ApoliceOutputDTO apoliceOutputDTO = service.editarInfomacoesPreenchidas(id, apoliceInputEditarDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apoliceOutputDTO);
	}
	
	@Operation(summary = "deleteById", description = "Deleta apólice pelo ID")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ApoliceOutputDTO.class))), //
			@ApiResponse(responseCode = "204", description = "Sem conteúdo"), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso") //
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ApoliceOutputDTO> deleteById(@PathVariable Long id) {
		try {
			ApoliceOutputDTO apoliceDTO = service.deleteById(id);
			return ResponseEntity.ok(apoliceDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
