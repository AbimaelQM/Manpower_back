package br.motorola.manpower.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.motorola.manpower.model.Historical;

public interface HistoricalRepository extends JpaRepository<Historical, Long> {
    
    // List<Historical> findByAll (String termoBusca);
    List<Historical> findByDate (Date date);
    List<Historical> findByPerson(@Param("person") Long id);
    List<Historical> findByJob(@Param("job") Long id);
}
