package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.Type;
import br.motorola.manpower.model.Job;

@Repository
public interface TypeRepository extends JpaRepository <Type,Long>{
    
    @Query("SELECT t FROM Type as t WHERE t.name LIKE %?1% ")
    public List <Type> buscaTermo(String termo);

    // Obtêm todas as skills de uma area especifica
    @Query("SELECT j FROM Job j WHERE j.type.id = :type_id")
    public List<Job> searchJobsByTypeId(@Param("type_id") Long id);

}
