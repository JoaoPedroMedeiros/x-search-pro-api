package com.jpcami.tads.xsearchpro.api.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MutantSkillsId implements Serializable {

    @Column(name = "mutant_id")
    private Long mutantId;

    @Column(name = "skill_id")
    private Long skillId;

    public Long getMutantId() {
        return mutantId;
    }

    public void setMutantId(Long mutantId) {
        this.mutantId = mutantId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}
