package br.motorola.manpower.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(unique=true)
    private String email;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ERole role;

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Team> teams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {

    
        return password;
    }
    
    public void setPassword(String password) {
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.password = passwordEncoder.encode(password);
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
    
    
    public String toString(){

        return this.email;
    }
}
