package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.Person;
import br.motorola.manpower.model.PersonSkill;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
    
    @Query("SELECT p FROM Person p WHERE p.name LIKE %:termo% OR p.email LIKE %:termo%")
    public List<Person> findByAll(@Param("termo") String termo);

    @Query("SELECT P FROM Person P ORDER BY id DESC")
    public List<Person> findAll(Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.person.id = :person_id")
    public Job searchJobByPersonId(@Param("person_id") Long id);

    @Query("SELECT p FROM Person p "
        +"LEFT JOIN Job j ON p.id = j.person.id " 
        +"WHERE j.person.id IS NULL"
        )
    public List<Person> searchPeopleFreeJob();


    @Query("SELECT ps FROM PersonSkill ps WHERE ps.person.id = :person_id")
    public List<PersonSkill> searchSkillsByPersonId(@Param("person_id") Long id);
}
