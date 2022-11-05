package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.SkillLevel;
import br.motorola.manpower.model.Job;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long>{
    @Query("SELECT a FROM SkillLevel as a WHERE a.name LIKE %?1%")
    public List<SkillLevel> buscaTermo(String searchTerm);

    @Query("SELECT j FROM Job j WHERE j.skillLevel.id = :skillLevel_id")
    public List<Job> searchJobsBySkillLevelId(@Param("skillLevel_id") Long id);
}
