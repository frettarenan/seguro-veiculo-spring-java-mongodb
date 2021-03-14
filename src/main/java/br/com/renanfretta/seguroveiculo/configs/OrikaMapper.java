package br.com.renanfretta.seguroveiculo.configs;

import org.springframework.stereotype.Component;

import br.com.renanfretta.seguroveiculo.dtos.apolice.ApoliceInputDTO;
import br.com.renanfretta.seguroveiculo.dtos.apolice.ApoliceOutputDTO;
import br.com.renanfretta.seguroveiculo.dtos.cliente.ClienteInputDTO;
import br.com.renanfretta.seguroveiculo.dtos.cliente.ClienteOutputDTO;
import br.com.renanfretta.seguroveiculo.dtos.consultaapolice.ConsultaApoliceOutputDTO;
import br.com.renanfretta.seguroveiculo.entities.Apolice;
import br.com.renanfretta.seguroveiculo.entities.Cliente;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class OrikaMapper extends OrikaMapperBase {

	public OrikaMapper() {
		if (mapperFacade != null)
			return;

		MapperFactory factory = new DefaultMapperFactory.Builder().build();

		factory.classMap(Cliente.class, ClienteInputDTO.class) //
				.constructorA().constructorB().mapNulls(true).mapNullsInReverse(true) //
				.byDefault().register();

		factory.classMap(Cliente.class, ClienteOutputDTO.class) //
				.constructorA().constructorB().mapNulls(true).mapNullsInReverse(true) //
				.byDefault().register();

		factory.classMap(Apolice.class, ApoliceInputDTO.class) //
				.constructorA().constructorB().mapNulls(true).mapNullsInReverse(true) //
				.byDefault().register();

		factory.classMap(Apolice.class, ApoliceOutputDTO.class) //
				.constructorA().constructorB().mapNulls(true).mapNullsInReverse(true) //
				.byDefault().register();

		factory.classMap(Apolice.class, ConsultaApoliceOutputDTO.class) //
				.constructorA().constructorB().mapNulls(true).mapNullsInReverse(true) //
				.byDefault().register();

		mapperFacade = factory.getMapperFacade();
	}

}
