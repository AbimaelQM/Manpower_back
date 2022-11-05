package br.motorola.manpower.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Person implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String country;

    @Column(nullable = true)
    private String state;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String street;

    @Column(nullable = true)
    private String district;

    @Column(nullable = true)
    private String number;

    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private Job job;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PersonSkill> personSkills;

    @NotNull
    private boolean active = true;

    public String toString(){

        return this.name;
    }
    
}
