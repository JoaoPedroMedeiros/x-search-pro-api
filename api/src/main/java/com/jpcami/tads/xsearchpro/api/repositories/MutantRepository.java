package com.jpcami.tads.xsearchpro.api.repositories;

import com.jpcami.tads.xsearchpro.api.entities.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MutantRepository extends JpaRepository<Mutant, Long> {

    boolean existsByNameIgnoreCase(String name);
}
