package com.japanese.japstudytool.controllers;

import com.japanese.japstudytool.entities.ApiResponse;
import com.japanese.japstudytool.entities.Grammar;
import com.japanese.japstudytool.services.GrammarService;
import com.japanese.japstudytool.services.ValidationErrorService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/grammar")
public class GrammarController {
    Logger logger = LoggerFactory.getLogger(GrammarController.class);
    private GrammarService grammarService;
    private ValidationErrorService validationErrorService;

    @Autowired
    public GrammarController(GrammarService grammarService, ValidationErrorService validationErrorService) {
        this.grammarService = grammarService;
        this.validationErrorService = validationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> addGrammar(@Valid @RequestBody Grammar grammar, BindingResult result) {
        Map<String, String > errorMap = validationErrorService.mapValidationError(result);

        if(!errorMap.isEmpty()) {
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(grammarService.saveOrUpdateGrammar(grammar), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllGrammar() {
        return new ResponseEntity<>(grammarService.findAllGrammar(), HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity<?> getGrammarByTitle(@RequestBody Map<String, String> title) {
        return new ResponseEntity<>(grammarService.findGrammarByTitle(title.get("title")), HttpStatus.OK);
    }

    @GetMapping("/id/{grammarId}")
    public  ResponseEntity<?> getGrammarById(@PathVariable String grammarId) {
        return new ResponseEntity<>(grammarService.findGrammarById(grammarId), HttpStatus.OK);
    }

    @DeleteMapping("/id/{grammarId}")
    public ResponseEntity<?> deleteGrammarById(@PathVariable String grammarId) {
        grammarService.deleteGrammarById(grammarId);
        return new ResponseEntity<>(new ApiResponse("Grammar was deleted"), HttpStatus.OK);
    }
}
