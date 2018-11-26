package com.jpcami.tads.xsearchpro.api.controllers;

import com.jpcami.tads.xsearchpro.api.entities.Mutant;
import com.jpcami.tads.xsearchpro.api.repositories.MutantRepository;
import com.jpcami.tads.xsearchpro.api.util.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class MutantController {

    @Autowired
    private MutantRepository repository;

    @GetMapping(value = "mutants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mutant>> index() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping(value = "mutants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResult> add(@RequestBody @Valid Mutant mutant) {
        mutant.setId(null);

        if (repository.existsByNameIgnoreCase(mutant.getName())) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation("Já existe mutante cadastrado com esse nome"));
        }

        repository.save(mutant);
        return ResponseEntity.ok(SimpleResult.success());
    }

    @PutMapping(value = "mutants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResult> alter(@RequestBody @Valid Mutant mutant) {
        Optional<Mutant> optional = repository.findById(mutant.getId());

        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation("Mutante com id [" + mutant.getId() + "] não existe"));
        }

        Mutant original = optional.get();
        original.setName(mutant.getName());
        original.setPhoto(mutant.getPhoto());
        repository.save(original);

        return ResponseEntity.ok(SimpleResult.success());
    }
}
