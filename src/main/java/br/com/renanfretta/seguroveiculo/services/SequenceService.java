package br.com.renanfretta.seguroveiculo.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import br.com.renanfretta.seguroveiculo.entities.Sequence;
import br.com.renanfretta.seguroveiculo.enums.SequenceEnum;

@Service
public class SequenceService {

	@Autowired
	private MongoOperations mongoOperations;

	public long getNextSequence(SequenceEnum sequenceEnum) {
		Sequence counter = mongoOperations.findAndModify(query(where("_id").is(sequenceEnum.name())), //
				new Update().inc("sequence", 1), options().returnNew(true).upsert(true), Sequence.class);
		return counter.getSequence();
	}

}
