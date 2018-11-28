package com.jpcami.tads.xsearchpro.api.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mutant_skills")
public class MutantSkills implements Serializable {

    @EmbeddedId
    private MutantSkillsId id;

    public MutantSkills() {
    }

    public MutantSkills(MutantSkillsId id) {
        this.id = id;
    }

    public MutantSkillsId getId() {
        return id;
    }

    public void setId(MutantSkillsId id) {
        this.id = id;
    }
}
