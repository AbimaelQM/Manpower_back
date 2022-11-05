package br.motorola.manpower.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class PersonSkill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @NotNull
    private Person person;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    @NotNull
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "area_id")
    @NotNull
    private Area area;

    @ManyToOne
    @JoinColumn(name = "skill_level_id")
    @NotNull
    private SkillLevel skillLevel;

}
