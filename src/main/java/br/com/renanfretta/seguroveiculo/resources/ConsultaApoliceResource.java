package br.com.renanfretta.seguroveiculo.resources;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanfretta.seguroveiculo.dtos.consultaapolice.ConsultaApoliceOutputDTO;
import br.com.renanfretta.seguroveiculo.services.ApoliceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/consulta-apolice")
public class ConsultaApoliceResource {

	@Autowired
	private ApoliceService service;

	@Operation(summary = "findByNumero", description = "Consulta apólice pelo número")
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ConsultaApoliceOutputDTO.class))), //
			@ApiResponse(responseCode = "401", description = "Não autorizado"), //
			@ApiResponse(responseCode = "403", description = "Não possui permissão para acessar o recurso"), //
			@ApiResponse(responseCode = "404", description = "Não encontrado") //
	})
	@GetMapping(value = "/{numero}")
	public ResponseEntity<ConsultaApoliceOutputDTO> consultaPeloNumero(@PathVariable Long numero) {
		try {
			ConsultaApoliceOutputDTO consultaApoliceOutputDTO = service.consultaPeloNumero(numero);
			return ResponseEntity.ok(consultaApoliceOutputDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
