package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.Area;
import br.motorola.manpower.model.Skill;

@Repository
public interface AreaRepository extends JpaRepository<Area,Long>{
    
    @Query("SELECT a FROM Area as a WHERE a.name LIKE %?1% ")
    public List<Area> buscaTermo(String termo);

    // Obtêm todas as skills de uma area especifica
    @Query("SELECT s FROM Skill s WHERE s.area.id = :area_id")
    public List<Skill> searchSkillsByAreaId(@Param("area_id") Long id);

    
}
