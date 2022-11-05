package br.motorola.manpower.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SkillLevel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "skillLevel")
    @JsonIgnore
    private List<Job> jobs;

    @OneToMany(mappedBy = "skillLevel")
    @JsonIgnore
    private List<PersonSkill> personSkills;

    public String toString(){

        return this.name;
    }
}
