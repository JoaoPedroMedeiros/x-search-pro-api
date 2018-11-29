package com.jpcami.tads.xsearchpro.api.controllers;

import com.jpcami.tads.xsearchpro.api.entities.Mutant;
import com.jpcami.tads.xsearchpro.api.entities.Skill;
import com.jpcami.tads.xsearchpro.api.repositories.SkillRepository;
import com.jpcami.tads.xsearchpro.api.util.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillRepository repository;

    @GetMapping(value = "skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Skill>> index() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping(value = "skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResult> add(@RequestBody @Valid Skill skill) {
        skill.setId(null);

        if (repository.existsByNameIgnoreCase(skill.getName())) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation("Já existe uma habilidade cadastrada com esse nome"));
        }

        Skill saved = repository.save(skill);

        return ResponseEntity.ok(SimpleResult.success(saved.getId().intValue()));
    }
}
