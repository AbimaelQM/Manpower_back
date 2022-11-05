package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.motorola.manpower.model.Job;

public interface JobRepository extends JpaRepository
<Job,Long>{
    @Query(" SELECT j FROM Job j " +
    "LEFT JOIN SkillLevel sl ON sl.id = j.skillLevel " +
    "LEFT JOIN Skill s ON s.id = j.skill " +
    "LEFT JOIN Address a ON a.id = j.address " +
    "LEFT JOIN SubTeam st ON st.id = j.subTeam " +
    "WHERE j.type LIKE %?1% OR " +
    "j.modality LIKE %?1% OR " +
    "sl.name LIKE %?1% OR " +
    "s.name LIKE %?1% OR " +
    "a.country LIKE %?1% OR " +
    "st.name LIKE %?1%")
    public List<Job> findByAll(String termo);

    @Query("SELECT j FROM Job j ORDER BY id DESC")
    public List<Job> findAll();

    @Query("SELECT j FROM Job j ORDER BY id DESC")
    public Page<Job> findAll(Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.active = 1")
    public Page<Job> findByActive(Pageable pageable);
    
}
