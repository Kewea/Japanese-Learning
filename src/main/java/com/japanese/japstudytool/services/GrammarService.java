package com.japanese.japstudytool.services;

import com.japanese.japstudytool.entities.Grammar;
import com.japanese.japstudytool.exceptions.GrammarException;
import com.japanese.japstudytool.repositories.GrammarRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class GrammarService {
    Logger logger = LoggerFactory.getLogger(GrammarService.class);
    private GrammarRepository grammarRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public GrammarService(
            GrammarRepository grammarRepository,
            MongoTemplate mongoTemplate
    )
    {
        this.grammarRepository = grammarRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Grammar saveOrUpdateGrammar(Grammar grammar) {
        try {
            return grammarRepository.save(grammar);
        } catch(Exception ex) {
            throw new GrammarException("Grammar cannot be saved or updated");
        }
    }

    /*public Grammar updateGrammar(Grammar grammar) {
        Grammar updateGrammar = mongoTemplate.update(Grammar.class)
                .matching(new Query(Criteria.where("id").is(grammar.getId())))
                .apply(new Update().set("name", grammar.getName()).set("explanation", grammar.getExplanation()))
                .withOptions(FindAndModifyOptions.options().returnNew(true)) // Now return the newly updated document when updating
                .findAndModifyValue();

        return updateGrammar;
    }*/

    public Iterable<Grammar> findAllGrammar() {
        return grammarRepository.findAll();
    }

    public Grammar findGrammarById(String id) {
        return grammarRepository.findById(id)
                .orElseThrow(() -> new GrammarException("Grammar cannot be found"));

    }

    public Iterable<Grammar> findGrammarByTitle(String title) {
        return grammarRepository.findByTitleContaining(title);
    }

    public void deleteGrammarById(String id) {
        Grammar optionalGrammar = grammarRepository.findById(id)
                                    .orElseThrow(() -> new GrammarException("Grammar does not exist"));

        grammarRepository.delete(optionalGrammar);
    }
}
