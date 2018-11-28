package com.jpcami.tads.xsearchpro.api.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "mutant")
public class Mutant {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name =  "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "photo")
    private byte[] photo;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "mutant_skills",
            joinColumns = @JoinColumn(name = "mutant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id",referencedColumnName = "id")
    )
    private List<Skill> skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
