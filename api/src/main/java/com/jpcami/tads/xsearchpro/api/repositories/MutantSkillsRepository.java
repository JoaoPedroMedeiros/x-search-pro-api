package com.jpcami.tads.xsearchpro.api.repositories;

import com.jpcami.tads.xsearchpro.api.entities.MutantSkills;
import com.jpcami.tads.xsearchpro.api.entities.MutantSkillsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface MutantSkillsRepository extends JpaRepository<MutantSkills, MutantSkillsId> {
}

