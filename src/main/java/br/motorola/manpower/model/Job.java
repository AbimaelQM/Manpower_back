package br.motorola.manpower.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "modality_id")
    private Modality modality;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "skill_level_id")
    @NotNull
    private SkillLevel skillLevel;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "skill_id")
    @NotNull
    private Skill skill;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sub_team")
    @NotNull
    private SubTeam subTeam;

    @OneToOne
    @JoinColumn(nullable = true,unique = true, name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(nullable = false)
    @NotNull
    private boolean active = true;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public List<String> getProperties(){

        Field[] propriedades = Job.class.getDeclaredFields();
        List<String> properties = new ArrayList<>();

        String nomeAtributo = "";
        for (Field campo : propriedades) {            
            try {               
                nomeAtributo = campo.getName();
                properties.add(nomeAtributo);
                                          
            } catch (Exception e) {
                e.printStackTrace();
            }           
          
        } 


        return properties;

    }

    public String toString(){

        return "Job fazer deoius";
    }

}
