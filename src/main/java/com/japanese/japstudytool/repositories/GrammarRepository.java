package com.japanese.japstudytool.repositories;

import com.japanese.japstudytool.entities.Grammar;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GrammarRepository extends MongoRepository<Grammar, String> {
    Optional<Grammar> findById(String id);
    Iterable<Grammar> findByTitleContaining(String name);
}
