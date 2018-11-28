package com.jpcami.tads.xsearchpro.api.repositories;

import com.jpcami.tads.xsearchpro.api.entities.Mutant;
import com.jpcami.tads.xsearchpro.api.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SkillRepository extends JpaRepository<Skill, Long> {

    boolean existsByNameIgnoreCase(String name);
}
