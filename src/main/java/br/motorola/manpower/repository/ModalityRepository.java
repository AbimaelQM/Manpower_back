package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.Modality;
import br.motorola.manpower.model.Job;

@Repository
public interface ModalityRepository extends JpaRepository<Modality,Long>{
    
    @Query("SELECT m FROM Modality as m WHERE m.name LIKE %?1% ")
    public List<Modality> buscaTermo(String termo);

    // Obtêm todas as skills de uma modality especifica
    @Query("SELECT j FROM Job j WHERE j.modality.id = :modality_id")
    public List<Job> searchJobsByModalityId(@Param("modality_id") Long id);

    
}
