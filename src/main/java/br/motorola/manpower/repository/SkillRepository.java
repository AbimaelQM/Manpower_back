package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.Skill;

public interface SkillRepository extends JpaRepository<Skill,Long>{
    
    @Query(" SELECT s FROM Skill s INNER JOIN Area a ON s.area = a.id WHERE "+
           " s.name LIKE %?1%"
    )
    public List<Skill> findByAll(String termo);

    @Query("SELECT j FROM Job j WHERE j.skill.id = :skill_id")
    public List<Job> searchJobsBySkillId(@Param("skill_id") Long id);

    
}
