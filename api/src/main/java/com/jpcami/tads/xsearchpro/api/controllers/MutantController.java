package com.jpcami.tads.xsearchpro.api.controllers;

import com.jpcami.tads.xsearchpro.api.entities.Mutant;
import com.jpcami.tads.xsearchpro.api.entities.MutantSkills;
import com.jpcami.tads.xsearchpro.api.entities.MutantSkillsId;
import com.jpcami.tads.xsearchpro.api.entities.Skill;
import com.jpcami.tads.xsearchpro.api.repositories.MutantRepository;
import com.jpcami.tads.xsearchpro.api.repositories.MutantSkillsRepository;
import com.jpcami.tads.xsearchpro.api.repositories.SkillRepository;
import com.jpcami.tads.xsearchpro.api.util.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.lang.management.MemoryUsage;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController(value = "mutants")
public class MutantController {

    @Autowired
    private MutantRepository repository;

    @Autowired
    private MutantSkillsRepository mutantSkillsRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mutant>> index() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping(value = "mutants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResult> add(@RequestBody @Valid Mutant mutant) {
        mutant.setId(null);

        if (repository.existsByNameIgnoreCase(mutant.getName())) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation("Já existe mutante cadastrado com esse nome"));
        }

        List<Skill> skills = mutant.getSkills();
        mutant.setSkills(null);

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Mutant saved = repository.save(mutant);

            for (Skill skill : skills) {
                Optional<Skill> originalSkill = skillRepository.findById(skill.getId());

                if (!originalSkill.isPresent()) {
                    transactionManager.rollback(status);
                    return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation("A habilidade com id [" + skill.getId() + "] não foi encontrada"));
                }

                MutantSkillsId mutantSkill = new MutantSkillsId();
                mutantSkill.setMutantId(saved.getId());
                mutantSkill.setSkillId(skill.getId());
                mutantSkillsRepository.save(new MutantSkills(mutantSkill));
            }

            transactionManager.commit(status);
            return ResponseEntity.ok(SimpleResult.success(saved.getId().intValue()));

        }
        catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }


    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResult> alter(@RequestBody @Valid Mutant mutant) {
        Optional<Mutant> optional = repository.findById(mutant.getId());

        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation("Mutante com id [" + mutant.getId() + "] não existe"));
        }

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Mutant original = optional.get();
            original.setName(mutant.getName());
            original.setPhoto(mutant.getPhoto());
            repository.save(original);

            for (Skill skill : original.getSkills()) {
                MutantSkillsId mutantSkill = new MutantSkillsId();
                mutantSkill.setMutantId(original.getId());
                mutantSkill.setSkillId(skill.getId());
                mutantSkillsRepository.deleteById(mutantSkill);
            }

            for (Skill skill : mutant.getSkills()) {
                Optional<Skill> originalSkill = skillRepository.findById(skill.getId());

                if (!originalSkill.isPresent()) {
                    transactionManager.rollback(status);
                    return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation("A habilidade com id [" + skill.getId() + "] não foi encontrada"));
                }

                MutantSkillsId mutantSkill = new MutantSkillsId();
                mutantSkill.setMutantId(mutant.getId());
                mutantSkill.setSkillId(skill.getId());
                mutantSkillsRepository.save(new MutantSkills(mutantSkill));
            }

            transactionManager.commit(status);

            return ResponseEntity.ok(SimpleResult.success());
        }
        catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    @DeleteMapping(value = "mutants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResult> delete(@PathVariable Long id) {
        Optional<Mutant> optional = repository.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation("Mutante com id [" + id + "] não existe"));
        }

        repository.deleteById(id);

        return ResponseEntity.ok(SimpleResult.success());
    }

    @GetMapping(value = "mutants/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mutant>> findByNameOrSkill(@PathVariable String name) {
        name = name == null ? "" : name;

        List<Mutant> mutants = repository.findAll();

        List<Mutant> result = new ArrayList<>();

        for (Mutant mutant: mutants) {
            if (replaceAccents(mutant.getName()).toUpperCase().contains(name.toUpperCase())) {
                result.add(mutant);
                continue;
            }

            if (mutant.getSkills() != null) {
                for (Skill skill : mutant.getSkills()) {
                    if (replaceAccents(skill.getName()).toUpperCase().contains(name.toUpperCase())) {
                        result.add(mutant);
                        continue;
                    }
                }
            }
        }

        return ResponseEntity.ok(result);
    }

    public String replaceAccents(String text) {
        String temp = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
