package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.PersonSkill;

@Repository
public interface PersonSkillRepository extends JpaRepository <PersonSkill, Long> {

    @Query("SELECT ps FROM PersonSkill ps " +
    "LEFT JOIN Person p ON p.id = ps.person " +
    "LEFT JOIN Skill s ON s.id = ps.skill " +
    "LEFT JOIN SkillLevel sl ON sl.id = ps.skillLevel " +
    "WHERE p.name LIKE %?1% OR " +
    "s.name LIKE %?1% OR " +
    "sl.name LIKE %?1%")
    public List<PersonSkill> findByAll(String termo);

}
